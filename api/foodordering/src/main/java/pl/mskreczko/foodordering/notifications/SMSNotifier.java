package pl.mskreczko.foodordering.notifications;

import org.springframework.stereotype.Service;

@Service
public class SMSNotifier implements Notifier {
    @Override
    public void sendNotification(String target, String content) {

    }
}
