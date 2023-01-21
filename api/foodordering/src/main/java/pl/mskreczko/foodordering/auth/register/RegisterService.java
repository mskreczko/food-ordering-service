package pl.mskreczko.foodordering.auth.register;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mskreczko.foodordering.user.UserService;

@RequiredArgsConstructor
@Service
public class RegisterService {

    private final UserService userService;

    public boolean registerUser(String email, String name, String password) {
        if (userService.doesUserExist(email)) {
            return false;
        }

        userService.saveUser(email, name, password);

        return true;
    }
}
