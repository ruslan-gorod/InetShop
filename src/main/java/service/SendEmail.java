package service;

import java.util.Properties;
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
        final String username = "ruslan.malyavsky@gmail.com";
        final String password = "135792MrM";
        logger.debug("entering google email with data -" + username + " " + password);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            logger.debug("sending a message");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Code confirm");
            message.setText(String.valueOf(code));
            Transport.send(message);
            logger.debug("Code " + code + " was sended");
        } catch (MessagingException e) {
            logger.error("Can't send a message" + e);
            throw new RuntimeException(e);
        }
    }
}
