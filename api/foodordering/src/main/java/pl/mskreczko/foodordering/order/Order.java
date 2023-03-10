package pl.mskreczko.foodordering.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import pl.mskreczko.foodordering.product.Product;
import pl.mskreczko.foodordering.user.User;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_user_id")
    private User customer;

    private String deliveryAddress;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    public void addProduct(Product product) {
        this.products.add(product);
        product.getOrders().add(this);
    }
}
