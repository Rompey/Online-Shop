package com.example.online_shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public", name = "cards")
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String cardNumber;
    private Timestamp cardValidity;
    private boolean isExpired;
    private String cvv;
    @ManyToMany
    @JoinColumn(name = "users")
    private List<User> users;
}
