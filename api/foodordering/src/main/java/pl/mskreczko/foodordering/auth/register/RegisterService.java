package pl.mskreczko.foodordering.auth.register;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mskreczko.foodordering.notifications.EmailNotifier;
import pl.mskreczko.foodordering.user.UserService;

@RequiredArgsConstructor
@Service
public class RegisterService {

    private final UserService userService;

    private final EmailNotifier emailNotifier;

    public boolean registerUser(String email, String name, String password) {
        if (userService.doesUserExist(email)) {
            return false;
        }

        userService.saveUser(email, name, password);

        emailNotifier.sendNotification("mic.skreczko@gmail.com", "thank you for signing in");

        return true;
    }
}
