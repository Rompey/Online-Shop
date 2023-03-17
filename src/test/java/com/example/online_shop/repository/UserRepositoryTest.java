package com.example.online_shop.repository;

import com.example.online_shop.model.Role;
import com.example.online_shop.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void testFindUserByLogin() {
        String login = "testuser";
        String name = "Test User";
        String password = "password";

        Role role = new Role();
        role.setName("USER");
        roleRepository.save(role);

        User user = User.builder()
                .login(login)
                .name(name)
                .password(password)
                .role(role)
                .build();

        userRepository.save(user);

        Optional<User> optionalUser = userRepository.findUserByLogin(login);

        Assertions.assertTrue(optionalUser.isPresent());
        User retrievedUser = optionalUser.get();
        Assertions.assertEquals(retrievedUser.getLogin(), login);
        Assertions.assertEquals(retrievedUser.getName(), name);
        Assertions.assertEquals(retrievedUser.getPassword(), password);
        Assertions.assertEquals(retrievedUser.getRole().getId(), role.getId());
    }
}