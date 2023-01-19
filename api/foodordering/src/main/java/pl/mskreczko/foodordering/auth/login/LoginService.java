package pl.mskreczko.foodordering.auth.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mskreczko.foodordering.config.JwtAccessTokenProvider;
import pl.mskreczko.foodordering.user.role.Role;
import pl.mskreczko.foodordering.user.User;
import pl.mskreczko.foodordering.user.UserService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserService userService;
    private final JwtAccessTokenProvider jwtAccessTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean authenticateUser(String email, String password) throws UsernameNotFoundException {
        try {
            User user = (User) userService.loadUserByUsername(email);
            if (bCryptPasswordEncoder.matches(password, user.getPassword()))
                return true;
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("No user with such username");
        }

        return false;
    }

    public String getJwtToken(String email) {
        User user = (User) userService.loadUserByUsername(email);
        return jwtAccessTokenProvider.getAccessToken(user.getEmail(),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
    }
}
