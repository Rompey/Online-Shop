package com.example.online_shop.model.dto;

import java.sql.Timestamp;
import java.util.List;

public record CardDTO(String name, String cardNumber, String cvv, Timestamp cardValidity, List<UserDTO> owner) {
}
