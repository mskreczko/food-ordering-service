package pl.mskreczko.foodordering.notifications;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import jakarta.mail.Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmailNotifierTest {

    Notifier emailNotifier;

    GreenMail smtpTestServer;

    @BeforeEach
    void setup() {
        emailNotifier = new EmailNotifier();
        smtpTestServer = new GreenMail(ServerSetup.SMTP.withPort(3025));
        smtpTestServer.start();
    }

    @Test
    void test_sendNotification() throws Exception {
        emailNotifier.sendNotification("test@receiver.com", "test content");

        Message[] messages = smtpTestServer.getReceivedMessages();
        Assertions.assertEquals(1, messages.length);
        Assertions.assertEquals("test", messages[0].getSubject());
        Assertions.assertEquals("test content", messages[0].getContent());
    }

    @AfterEach
    void clean() {
        smtpTestServer.stop();
    }
}