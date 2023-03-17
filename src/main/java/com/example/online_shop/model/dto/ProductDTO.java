package com.example.online_shop.model.dto;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record ProductDTO(String productName, String description, Timestamp manufactureDate,String price) {
}
