package pl.mskreczko.foodordering.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderStatus {

    @JsonProperty("AWAITS")
    AWAITS,
    @JsonProperty("IN PREPARATION")
    IN_PREPARATION,
    @JsonProperty("DELIVERING")
    DELIVERING,
    @JsonProperty("DELIVERED")
    DELIVERED
}
