package pl.mskreczko.foodordering.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @MockBean
    Authentication auth;

    @MockBean
    SecurityContext securityContext;

    @Autowired
    MockMvc mvc;

    @BeforeEach
    void setup() {
        auth = Mockito.mock(Authentication.class);
        securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void getAccountDetails_should_return_ok() throws Exception {
        Mockito.when(securityContext.getAuthentication().getName()).thenReturn("test@test.com");
        Mockito.when(userService.loadUserByUsername("test@test.com")).thenReturn(new User());

        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/customer/account/").with(user("customer").roles("CUSTOMER")))
                .andExpect(status().isOk());
    }

    // @Test
    // void getAccountDetails_should_return_not_found() throws Exception {
    //     Mockito.when(userService.loadUserByUsername("test@test.com")).thenThrow(new NoSuchEntityException("No user with given id"));

    //     mvc.perform(MockMvcRequestBuilders
    //             .get("/api/v1/customer/account/").with(user("customer").roles("CUSTOMER")))
    //             .andExpect(status().isNotFound());
    // }
}
