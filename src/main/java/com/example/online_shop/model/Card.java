package com.example.online_shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@NamedEntityGraph(name = "card",
        attributeNodes = {@NamedAttributeNode("userId")})
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
    @Column(length = 16,
            unique = true)
    private String cardNumber;
    private Timestamp cardValidity;
    private boolean isExpired = Boolean.FALSE;
    private String cvv;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "users_cards",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> userId;
}
