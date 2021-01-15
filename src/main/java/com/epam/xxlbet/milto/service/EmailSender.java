package com.epam.xxlbet.milto.service;

import javax.mail.MessagingException;

/**
 * EmailSender.
 *
 * @author Aliaksei Milto
 */
public interface EmailSender {

    /**
     * Send email.
     *
     * @param emailTo recipient email
     * @param htmlMessage email body in html
     * @param subject email subject
     */
    void sendEmail(String emailTo, String htmlMessage, String subject) throws MessagingException;
}
