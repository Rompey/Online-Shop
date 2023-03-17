package com.example.online_shop.model.dto;

import java.util.Date;

public record TokenDTO(String token, Date expiredTime) {
}
