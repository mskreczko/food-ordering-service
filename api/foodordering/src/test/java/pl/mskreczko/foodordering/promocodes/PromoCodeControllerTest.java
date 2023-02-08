package pl.mskreczko.foodordering.promocodes;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PromoCodeController.class)
public class PromoCodeControllerTest {

    @MockBean
    private PromoCodeService promoCodeService;

    @Autowired
    MockMvc mvc;

    @Test
    void verifyCode_should_return_ok() throws Exception {
        Mockito.when(promoCodeService.verifyCode("ABC12D")).thenReturn(true);

        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/customer/orders/promocodes/verify?code=ABC12D").with(user("customer").roles("CUSTOMER")))
                .andExpect(status().isOk());
    }

    @Test
    void verifyCode_should_return_not_found() throws Exception {
        Mockito.when(promoCodeService.verifyCode("ABC12D")).thenReturn(false);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/customer/orders/promocodes/verify?code=ABC12D").with(user("customer").roles("CUSTOMER")))
                .andExpect(status().isNotFound());
    }
}
