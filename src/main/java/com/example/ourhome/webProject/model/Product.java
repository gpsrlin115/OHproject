package com.example.ourhome.webProject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
    private Long price;
    private int discount;
    @Column(name = "image_path")
    private String imagePath;

}
