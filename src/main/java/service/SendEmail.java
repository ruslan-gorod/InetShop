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

        final String myEmail = "ruslan.malyavsky@gmail.com";
        final String password = "135792MrM";
        logger.debug("entering google email with data -" + myEmail);
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        logger.debug("properties initializing " + props);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(myEmail, password);
                    }
                });

        try {
            logger.debug("sending a message");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Code confirm");
            message.setText(String.valueOf(code));

            Transport.send(message);

            logger.debug("message was sended");

        } catch (MessagingException e) {
            logger.error("cant send a message" + e);
            throw new RuntimeException(e);
        }
    }
}
