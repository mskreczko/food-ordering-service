package pl.mskreczko.foodordering.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.mskreczko.foodordering.User.UserRepository;
import pl.mskreczko.foodordering.User.UserService;
import pl.mskreczko.foodordering.User.exceptions.UserException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserService userService;

    @BeforeEach
    void setup() {
        this.userService = new UserService(this.userRepository, this.bCryptPasswordEncoder);
    }

    @Test
    void test_saveUser_should_throw() {
        Mockito.when(userRepository.existsByEmail("test@test.com")).thenReturn(true);

        Assertions.assertThrows(UserException.class,
                () -> userService.saveUser("test@test.com", "123"));
    }

    @Test
    void test_loadUserByUsername_should_throw() {
        Mockito.when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.empty());

        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("test@test.com"));
    }
}
