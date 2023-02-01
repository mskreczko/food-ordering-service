package pl.mskreczko.foodordering.customer.order;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {

    @MockBean
    private OrderService orderService;

    @Autowired
    MockMvc mvc;

    @Test
    void getOrderDetails_should_return_ok() throws Exception {
        Order o = Order.builder().id(1L).build();
        Mockito.when(orderService.getOrderDetails(1L)).thenReturn(Optional.of(o));

        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/customer/orders/1").with(user("customer").roles("CUSTOMER")))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderDetails_should_return_not_found() throws Exception {
        Mockito.when(orderService.getOrderDetails(1L)).thenReturn(Optional.empty());


        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/customer/orders/1").with(user("customer").roles("CUSTOMER")))
                .andExpect(status().isNotFound());
    }
}
