package com.shoeshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "size")
@Data
public class Size {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 10)
    private String name;

}