package pl.mskreczko.foodordering.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OrderStatus {

    @JsonProperty("AWAITS")
    AWAITS,
    @JsonProperty("IN PREPARATION")
    IN_PREPARATION,
    @JsonProperty("BEING DELIVERED")
    BEING_DELIVERED,
    @JsonProperty("DELIVERED")
    DELIVERED
}
