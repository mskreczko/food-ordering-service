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
import pl.mskreczko.foodordering.exceptions.NoSuchEntityException;
import pl.mskreczko.foodordering.user.role.RoleRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private RoleRepository roleRepository;

    private UserService userService;

    @BeforeEach
    void setup() {
        this.userService = new UserService(this.userRepository, this.roleRepository, this.bCryptPasswordEncoder);
    }

    @Test
    void loadUserByUsername_should_throw() {
        Mockito.when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.empty());

        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("test@test.com"));
    }

    @Test
    void loadUserById_should_throw() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchEntityException.class,
                () -> userService.loadUserById(1L));
    }
}
