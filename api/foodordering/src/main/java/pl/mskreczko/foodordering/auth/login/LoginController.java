package pl.mskreczko.foodordering.auth.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.foodordering.auth.login.dto.LoginDto;
import pl.mskreczko.foodordering.exceptions.NoSuchEntityException;
import pl.mskreczko.foodordering.notifications.EmailNotifier;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    private final LoginService loginService;
    private final EmailNotifier emailNotifier;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody LoginDto credentials) {
        try {
            if (!loginService.authenticateUser(credentials.email(), credentials.password())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(loginService.getJwtToken(credentials.email()));
    }

    @GetMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
        try {
            var token = loginService.generatePasswordResetToken(email);
            String resetUrl = "http://localhost:3000/resetPassword/" + token;
            // emailNotifier.sendNotification("email", resetUrl);
        } catch (NoSuchEntityException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
