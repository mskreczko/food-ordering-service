package pl.mskreczko.foodordering.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mskreczko.foodordering.product.Product;
import pl.mskreczko.foodordering.product.ProductService;
import pl.mskreczko.foodordering.exceptions.NoSuchEntityException;
import pl.mskreczko.foodordering.user.User;
import pl.mskreczko.foodordering.user.UserRepository;
import pl.mskreczko.foodordering.user.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final ProductService productService;
    private final UserService userService;

    public Optional<Order> getOrderDetails(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Transactional
    public Long createNewOrder(List<Long> productsIds, Long userId, String deliveryAddress) throws NoSuchEntityException {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchEntityException("User with specified id does not exist"));

        Order order = new Order();
        order.setDeliveryAddress(deliveryAddress);
        order.setOrderStatus(OrderStatus.AWAITS);

        Set<Product> products = productService.getProductsByIds(productsIds);
        for (Product p : products) {
            order.addProduct(p);
        }

        userService.addLoyaltyPoints(user);

        order.setCustomer(user);
        return orderRepository.save(order).getId();
    }

    public void updateStatus(Long orderId, OrderStatus orderStatus) throws NoSuchEntityException {
        var order = orderRepository.findById(orderId).orElseThrow(() -> new NoSuchEntityException(("No order with given id")));
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}