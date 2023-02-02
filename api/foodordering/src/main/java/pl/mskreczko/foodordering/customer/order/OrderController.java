package pl.mskreczko.foodordering.customer.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.foodordering.customer.order.dto.NewOrderDto;
import pl.mskreczko.foodordering.customer.order.dto.OrderDetails;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer/orders")
public class OrderController {

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
        orderService.createNewOrder(newOrder.productsIds(), customerId, newOrder.deliveryAddress());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
