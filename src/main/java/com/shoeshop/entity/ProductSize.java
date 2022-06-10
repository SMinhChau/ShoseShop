package com.shoeshop.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "product_size")
@Data
public class ProductSize {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;


    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "status")
    private Boolean status;

}