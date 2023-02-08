package pl.mskreczko.foodordering.promocodes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "promo_codes")
public class PromoCode {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "promocode_id")
    private Long id;

    private String code;

    private Double discount; // percentage discount

    public PromoCode() {}
}
