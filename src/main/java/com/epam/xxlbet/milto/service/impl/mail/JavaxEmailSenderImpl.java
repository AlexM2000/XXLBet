package com.epam.xxlbet.milto.service.impl.mail;

import com.epam.xxlbet.milto.connection.mail.TransportConnectionPool;
import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.EmailSender;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.MAIL_PROPERTIES;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * JavaxEmailSenderImpl.
 * This implementation uses classes from javax.mail package.
 *
 * @author Aliaksei Milto
 */
public class JavaxEmailSenderImpl implements EmailSender {
    private static final Logger LOG = LoggerFactory.getLogger(JavaxEmailSenderImpl.class);
    private static JavaxEmailSenderImpl instance;

    private final TransportConnectionPool connectionPool;
    private String from;

    private JavaxEmailSenderImpl() {
        init();
        this.connectionPool = TransportConnectionPool.getInstance();
    }

    public static JavaxEmailSenderImpl getInstance() {
        if (instance == null) {
            instance = new JavaxEmailSenderImpl();
        }

        return instance;
    }

    @Override
    public void sendEmail(String emailTo, String htmlMessage, String subject) {
        try {
            Transport transport = connectionPool.getConnection();

            if (!transport.isConnected()) {
                LOG.debug("Transport is not connected! Connecting...");
                transport.connect();
            }

            MimeMessage msg = new MimeMessage(connectionPool.getSession());
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(new InternetAddress(from, "NoReply-XXLBet"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo, false));
            msg.setSubject(subject, String.valueOf(UTF_8));
            msg.setContent(htmlMessage, "text/html");
            msg.setSentDate(new Date());

            transport.sendMessage(msg, msg.getAllRecipients());

            connectionPool.releaseConnection(transport);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new ServiceException("Something wrong happened while sending email...", e);
        }
    }

    @Override
    public void shutdown() {
        try {
            connectionPool.closeAllConnections();
        } catch (MessagingException e) {
            throw new ServiceException("Something wrong when closing connection with smtp", e);
        }
    }

    /**
     * Initialize service.
     * Set "from" email, create session instance,
     * transport instance and perform connection.
     */
    private void init() {
        PropertyLoader loader = PropertyLoader.getInstance();
        this.from = loader.getStringProperty(MAIL_PROPERTIES, "username").get();
    }

}
