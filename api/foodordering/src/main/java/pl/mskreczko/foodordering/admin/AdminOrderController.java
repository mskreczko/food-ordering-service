package pl.mskreczko.foodordering.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.foodordering.order.Order;
import pl.mskreczko.foodordering.order.OrderService;
import pl.mskreczko.foodordering.order.OrderStatus;
import pl.mskreczko.foodordering.order.dto.OrderDetails;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PatchMapping("/status/{order_id}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable("order_id") Long orderId, @RequestParam Integer status) {
        orderService.updateStatus(orderId, OrderStatus.values()[status]);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{order_id}")
    public ResponseEntity<?> getOrderDetails(@PathVariable("order_id") Long orderId) {
        Optional<Order> orderOptional = orderService.getOrderDetails(orderId);
        if (orderOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        final var order = orderOptional.get();
        return ResponseEntity.ok(new OrderDetails(order.getId(), order.getDeliveryAddress(), order.getProducts(),
                order.getOrderStatus().name()));
    }
}
