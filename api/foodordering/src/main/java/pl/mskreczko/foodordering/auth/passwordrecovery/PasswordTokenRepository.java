package pl.mskreczko.foodordering.auth.passwordrecovery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mskreczko.foodordering.user.User;

@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    @Query("SELECT u FROM User u WHERE u =(SELECT user FROM PasswordResetToken WHERE token=?1)")
    User findUserByPasswordToken(String token);

    PasswordResetToken findByToken(String token);
}
