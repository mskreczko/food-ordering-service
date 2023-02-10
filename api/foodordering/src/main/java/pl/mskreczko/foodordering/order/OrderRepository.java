package pl.mskreczko.foodordering.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query("UPDATE Order o set o.orderStatus = ?2 where o.id = ?1")
    void updateStatus(Long orderId, Integer orderStatus);
}
