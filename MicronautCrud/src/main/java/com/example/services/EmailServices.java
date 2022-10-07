package com.example.services;

import com.example.entities.EmailDetails;
import jakarta.inject.Singleton;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Singleton
public class EmailServices {

    public static void sendEmail(EmailDetails emailDetails) {

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("abhipatil7038@gmail.com","vojbuukdqgwpdvop");
            }
        });

        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress("abhipatil7038@gmail.com"));
            message.setSubject(emailDetails.getSubject());
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDetails.getTo()));
            message.setText(emailDetails.getMessage());

            Transport.send(message);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}