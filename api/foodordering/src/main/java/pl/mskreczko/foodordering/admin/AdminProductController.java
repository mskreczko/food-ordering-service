package pl.mskreczko.foodordering.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mskreczko.foodordering.exceptions.NoSuchEntityException;
import pl.mskreczko.foodordering.product.Product;
import pl.mskreczko.foodordering.product.ProductService;
import pl.mskreczko.foodordering.product.dto.NewProductDto;
import pl.mskreczko.foodordering.exceptions.AlreadyExistsException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/products")
public class AdminProductController {

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

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody NewProductDto newProductDto) {
        try {
            productService.createNewProduct(newProductDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (AlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestParam Long productId) {
        try {
            productService.getProductById(productId).ifPresentOrElse((p) -> productService.deleteById(p.getId()),
                    () -> { throw new NoSuchEntityException("Such product does not exist"); });
        } catch (NoSuchEntityException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok().build();
    }
}
