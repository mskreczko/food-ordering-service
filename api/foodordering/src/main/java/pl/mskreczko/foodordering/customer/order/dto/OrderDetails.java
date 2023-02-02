package pl.mskreczko.foodordering.customer.order.dto;

import pl.mskreczko.foodordering.admin.product.Product;

import java.util.Set;

public record OrderDetails(Long id, String deliveryAddress, Set<Product> products) {
}
