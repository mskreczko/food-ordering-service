package pl.mskreczko.foodordering.admin.product;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.mskreczko.foodordering.admin.product.dto.NewProductDto;
import pl.mskreczko.foodordering.exceptions.AlreadyExistsException;

import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    MockMvc mvc;

    @Test
    void createNewProduct_should_return_conflict() throws Exception {
        doThrow(new AlreadyExistsException("product already exists")).when(
                productService).createNewProduct(new NewProductDto("food", 1.0, "test"));

        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/admin/menu").with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"food\", \"price\":  1.0, \"description\": \"test\"}").with(csrf()))
                .andExpect(status().isConflict());
    }

    @Test
    void createNewProduct_should_return_created() throws Exception {
        doNothing().when(productService).createNewProduct(new NewProductDto("food", 1.0, "test"));

        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/admin/menu").with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"food\", \"price\":  1.0, \"description\": \"test\"}").with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteProduct_should_return_bad_request() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/admin/menu?productId=").with(user("admin").roles("ADMIN")).with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteProduct_should_return_conflict() throws Exception {
        Mockito.when(productService.getProductById(1L)).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/admin/menu?productId=1").with(user("admin").roles("ADMIN")).with(csrf()))
                .andExpect(status().isConflict());
    }

    @Test
    void deleteProduct_should_return_ok() throws Exception {
        Product p = new Product("food", 1.0, "test");

        Mockito.when(productService.getProductById(1L)).thenReturn(Optional.of(p));
        doNothing().when(productService).deleteById(1L);

        mvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/admin/menu?productId=1").with(user("admin").roles("ADMIN")).with(csrf()))
                .andExpect(status().isOk());
    }
}
