package com.example.online_shop.model.dto;

import java.sql.Timestamp;

public record CardDTO(String name, String cardNumber, Timestamp cardValidity, String cvv, boolean isExpired) {
}
