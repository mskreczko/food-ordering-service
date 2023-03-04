package pl.mskreczko.foodordering.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.mskreczko.foodordering.exceptions.NoSuchEntityException;
import pl.mskreczko.foodordering.order.OrderService;
import pl.mskreczko.foodordering.order.OrderStatus;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdminOrderController.class)
public class AdminOrderControllerTest {

    @MockBean
    OrderService orderService;

    @Autowired
    MockMvc mvc;

    @Test
    void updateOrderStatus_should_return_ok() throws Exception {
        doNothing().when(orderService).updateStatus(1L, OrderStatus.IN_PREPARATION);

        mvc.perform(MockMvcRequestBuilders
                .patch("/api/v1/admin/orders/status/1?status=1").with(user("admin").roles("ADMIN")).with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void updateOrderStatus_should_return_not_found() throws Exception {
        doThrow(new NoSuchEntityException("order with given id not found")).when(orderService)
                .updateStatus(1L, OrderStatus.IN_PREPARATION);

        mvc.perform(MockMvcRequestBuilders
                .patch("/api/v1/admin/orders/status/1?status=1").with(user("admin").roles("ADMIN")).with(csrf()))
                .andExpect(status().isNotFound());
    }
}
