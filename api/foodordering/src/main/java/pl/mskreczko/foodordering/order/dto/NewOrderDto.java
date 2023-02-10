package pl.mskreczko.foodordering.order.dto;

import java.util.List;

public record NewOrderDto(List<Long> productsIds, String deliveryAddress) {
}
