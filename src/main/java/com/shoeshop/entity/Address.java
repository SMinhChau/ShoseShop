package com.shoeshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city", nullable = false, columnDefinition = "nvarchar(255)")
    private String city;

    @Column(name = "district", nullable = false, columnDefinition = "nvarchar(255)")
    private String district;

    @Column(name = "ward", nullable = false, columnDefinition = "nvarchar(255)")
    private String ward;

}