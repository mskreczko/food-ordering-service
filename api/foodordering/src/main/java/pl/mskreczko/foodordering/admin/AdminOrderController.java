package pl.mskreczko.foodordering.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.foodordering.order.Order;
import pl.mskreczko.foodordering.order.OrderService;
import pl.mskreczko.foodordering.order.OrderStatus;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PatchMapping("/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long orderId, @RequestParam Integer status) {
        orderService.updateStatus(orderId, OrderStatus.values()[status]);
        return ResponseEntity.ok().build();
    }
}