package com.example.online_shop.model.dto;

import java.sql.Timestamp;

public record CreateCardDTO(String name, String cardNumber, String cvv, Timestamp cardValidity, UserRegistrationDTO usersId) {
}
