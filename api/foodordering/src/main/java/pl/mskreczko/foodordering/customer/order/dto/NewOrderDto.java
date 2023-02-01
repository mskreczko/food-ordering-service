package pl.mskreczko.foodordering.customer.order.dto;

import pl.mskreczko.foodordering.admin.product.Product;

import java.util.List;
import java.util.Set;

public record NewOrderDto(List<Long> productsIds, String deliveryAddress) {
}
