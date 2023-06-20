package com.example.online_shop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/login/**", "/registration/**", "/error/**").permitAll()
                .requestMatchers("/users/**", "/products/**", "/cards/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/adm/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic();
        return http.build();
    }
}
