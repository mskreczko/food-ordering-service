package pl.mskreczko.foodordering.auth.register;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.foodordering.auth.register.dto.RegisterDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterDto credentials) {
        if (registerService.registerUser(credentials.email(), credentials.name(), credentials.password())) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
