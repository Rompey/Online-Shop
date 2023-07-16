package com.example.online_shop.service;

import com.example.online_shop.model.Role;
import com.example.online_shop.model.User;
import com.example.online_shop.repository.RoleRepository;
import com.example.online_shop.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

//    @Test
//    void deleteUser() {
//        int id = 2;
//        String login = "user1@mail.com";
//        User user = User.builder()
//                .id(id)
//                .login(login)
//                .isDeleted(false)
//                .build();
//        userRepository.save(user);
//
//        userRepository.findUserByLoginOptional(login)
//                .orElseThrow(RuntimeException::new);
//
//        userRepository.deleteById(id);
//
//        Assertions.assertTrue(user.isDeleted());
//    }

    @Test
    void getUsers() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void saveUser() {
        String name = "User";
        String login = "user@mail.com";
        String password = "12345";
        Role role = new Role();
        role.setName("USER");
        roleRepository.save(role);

        User build = User.builder()
                .name(name)
                .login(login)
                .password(password)
                .role(role)
                .isDeleted(false)
                .build();
        userRepository.save(build);

        Optional<User> optionalUser = userRepository.findUserByLoginOptional(login);

        Assertions.assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        Assertions.assertEquals(name, user.getName());
        Assertions.assertEquals(login, user.getLogin());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals("USER", user.getRole().getName());
        Assertions.assertFalse(user.isDeleted());


    }

    @Test
    void getUserByNameAndLogin() {
    }
}