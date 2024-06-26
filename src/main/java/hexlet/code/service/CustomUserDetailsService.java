package hexlet.code.service;

import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public final class CustomUserDetailsService implements UserDetailsManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void createUser(UserDetails userDetails) {
        var user = new User();
        user.setEmail(userDetails.getUsername());
        var passwordDigest = passwordEncoder.encode(userDetails.getPassword());
        user.setPasswordDigest(passwordDigest);
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        throw new UnsupportedOperationException("updateUser");
    }

    @Override
    public void deleteUser(String email) {
        var user = userRepository.findByEmail(email).get();
        userRepository.deleteById(user.getId());
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException("changePassword");
    }

    @Override
    public boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
