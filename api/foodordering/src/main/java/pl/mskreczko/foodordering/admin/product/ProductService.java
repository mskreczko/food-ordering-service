package pl.mskreczko.foodordering.admin.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.mskreczko.foodordering.admin.product.dto.NewProductDto;
import pl.mskreczko.foodordering.exceptions.AlreadyExistsException;
import pl.mskreczko.foodordering.exceptions.NoSuchEntityException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> findAllByPage(Optional<Integer> pageNumber) {
        if (pageNumber.isEmpty()) {
            return productRepository.findAll(PageRequest.of(0, 5));
        }
        return productRepository.findAll(PageRequest.of(pageNumber.get(), 5));
    }

    public void createNewProduct(NewProductDto newProductDto) throws AlreadyExistsException {
        if (productRepository.existsByName(newProductDto.name())) {
            throw new AlreadyExistsException("Food item with such name already exists");
        }

        productRepository.save(Product.builder()
                .name(newProductDto.name())
                .description(newProductDto.description())
                .price(newProductDto.price())
                .build());
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public Set<Product> getProductsByIds(List<Long> ids) throws NoSuchEntityException {
        Set<Product> products = new HashSet<>();
        for (Long id : ids) {
            products.add(productRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("Product with specified id does not exist")));
        }

        return products;
    }

    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }
}
