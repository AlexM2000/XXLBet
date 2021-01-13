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
        RegistrationRequest requestBody = getRequestBody(request, RegistrationRequest.class);
        validate(requestBody.getEmail(), getCurrentLocale(request), emailValidator);
        validate(requestBody.getPassword(), getCurrentLocale(request), passwordValidator);
        validate(requestBody.getPhoneNumber(), getCurrentLocale(request), phoneNumberValidator);
        validate(requestBody.getPhoneNumber(), getCurrentLocale(request), phoneNumberExistsValidator);
        validate(requestBody.getEmail(), getCurrentLocale(request), userExistsValidater);

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            VerificationToken token = verificationTokenService.createToken(
                    userService.createNewUser(requestBody).getId()
            );

            String currentLocale = getCurrentLocale(request);
            String msgFile = getNameOfLocaleFile(currentLocale);

            sendRegistrationEmail(requestBody.getEmail(), msgFile, currentLocale, token);
        }

        return CommandResult.createWriteDirectlyToResponseCommandResult(getErrors());
    }

    /**
     * Send email with link to confirm registration.
     *
     * @param email recipient email
     * @param currentLocale current locale
     * @param msgFile file with localized messages
     * @param token token that will be attached to email
     */
    private void sendRegistrationEmail(String email, String msgFile, String currentLocale, VerificationToken token) {
        try {
            emailSender.sendEmail(
                    email,
                    getText(msgFile, currentLocale, "email.confirmregistration.body", token.getToken(), token.getToken()),
                    getText(msgFile, currentLocale, "email.confirmregistration.subject")
            );
        } catch (MessagingException e) {
            getLogger().error("Something went wrong during sending email...", e);
        }
    }

    /**
     * Get name of file with localized messages depending on given language.
     *
     * @param locale given language.
     */
    private String getNameOfLocaleFile(final String locale) {
        switch (locale) {
            default:
            case "en":
                return MESSAGES_EN_PROPERTIES;
            case "be":
                return MESSAGES_BE_PROPERTIES;
            case "ru":
                return MESSAGES_RU_PROPERTIES;
        }
    }

    /**
     * Return text from messages file with given locale and id.
     *
     * @param msgFile File which contains localized messages.
     * @param locale Language on which message must be
     * @param msgId id of the message
     * @param args Arguments for message formatting
     */
    private String getText(String msgFile, String locale, String msgId, Object... args) {
        return format(PropertyLoader.getInstance().getStringProperty(msgFile, msgId)
                .orElseThrow(() -> new PropertyNotFoundException(format("Can't find text for %s locale", locale))), args);
    }
}
