package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.domain.VerificationToken;
import com.epam.xxlbet.milto.exceptions.PropertyNotFoundException;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.requestandresponsebody.RegistrationRequest;
import com.epam.xxlbet.milto.service.EmailSender;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.service.VerificationTokenService;
import com.epam.xxlbet.milto.service.impl.EmailSenderImpl;
import com.epam.xxlbet.milto.service.impl.VerificationTokenServiceImpl;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import com.epam.xxlbet.milto.validator.Validator;
import com.epam.xxlbet.milto.validator.impl.EmailValidator;
import com.epam.xxlbet.milto.validator.impl.PasswordValidator;
import com.epam.xxlbet.milto.validator.impl.PhoneNumberExistsValidator;
import com.epam.xxlbet.milto.validator.impl.PhoneNumberValidator;
import com.epam.xxlbet.milto.validator.impl.UserExistsValidator;

import javax.mail.MessagingException;
import java.io.IOException;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.MESSAGES_BE_PROPERTIES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.MESSAGES_EN_PROPERTIES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.MESSAGES_RU_PROPERTIES;
import static java.lang.String.format;

/**
 * PostRegistrationCommand.
 *
 * @author Aliaksei Milto
 */
public class PostRegistrationCommand extends AbstractCommand {
    private Validator userExistsValidater;
    private Validator phoneNumberExistsValidator;
    private Validator passwordValidator;
    private Validator phoneNumberValidator;
    private Validator emailValidator;
    private UserService userService;
    private VerificationTokenService verificationTokenService;
    private EmailSender emailSender;


    public PostRegistrationCommand(final UserService userService) {
        this.userService = userService;
        this.emailValidator = EmailValidator.getInstance();
        this.passwordValidator = PasswordValidator.getInstance();
        this.phoneNumberValidator = PhoneNumberValidator.getInstance();
        this.userExistsValidater = UserExistsValidator.getInstance();
        this.phoneNumberExistsValidator = PhoneNumberExistsValidator.getInstance();
        this.verificationTokenService = VerificationTokenServiceImpl.getInstance();
        this.emailSender = EmailSenderImpl.getInstance();
    }


    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) throws ServiceException {
        getLogger().debug("Executing " + this.getClass());
        RegistrationRequest body = getRequestBody(request, RegistrationRequest.class);
        validate(body.getEmail(), getCurrentLocale(request), emailValidator);
        validate(body.getPassword(), getCurrentLocale(request), passwordValidator);
        validate(body.getPhoneNumber(), getCurrentLocale(request), phoneNumberValidator);
        validate(body.getPhoneNumber(), getCurrentLocale(request), phoneNumberExistsValidator);
        validate(body.getEmail(), getCurrentLocale(request), userExistsValidater);

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            userService.createNewUser(body);

            VerificationToken token = verificationTokenService.createToken(userService.getUserByEmail(body.getEmail()).getId());

            String msgFile;
            switch (getCurrentLocale(request)) {
                default:
                case "en":
                    msgFile = MESSAGES_EN_PROPERTIES;
                    break;
                case "be":
                    msgFile = MESSAGES_BE_PROPERTIES;
                    break;
                case "ru":
                    msgFile = MESSAGES_RU_PROPERTIES;
                    break;
            }

            try {
                emailSender.sendEmail(
                        body.getEmail(),
                        format(PropertyLoader.getInstance().getStringProperty(msgFile, "email.confirmregistration.body")
                                .orElseThrow(() -> new PropertyNotFoundException(format("Can't find email body for %s locale", getCurrentLocale(request)))), token.getToken(), token.getToken()),
                        PropertyLoader.getInstance().getStringProperty(msgFile, "email.confirmregistration.subject")
                                .orElseThrow(() -> new PropertyNotFoundException(format("Can't find email subject for %s locale", getCurrentLocale(request))))
                );
            } catch (MessagingException e) {
                getLogger().error("Something went wrong during sending email...", e);
            }
        }

        try {
            response.writeJSONValue(getErrors());
            getErrors().clear();
        } catch (final IOException e) {
            getLogger().error("Something went wrong during writing response...", e);
        }

        return CommandResult.createWriteDirectlyToResponseCommandResult();
    }
}
