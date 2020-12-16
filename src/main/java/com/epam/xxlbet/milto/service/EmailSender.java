package com.epam.xxlbet.milto.service;

import javax.mail.MessagingException;

public interface EmailSender {
    void sendEmail(String emailTo, String htmlMessage, String subject) throws MessagingException;
}
