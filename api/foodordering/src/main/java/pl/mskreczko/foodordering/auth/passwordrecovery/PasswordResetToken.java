package pl.mskreczko.foodordering.auth.passwordrecovery;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import pl.mskreczko.foodordering.user.User;

@NoArgsConstructor
@Entity
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name="user_id")
    User user;

    public PasswordResetToken(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
