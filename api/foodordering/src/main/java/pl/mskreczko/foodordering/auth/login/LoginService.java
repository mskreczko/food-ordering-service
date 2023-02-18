package pl.mskreczko.foodordering.auth.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mskreczko.foodordering.auth.passwordrecovery.PasswordResetToken;
import pl.mskreczko.foodordering.auth.passwordrecovery.PasswordTokenRepository;
import pl.mskreczko.foodordering.config.JwtAccessTokenProvider;
import pl.mskreczko.foodordering.exceptions.NoSuchEntityException;
import pl.mskreczko.foodordering.user.role.Role;
import pl.mskreczko.foodordering.user.User;
import pl.mskreczko.foodordering.user.UserService;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserService userService;
    private final JwtAccessTokenProvider jwtAccessTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PasswordTokenRepository passwordTokenRepository;

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

    public String generatePasswordResetToken(String email) {
        if (!userService.doesUserExist(email)) {
            throw new NoSuchEntityException("User with such email does not exist");
        }

        String token = UUID.randomUUID().toString();
        User user = (User) userService.loadUserByUsername(email);
        PasswordResetToken passwordResetToken = new PasswordResetToken(user, token);
        passwordTokenRepository.save(passwordResetToken);
        return token;
    }
}
