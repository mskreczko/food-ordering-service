package pl.mskreczko.foodordering.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mskreczko.foodordering.exceptions.NoSuchEntityException;
import pl.mskreczko.foodordering.user.User;
import pl.mskreczko.foodordering.user.UserService;
import pl.mskreczko.foodordering.user.dto.AccountDetailsDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer/account")
public class CustomerAccountController {

    private final UserService userService;

    @GetMapping("{user_id}")
    public ResponseEntity<?> getAccountDetails(@PathVariable("user_id") Long userId) {
        try {
            User user = userService.loadUserById(userId);
            return ResponseEntity.ok(new AccountDetailsDto(user.getEmail(), user.getName(), user.getLoyaltyPoints()));
        } catch (NoSuchEntityException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
