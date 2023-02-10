package pl.mskreczko.foodordering.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import pl.mskreczko.foodordering.order.Order;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(unique = true)
    private String name;

    private Double price;

    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();

    public Product(String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
