package com.example.online_shop.model.dto;

import lombok.Builder;

@Builder
public record UserRegistrationDTO(String name, String login, String password) {

}
