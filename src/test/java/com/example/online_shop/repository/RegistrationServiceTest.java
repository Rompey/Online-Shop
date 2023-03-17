package com.example.online_shop.repository;

import com.example.online_shop.model.User;
import com.example.online_shop.model.dto.UserDTO;
import com.example.online_shop.model.dto.UserRegistrationDTO;
import com.example.online_shop.service.RegistrationService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class RegistrationServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RegistrationService userService;

    @Test
    @Transactional
    public void saveUserTest() {
        // Create a UserRegistrationDTO object with some dummy values
        UserRegistrationDTO userRegistrationDTO = UserRegistrationDTO.builder()
                .name("John Doe")
                .login("johndoe")
                .password("password")
                .build();

        // Call the saveUser method of the UserService
        UserDTO userDTO = userService.saveUser(userRegistrationDTO);

        // Retrieve the saved User object from the database using the TestEntityManager
        User savedUser = entityManager.find(User.class, userDTO.login());

        // Verify that the saved User object has the correct values
        Assertions.assertEquals(userRegistrationDTO.name(), savedUser.getName());
        Assertions.assertEquals(userRegistrationDTO.login(), savedUser.getLogin());
        Assertions.assertNotEquals(userRegistrationDTO.password(), savedUser.getPassword());
        Assertions.assertEquals(roleRepository.findRoleByName("USER").getId(), savedUser.getRole().getId());
    }
}