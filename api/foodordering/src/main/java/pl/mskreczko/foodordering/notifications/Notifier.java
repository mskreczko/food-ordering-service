package pl.mskreczko.foodordering.notifications;

public interface Notifier {
    void sendNotification(String target, String content);
}
