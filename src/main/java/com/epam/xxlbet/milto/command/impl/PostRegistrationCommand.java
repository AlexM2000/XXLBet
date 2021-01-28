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
import com.epam.xxlbet.milto.service.impl.JavaxEmailSenderImpl;
import com.epam.xxlbet.milto.service.impl.VerificationTokenServiceImpl;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import com.epam.xxlbet.milto.validator.Validator;
import com.epam.xxlbet.milto.validator.impl.EmailValidator;
import com.epam.xxlbet.milto.validator.impl.PasswordFormatValidator;
import com.epam.xxlbet.milto.validator.impl.PhoneNumberExistsValidator;
import com.epam.xxlbet.milto.validator.impl.PhoneNumberValidator;
import com.epam.xxlbet.milto.validator.impl.RepeatPasswordValidator;
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
    private Validator userExistsValidator;
    private Validator phoneNumberExistsValidator;
    private Validator passwordValidator;
    private Validator phoneNumberValidator;
    private Validator emailValidator;
    private Validator repeatPasswordValidator;
    private UserService userService;
    private VerificationTokenService verificationTokenService;
    private EmailSender emailSender;

    public PostRegistrationCommand(final UserService userService) {
        this.userService = userService;
        this.emailValidator = EmailValidator.getInstance();
        this.passwordValidator = PasswordFormatValidator.getInstance();
        this.phoneNumberValidator = PhoneNumberValidator.getInstance();
        this.userExistsValidator = UserExistsValidator.getInstance();
        this.phoneNumberExistsValidator = PhoneNumberExistsValidator.getInstance();
        this.repeatPasswordValidator = RepeatPasswordValidator.getInstance();
        this.verificationTokenService = VerificationTokenServiceImpl.getInstance();
        this.emailSender = JavaxEmailSenderImpl.getInstance();
    }


    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());
        RegistrationRequest requestBody = getRequestBody(request, RegistrationRequest.class);
        String locale = getCurrentLocale(request);
        validate(requestBody.getEmail(), locale, emailValidator);
        validate(requestBody.getPassword(), locale, passwordValidator);
        validate(requestBody.getPhoneNumber(), locale, phoneNumberValidator);
        validate(requestBody.getPhoneNumber(), locale, phoneNumberExistsValidator);
        validate(requestBody.getEmail(), locale, userExistsValidator);
        validate(requestBody, locale, repeatPasswordValidator);

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            VerificationToken token = verificationTokenService.createToken(
                    userService.createNewUser(requestBody).getId()
            );

            sendRegistrationEmail(requestBody.getEmail(), locale, token);
        }

        return CommandResult.createWriteDirectlyToResponseCommandResult(getErrors());
    }

    /**
     * Send email with link to confirm registration.
     *
     * @param email         recipient email
     * @param currentLocale current locale
     * @param token         token that will be attached to email
     */
    private void sendRegistrationEmail(String email, String currentLocale, VerificationToken token) {
        String msgFile = getNameOfLocaleFile(currentLocale);

        try {
            emailSender.sendEmail(
                    email,
                    getText(msgFile, currentLocale, "email.confirmregistration.body", token.getToken(), token.getToken()),
                    getText(msgFile, currentLocale, "email.confirmregistration.subject")
            );
        } catch (MessagingException e) {
            throw new ServiceException("Something went wrong during sending email...", e);
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
     * @param locale  Language on which message must be
     * @param msgId   id of the message
     * @param args    Arguments for message formatting
     */
    private String getText(String msgFile, String locale, String msgId, Object... args) {
        return format(PropertyLoader.getInstance().getStringProperty(msgFile, msgId)
                .orElseThrow(() -> new PropertyNotFoundException(format("Can't find text for %s locale", locale))), args);
    }
}
