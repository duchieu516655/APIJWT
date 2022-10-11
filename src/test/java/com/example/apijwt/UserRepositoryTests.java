package com.example.apijwt;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.apijwt.Role.Role;
import com.example.apijwt.user.User;
import com.example.apijwt.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("hieu1404");

        User newUser = new User(4, "hieu1234567@gmail.com", password);
        User savedUser = userRepository.save(newUser);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testAssignRoleToUser() {
        Integer userId = 1;
        User user = userRepository.findById(userId).get();
        user.addRole(new Role(3));


        User updatedUser = userRepository.save(user);
        assertThat(updatedUser.getRoles()).hasSize(2);

    }
}

