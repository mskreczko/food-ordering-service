package pl.mskreczko.foodordering.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mskreczko.foodordering.order.OrderService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;

    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok("Hello");
    }
}
