package com.epam.xxlbet.milto.service.impl;

import com.epam.xxlbet.milto.service.EmailSender;
import com.epam.xxlbet.milto.utils.PropertyLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 *
 * @author Aliaksei Milto
 */
public class JavaxEmailSenderImpl implements EmailSender {
    private static final Logger LOG = LoggerFactory.getLogger(JavaxEmailSenderImpl.class);
    private static JavaxEmailSenderImpl instance;
    private PropertyLoader loader;
    private String host;
    private String port;
    private String auth;
    private String startTlsEnable;

    private JavaxEmailSenderImpl() {
        loader = PropertyLoader.getInstance();
        host = loader.getStringProperty(MAIL_PROPERTIES, "mail.smtp.host").get();
        port = loader.getStringProperty(MAIL_PROPERTIES, "mail.smtp.port").get();
        auth = loader.getStringProperty(MAIL_PROPERTIES, "mail.smtp.auth").get();
        startTlsEnable = loader.getStringProperty(MAIL_PROPERTIES, "mail.smtp.starttls.enable").get();
    }

    public static JavaxEmailSenderImpl getInstance() {
        if (instance == null) {
            instance = new JavaxEmailSenderImpl();
        }

        return instance;
    }

    @Override
    public void sendEmail(String emailTo, String htmlMessage, String subject) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", host); //SMTP Host
        props.put("mail.smtp.port", port); //TLS Port
        props.put("mail.smtp.auth", auth); //enable authentication
        props.put("mail.smtp.starttls.enable", startTlsEnable); //enable STARTTLS
        props.put("mail.smtp.ssl.trust", loader.getStringProperty(MAIL_PROPERTIES, "mail.smtp.ssl.trust").get());

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        loader.getStringProperty(MAIL_PROPERTIES, "username").get(), loader.getStringProperty(MAIL_PROPERTIES, "password").get()
                );
            }
        };

        Session session = Session.getInstance(props, auth);
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(new InternetAddress(loader.getStringProperty(MAIL_PROPERTIES, "username").get(), "NoReply-XXLBet"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo, false));
            msg.setSubject(subject, String.valueOf(UTF_8));
            msg.setContent(htmlMessage, "text/html");
            msg.setSentDate(new Date());

            Transport.send(msg);
        } catch (UnsupportedEncodingException e) {
            LOG.error("Somenthing wrong with address encoding...", e);
        }
    }
}
