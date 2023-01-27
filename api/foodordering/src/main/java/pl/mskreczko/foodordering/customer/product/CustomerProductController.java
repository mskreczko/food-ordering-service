package pl.mskreczko.foodordering.customer.product;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mskreczko.foodordering.admin.product.ProductController;
import pl.mskreczko.foodordering.admin.product.ProductService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer/menu")
public class CustomerProductController extends ProductController {

    public CustomerProductController(ProductService productService) {
        super(productService);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProducts(@RequestParam Optional<Integer> pageNumber) {
        return super.getAllProducts(pageNumber);
    }
}
