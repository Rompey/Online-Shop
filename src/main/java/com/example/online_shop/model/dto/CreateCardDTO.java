package com.example.online_shop.model.dto;

public record CreateCardDTO(String name, String cardNumber, String cvv, UserDTO userId) {
}
