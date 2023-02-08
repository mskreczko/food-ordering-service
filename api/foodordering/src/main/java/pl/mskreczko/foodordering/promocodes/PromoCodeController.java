package pl.mskreczko.foodordering.promocodes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer/orders/promocodes")
public class PromoCodeController {

    private final PromoCodeService promoCodeService;

    @GetMapping("verify")
    public ResponseEntity<?> verify(@RequestParam String code) {
        if (promoCodeService.verifyCode(code)) {
            return ResponseEntity.ok(promoCodeService.getDiscount(code));
        }

        return ResponseEntity.notFound().build();
    }
}