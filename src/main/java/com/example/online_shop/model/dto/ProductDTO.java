package com.example.online_shop.model.dto;

import java.sql.Timestamp;


public record ProductDTO(String productName, String description, Timestamp manufactureDate,String price) {
}
