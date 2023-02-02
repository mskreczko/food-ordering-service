package pl.mskreczko.foodordering.customer.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mskreczko.foodordering.admin.product.Product;
import pl.mskreczko.foodordering.admin.product.ProductService;
import pl.mskreczko.foodordering.exceptions.NoSuchEntityException;
import pl.mskreczko.foodordering.user.User;
import pl.mskreczko.foodordering.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final ProductService productService;

    public Optional<Order> getOrderDetails(Long orderId) {
        return orderRepository.findById(orderId);
    }


    @Transactional
    public void createNewOrder(List<Long> productsIds, Long userId, String deliveryAddress) throws NoSuchEntityException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NoSuchEntityException("User with specified id does not exist");
        }

        Order order = new Order();
        order.setDeliveryAddress(deliveryAddress);

        Set<Product> products = productService.getProductsByIds(productsIds);
        for (Product p : products) {
            order.addProduct(p);
        }

        order.setCustomer(user.get());
        orderRepository.save(order);
    }
}