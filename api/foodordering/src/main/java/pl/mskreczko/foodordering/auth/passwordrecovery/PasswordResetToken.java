package pl.mskreczko.foodordering.auth.passwordrecovery;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import pl.mskreczko.foodordering.user.User;

import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
public class PasswordResetToken {

    private static final int EXPIRATION = 3;

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
        this.expiration = LocalDateTime.now().plusMinutes(EXPIRATION);
    }

    LocalDateTime expiration;
}
