package pl.mskreczko.foodordering.notifications;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailNotifier implements Notifier {

    private final JavaMailSenderImpl mailSender;

    EmailNotifier() {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost("localhost");
        mailSender.setPort(3025);

        // TODO
        // MOVE TO PROPERTIES FILE
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.debug", "true");
    }

    @Override
    public void sendNotification(String target, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(target);
        simpleMailMessage.setSubject("test");
        simpleMailMessage.setText(content);
        mailSender.send(simpleMailMessage);
    }
}
