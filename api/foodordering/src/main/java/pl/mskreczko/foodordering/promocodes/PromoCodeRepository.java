package pl.mskreczko.foodordering.promocodes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {
    boolean existsByCode(String code);
    PromoCode findByCode(String code);
}
