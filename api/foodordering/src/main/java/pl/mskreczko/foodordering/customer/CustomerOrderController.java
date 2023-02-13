package pl.mskreczko.foodordering.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.foodordering.exceptions.NoSuchEntityException;
import pl.mskreczko.foodordering.order.Order;
import pl.mskreczko.foodordering.order.OrderService;
import pl.mskreczko.foodordering.order.dto.NewOrderDto;
import pl.mskreczko.foodordering.order.dto.OrderDetails;
import pl.mskreczko.foodordering.user.User;
import pl.mskreczko.foodordering.user.UserService;

import java.net.URI;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer/orders")
public class CustomerOrderController {

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("{order_id}")
    public ResponseEntity<?> getOrderDetails(@PathVariable("order_id") Long orderId) {
        Optional<Order> order = orderService.getOrderDetails(orderId);
        if (order.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        OrderDetails response = new OrderDetails(orderId, order.get().getDeliveryAddress(), order.get().getProducts(),
                order.get().getOrderStatus().name());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> makeOrder(@RequestBody NewOrderDto newOrder) {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = (User) userService.loadUserByUsername(email);
            Long orderId = orderService.createNewOrder(newOrder.productsIds(), user.getId(), newOrder.deliveryAddress());
            return ResponseEntity.created(URI.create("/customer/order/" + orderId)).build();
        } catch (NoSuchEntityException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
