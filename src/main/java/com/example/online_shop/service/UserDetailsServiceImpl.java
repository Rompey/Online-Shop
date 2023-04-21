package com.example.online_shop.service;

import com.example.online_shop.model.User;
import com.example.online_shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByLoginOptional(login);

        User user = optionalUser.orElseThrow(() -> new AccessDeniedException("User doesn't exist"));
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole().getName())));
    }
}
