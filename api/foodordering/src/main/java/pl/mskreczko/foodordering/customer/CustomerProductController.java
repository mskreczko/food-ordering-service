package pl.mskreczko.foodordering.customer;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mskreczko.foodordering.product.Product;
import pl.mskreczko.foodordering.product.ProductService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer/products")
public class CustomerProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProducts(@RequestParam Optional<Integer> pageNumber) {
        Page<Product> pages = productService.findAllByPage(pageNumber);
        Map<String, Object> responseBody = new HashMap<>() {{
            put("products", pages.getContent());
            put("currentPage", pages.getNumber());
            put("totalItems", pages.getTotalElements());
            put("totalPages", pages.getTotalPages());
            put("itemsPerPage", 5);
        }};
        return ResponseEntity.ok(responseBody);
    }
}
