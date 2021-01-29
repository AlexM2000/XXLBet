package com.epam.xxlbet.milto.scheduled;

import com.epam.xxlbet.milto.domain.User;
import com.epam.xxlbet.milto.exceptions.PropertyNotFoundException;
import com.epam.xxlbet.milto.service.EmailSender;
import com.epam.xxlbet.milto.service.UserService;
import com.epam.xxlbet.milto.service.impl.mail.JavaxEmailSenderImpl;
import com.epam.xxlbet.milto.service.impl.UserServiceImpl;
import com.epam.xxlbet.milto.service.impl.mail.MailThread;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.MESSAGES_EN_PROPERTIES;

/**
 * Deletes users that didn't confirm registration.
 *
 * @author Aliaksei Milto
 */
public final class DeleteUnconfirmedUsersJob implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(DeleteUnconfirmedUsersJob.class);
    private static DeleteUnconfirmedUsersJob instance;
    private UserService userService;
    private EmailSender emailSender;

    private DeleteUnconfirmedUsersJob() {
        LOG.debug("Creating " + DeleteUnconfirmedUsersJob.class);
        userService = UserServiceImpl.getInstance();
        emailSender = JavaxEmailSenderImpl.getInstance();
    }

    public static DeleteUnconfirmedUsersJob getInstance() {
        if (instance == null) {
            instance = new DeleteUnconfirmedUsersJob();
        }

        return instance;
    }

    @Override
    public void run() {
        LOG.debug("Executing DeleteUnconfirmedUsersJob...");

        List<User> unconfirmedUsers = userService.getAllUnconfirmedUsers();
        userService.deleteAllUnconfirmedUsers();

        for (User user : unconfirmedUsers) {
            new MailThread(
                    user.getEmail(),
                    PropertyLoader.getInstance().getStringProperty(MESSAGES_EN_PROPERTIES, "email.notconfirmed.body")
                            .orElseThrow(() -> new PropertyNotFoundException("Couldn't find property for en language email.notconfirmed.body")),
                    PropertyLoader.getInstance().getStringProperty(MESSAGES_EN_PROPERTIES, "email.notconfirmed.subject")
                            .orElseThrow(() -> new PropertyNotFoundException("Couldn't find property for en language email.notconfirmed.subject")),
                    emailSender
            ).start();
        }

        LOG.debug("Executed DeleteUnconfirmedUsersJob successfully");
    }
}
