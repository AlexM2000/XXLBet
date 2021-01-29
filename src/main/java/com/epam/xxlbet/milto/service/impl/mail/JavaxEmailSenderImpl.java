package com.epam.xxlbet.milto.service.impl.mail;

import com.epam.xxlbet.milto.exceptions.ServiceException;
import com.epam.xxlbet.milto.service.EmailSender;
import com.epam.xxlbet.milto.utils.PropertyLoader;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import static com.epam.xxlbet.milto.utils.XxlBetConstants.MAIL_PROPERTIES;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * JavaxEmailSenderImpl.
 * This implementation uses classes from javax.mail package.
 *
 * @author Aliaksei Milto
 */
public class JavaxEmailSenderImpl implements EmailSender {
    private static JavaxEmailSenderImpl instance;
    private String from;
    private Session session;
    private Transport transport;

    private JavaxEmailSenderImpl() {
        init();
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
            if (!transport.isConnected()) {
                transport.connect();
            }

            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(new InternetAddress(from, "NoReply-XXLBet"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo, false));
            msg.setSubject(subject, String.valueOf(UTF_8));
            msg.setContent(htmlMessage, "text/html");
            msg.setSentDate(new Date());

            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new ServiceException("Something wrong with address encoding...", e);
        }
    }

    @Override
    public void shutdown() {
        try {
            transport.close();
        } catch (MessagingException e) {
            throw new ServiceException("Something wrong when closing connection...", e);
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
        this.session = createSession();

        try {
            this.transport = session.getTransport();
            transport.connect();
        } catch (MessagingException e) {
            throw new ServiceException("Something went wrong while connecting to smtp server", e);
        }
    }

    /**
     * Create session with smtp server
     * with session properties from mail.properties file.
     * @return Session instance
     */
    private Session createSession() {
        PropertyLoader loader = PropertyLoader.getInstance();

        Properties props = new Properties();

        props.put("mail.smtp.host", loader.getStringProperty(MAIL_PROPERTIES, "mail.smtp.host").get()); //SMTP Host
        props.put("mail.smtp.port", loader.getStringProperty(MAIL_PROPERTIES, "mail.smtp.port").get()); //TLS Port
        props.put("mail.smtp.auth", loader.getStringProperty(MAIL_PROPERTIES, "mail.smtp.auth").get()); //enable authentication
        props.put("mail.smtp.starttls.enable", loader.getStringProperty(MAIL_PROPERTIES, "mail.smtp.starttls.enable").get()); //enable STARTTLS
        props.put("mail.smtp.ssl.trust", loader.getStringProperty(MAIL_PROPERTIES, "mail.smtp.ssl.trust").get());

        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        loader.getStringProperty(MAIL_PROPERTIES, "username").get(), loader.getStringProperty(MAIL_PROPERTIES, "password").get()
                );
            }
        };

        return Session.getInstance(props, authenticator);
    }
}
