package pl.mskreczko.foodordering.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.foodordering.exceptions.NoSuchEntityException;
import pl.mskreczko.foodordering.user.User;
import pl.mskreczko.foodordering.user.UserService;
import pl.mskreczko.foodordering.user.dto.AccountDetailsDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer/account")
public class CustomerAccountController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> getAccountDetails() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = (User) userService.loadUserByUsername(email);
            return ResponseEntity.ok(new AccountDetailsDto(user.getEmail(), user.getName(), user.getLoyaltyPoints()));
        } catch (NoSuchEntityException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
