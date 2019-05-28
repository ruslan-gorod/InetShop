package service;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

public class SendEmail {

    private static final Logger logger = Logger.getLogger(SendEmail.class);

    public static void sendMessage(String email, int code) {
        final String username = "mrmtestjavama1973@gmail.com";
        final String password = "1973java1973!";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Code for order confirm");
            message.setText(String.valueOf(code));

            Transport.send(message);

            logger.debug("Message with code " + code + " sent to " + email);

        } catch (MessagingException e) {
            logger.error("Sending message error", e);
        }
    }
}