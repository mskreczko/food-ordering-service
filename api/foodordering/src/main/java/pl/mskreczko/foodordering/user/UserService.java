package pl.mskreczko.foodordering.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mskreczko.foodordering.user.role.RoleRepository;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUser(String email, String name, String password) {
        User u = new User(email, name, bCryptPasswordEncoder.encode(password));
        u.getRoles().add(roleRepository.findByName("ROLE_CUSTOMER"));
        userRepository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
    }

    public boolean doesUserExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public void addLoyaltyPoints(User user) {
        final int points = 10;
        user.setLoyaltyPoints(user.getLoyaltyPoints() + points);
        userRepository.save(user);
    }
}
