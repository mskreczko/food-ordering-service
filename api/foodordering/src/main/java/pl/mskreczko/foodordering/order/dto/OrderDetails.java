package pl.mskreczko.foodordering.order.dto;

import pl.mskreczko.foodordering.product.Product;

import java.util.Set;

public record OrderDetails(Long id, String deliveryAddress, Set<Product> products, String status) {
}
