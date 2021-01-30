package com.epam.xxlbet.milto.service.impl.mail;

import com.epam.xxlbet.milto.service.EmailSender;

/**
 * MailThread.
 * Runs email sending task in separated thread.
 *
 * @author Aliaksei Milto
 */
public class MailThread extends Thread {
    private EmailSender emailSender;
    private String emailTo;
    private String htmlMessage;
    private String subject;

    public MailThread(String emailTo, String htmlMessage, String subject, EmailSender emailSender) {
        this.emailTo = emailTo;
        this.htmlMessage = htmlMessage;
        this.subject = subject;
        this.emailSender = emailSender;
    }

    @Override
    public void run() {
        emailSender.sendEmail(emailTo, htmlMessage, subject);
    }
}
