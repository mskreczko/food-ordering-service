package pl.mskreczko.foodordering.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.foodordering.order.Order;
import pl.mskreczko.foodordering.order.OrderService;
import pl.mskreczko.foodordering.order.dto.NewOrderDto;
import pl.mskreczko.foodordering.order.dto.OrderDetails;

import java.net.URI;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer/orders")
public class CustomerOrderController {

    private final OrderService orderService;

    @GetMapping("{order_id}")
    public ResponseEntity<?> getOrderDetails(@PathVariable("order_id") Long orderId) {
        Optional<Order> order = orderService.getOrderDetails(orderId);
        if (order.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        OrderDetails response = new OrderDetails(orderId, order.get().getDeliveryAddress(), order.get().getProducts());
        return ResponseEntity.ok(response);
    }

    @PostMapping("{customer_id}")
    public ResponseEntity<?> makeOrder(@RequestBody NewOrderDto newOrder, @PathVariable("customer_id") Long customerId) {
        Long orderId = orderService.createNewOrder(newOrder.productsIds(), customerId, newOrder.deliveryAddress());
        return ResponseEntity.created(URI.create("/api/v1/customer/orders/" + orderId)).build();
    }
}
