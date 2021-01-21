package com.epam.xxlbet.milto.command.impl;

import com.epam.xxlbet.milto.command.CommandResult;
import com.epam.xxlbet.milto.context.RequestContext;
import com.epam.xxlbet.milto.context.ResponseContext;
import com.epam.xxlbet.milto.domain.PasswordChangeRequest;
import com.epam.xxlbet.milto.exceptions.PropertyNotFoundException;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.EmailSender;
import com.epam.xxlbet.milto.service.PasswordChangeRequestService;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import com.epam.xxlbet.milto.validator.Validator;
import com.epam.xxlbet.milto.validator.impl.UserNotExistsValidator;

import javax.mail.MessagingException;

import static com.epam.xxlbet.milto.command.CommandResult.createWriteDirectlyToResponseCommandResult;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.MESSAGES_BE_PROPERTIES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.MESSAGES_EN_PROPERTIES;
import static com.epam.xxlbet.milto.utils.XxlBetConstants.MESSAGES_RU_PROPERTIES;
import static java.lang.String.format;

/**
 * PostCreateChangePasswordCommand.
 *
 * @author Aliaksei Milto
 */
public class PostCreateChangePasswordRequestCommand extends AbstractCommand {
    private PasswordChangeRequestService service;
    private EmailSender emailSender;
    private Validator userNotExistsValidator;

    public PostCreateChangePasswordRequestCommand(
            PasswordChangeRequestService service,
            EmailSender emailSender) {
        this.service = service;
        this.emailSender = emailSender;
        this.userNotExistsValidator = UserNotExistsValidator.getInstance();
    }

    @Override
    public CommandResult execute(RequestContext request, ResponseContext response) {
        getLogger().debug("Executing " + this.getClass());

        String email = getRequestBody(request, String.class);
        validate(email, getCurrentLocale(request), userNotExistsValidator);

        if (getErrors().get(STATUS).equals(VERIFIED)) {
            PasswordChangeRequest pswdRequest = service.createPasswordChangeRequest(email);
            sendPasswordChangeEmail(email, getCurrentLocale(request), pswdRequest.getToken());
        }

        return createWriteDirectlyToResponseCommandResult(getErrors());
    }

    /**
     * Send password change email.
     *
     * @param email recipient email
     * @param currentLocale current locale
     * @param token token, that will be attached to password change link
     */
    private void sendPasswordChangeEmail(String email, String currentLocale, String token) {
        String msgFile = getNameOfLocaleFile(currentLocale);

        try {
            emailSender.sendEmail(
                    email,
                    getText(msgFile, currentLocale, "email.change.password.body", token, token),
                    getText(msgFile, currentLocale, "email.change.password.subject")
            );
        } catch (MessagingException e) {
            getLogger().error("Something went wrong during sending email...", e);
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
     * @param locale Language on which message must be
     * @param msgId id of the message
     * @param args Arguments for message formatting
     */
    private String getText(String msgFile, String locale, String msgId, Object... args) {
        return format(PropertyLoader.getInstance().getStringProperty(msgFile, msgId)
                .orElseThrow(() -> new PropertyNotFoundException(format("Can't find text for %s locale", locale))), args);
    }
}
