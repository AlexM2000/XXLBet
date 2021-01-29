package com.epam.xxlbet.milto.service;

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
    void sendEmail(String emailTo, String htmlMessage, String subject);

    /**
     * Close any resources that used for email sending.
     * Part of shutdown application flow.
     */
    void shutdown();
}
