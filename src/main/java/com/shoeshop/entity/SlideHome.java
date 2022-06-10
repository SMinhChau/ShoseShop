package com.shoeshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "slide_home")
@Data
public class SlideHome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "url")
    private String url;

    
}