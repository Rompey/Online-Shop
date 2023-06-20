package com.example.online_shop.service;

import com.example.online_shop.mappers.UserMapper;
import com.example.online_shop.model.User;
import com.example.online_shop.model.dto.UserDTO;
import com.example.online_shop.model.dto.UserRegistrationDTO;
import com.example.online_shop.repository.RoleRepository;
import com.example.online_shop.repository.UserRepository;
import com.example.online_shop.utils.ArgonUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public void deleteUser(String login) {
        User user = userRepository.findUserByLoginOptional(login)
                .orElseThrow(() -> new NotFoundException("This login doesn't exist"));
        userRepository.deleteById(user.getId());
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    public UserDTO updateUser(UserRegistrationDTO userDTO, String login) {
        return userRepository.findUserByLoginOptional(login)
                .map(user -> getUserDTO(userDTO, user)).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public UserDTO saveUser(UserRegistrationDTO userRegistrationDTO) {
        User save = userRepository.save(buildUser(userRegistrationDTO));

        return getUserDTO(save);
    }

    protected User getUserByNameAndLogin(String name, String login){
        return userRepository.findUserByNameAndLogin(name, login);
    }

    private UserDTO getUserDTO(User save) {
        return new UserDTO(save.getName(), save.getLogin());
    }

    private User buildUser(UserRegistrationDTO userDTO) {
        return User.builder()
                .name(userDTO.name())
                .login(userDTO.login())
                .password(ArgonUtil.hashPassword(userDTO.password()))
                .role(roleRepository.findRoleByName("USER"))
                .build();
    }

    private UserDTO getUserDTO(UserRegistrationDTO userDTO, User user) {
        user.setName(userDTO.name());
        user.setLogin(userDTO.login());
        user.setPassword(userDTO.password());
        User save = userRepository.save(user);
        return UserMapper.USER_MAPPER.map(save);
    }
}
