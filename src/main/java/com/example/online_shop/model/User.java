package com.example.online_shop.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Where;

import java.util.Locale;

@Entity
@NamedEntityGraph(name = "user",
        attributeNodes = {@NamedAttributeNode("role")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(schema = "public", name = "users")
@Builder
@Where(clause = "is_deleted = false")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true)
    private String login;
    private String password;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_roles")
    private Role role;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE;

    public void setLogin(String login) {
        this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
    }
}
