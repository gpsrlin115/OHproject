package com.example.ourhome.webProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usernum_id")
    private Long id;

    @Column(name = "user_id")
    private String userid;

    @Column(unique = true)
    private String username;

    private String password;

    @Embedded
    private Address address;

    @OneToMany(mappedBy= "siteUser")
    private List<CartItem> cartItems = new ArrayList<>();

    @Column(unique = true)
    private String email;

    private String role;
}
