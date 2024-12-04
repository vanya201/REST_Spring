package com.example.REST_Spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;


    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE )
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER, cascade =  CascadeType.REMOVE )
    private Set<Shop> shops = new HashSet<>();

}
