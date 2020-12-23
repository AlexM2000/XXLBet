package com.epam.xxlbet.milto.service;

import javax.mail.MessagingException;

/**
 * EmailSender.
 *
 * @author Aliaksei Milto
 */
public interface EmailSender {
    void sendEmail(String emailTo, String htmlMessage, String subject) throws MessagingException;
}
