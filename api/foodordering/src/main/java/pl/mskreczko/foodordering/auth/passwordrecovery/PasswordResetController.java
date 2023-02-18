package pl.mskreczko.foodordering.auth.passwordrecovery;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.foodordering.user.User;
import pl.mskreczko.foodordering.user.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth/resetPassword")
public class PasswordResetController {

    private final PasswordTokenRepository passwordTokenRepository;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> reset(@RequestParam("token") String token, @RequestBody String newPassword) {
        User user = passwordTokenRepository.findUserByPasswordToken(token);
        userService.resetPassword(user, newPassword);
        passwordTokenRepository.delete(passwordTokenRepository.findByToken(token));
        return ResponseEntity.ok().build();
    }
}
