package com.epam.xxlbet.milto.scheduled;

import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.exceptions.PropertyNotFoundException;
import com.epam.xxlbet.milto.service.EmailSender;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.service.impl.EmailSenderImpl;
import com.epam.xxlbet.milto.service.impl.UserServiceImpl;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.util.List;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.MESSAGES_EN_PROPERTIES;

/**
 * Deletes users that didn't confirm registration.
 *
 * @author Aliaksei Milto
 */
public class DeleteUnconfirmedUsersJob implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(DeleteUnconfirmedUsersJob.class);
    private static DeleteUnconfirmedUsersJob instance;
    private UserService userService;
    private EmailSender emailSender;

    private DeleteUnconfirmedUsersJob() {
        userService = UserServiceImpl.getInstance();
        emailSender = EmailSenderImpl.getInstance();
    }

    public static DeleteUnconfirmedUsersJob getInstance() {
        if (instance == null) {
            instance = new DeleteUnconfirmedUsersJob();
        }

        return instance;
    }

    // Should have stored language inside user for i18n, but i'm lazy.
    @Override
    public void run() {
        List<User> unconfirmedUsers = userService.getAllUnconfirmedUsers();
        userService.deleteAllUnconfirmedUsers();
        for (User user : unconfirmedUsers) {
            try {
                emailSender.sendEmail(
                        user.getEmail(),
                        PropertyLoader.getInstance().getStringProperty(MESSAGES_EN_PROPERTIES, "email.notconfirmed.body")
                                .orElseThrow(() -> new PropertyNotFoundException("Couldn't find property for en language email.notconfirmed.body")),
                        PropertyLoader.getInstance().getStringProperty(MESSAGES_EN_PROPERTIES, "email.notconfirmed.subject")
                                .orElseThrow(() -> new PropertyNotFoundException("Couldn't find property for en language email.notconfirmed.subject")));
            } catch (MessagingException e) {
                LOG.error("Error occured while executing DeleteUnconfirmedUsersJob", e);
            }
        }
    }
}