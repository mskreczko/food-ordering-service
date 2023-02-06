package pl.mskreczko.foodordering.customer.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mskreczko.foodordering.admin.product.Product;
import pl.mskreczko.foodordering.admin.product.ProductService;
import pl.mskreczko.foodordering.exceptions.NoSuchEntityException;
import pl.mskreczko.foodordering.user.User;
import pl.mskreczko.foodordering.user.UserRepository;
import pl.mskreczko.foodordering.user.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ProductService productService;

    @Mock
    UserService userService;

    @InjectMocks
    OrderService orderService;

    @Test
    void testGetOrderDetails_returnsOrder() {
        Order order = new Order();
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        var result = orderService.getOrderDetails(1L);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void testGetOrderDetails_returnsEmpty() {
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        var result = orderService.getOrderDetails(1L);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testCreateNewOrder_throws() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchEntityException.class,
                () -> orderService.createNewOrder(List.of(1L, 2L), 1L, "asdf")
        );
    }

    @Test
    void testCreateNewOrder_savesOrder() {
        User user = new User();
        Set<Product> products = new HashSet<>();

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(productService.getProductsByIds(List.of(1L, 2L))).thenReturn(products);

        orderService.createNewOrder(List.of(1L, 2L), 1L, "asdf");

        Mockito.verify(userRepository).findById(1L);
        Mockito.verify(productService).getProductsByIds(List.of(1L, 2L));
        Mockito.verify(userService).addLoyaltyPoints(user);
    }
}
