package pl.mskreczko.foodordering.customer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.mskreczko.foodordering.exceptions.NoSuchEntityException;
import pl.mskreczko.foodordering.user.User;
import pl.mskreczko.foodordering.user.UserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerAccountController.class)
public class CustomerAccountControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    MockMvc mvc;

    @Test
    void getAccountDetails_returnsDetails() throws Exception {
        Mockito.when(userService.loadUserById(1L)).thenReturn(new User());

        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/customer/account/1").with(user("customer").roles("CUSTOMER")))
                .andExpect(status().isOk());
    }

    @Test
    void getAccountDetails_returnsNotFound() throws Exception {
        Mockito.when(userService.loadUserById(1L)).thenThrow(new NoSuchEntityException("No user with given id"));

        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/customer/account/1").with(user("customer").roles("CUSTOMER")))
                .andExpect(status().isNotFound());
    }
}
