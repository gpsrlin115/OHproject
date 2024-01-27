package com.example.ourhome.webProject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
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
}
