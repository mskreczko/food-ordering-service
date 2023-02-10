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
    public void createNewOrder(List<Long> productsIds, Long userId, String deliveryAddress) throws NoSuchEntityException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new NoSuchEntityException("User with specified id does not exist");
        }

        User user = userOptional.get();

        Order order = new Order();
        order.setDeliveryAddress(deliveryAddress);

        Set<Product> products = productService.getProductsByIds(productsIds);
        for (Product p : products) {
            order.addProduct(p);
        }

        userService.addLoyaltyPoints(user);

        order.setCustomer(user);
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}