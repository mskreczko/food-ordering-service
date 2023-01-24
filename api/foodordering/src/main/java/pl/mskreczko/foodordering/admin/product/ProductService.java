package pl.mskreczko.foodordering.admin.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.mskreczko.foodordering.admin.product.dto.NewProductDto;
import pl.mskreczko.foodordering.exceptions.AlreadyExistsException;

import java.util.Optional;

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
}
