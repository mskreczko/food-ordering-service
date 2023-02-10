package pl.mskreczko.foodordering.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mskreczko.foodordering.product.dto.NewProductDto;
import pl.mskreczko.foodordering.exceptions.AlreadyExistsException;
import pl.mskreczko.foodordering.exceptions.NoSuchEntityException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest{

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    void getProductsByIds_throwsException() {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchEntityException.class,
                () -> productService.getProductsByIds(List.of(1L, 2L)));
    }

    @Test
    void getProductsByIds_returnsProducts() {
        Product p1 = new Product();
        Product p2 = new Product();
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(p1));
        Mockito.when(productRepository.findById(2L)).thenReturn(Optional.of(p2));

        Set<Product> products =  productService.getProductsByIds(List.of(1L, 2L));

        Assertions.assertEquals(2, products.size());
    }

    @Test
    void createNewProduct_throwsException() {
        Mockito.when(productRepository.existsByName("soup")).thenReturn(true);
        Assertions.assertThrows(AlreadyExistsException.class,
                () -> productService.createNewProduct(new NewProductDto("soup", 15., "chicken soup")));
    }
}
