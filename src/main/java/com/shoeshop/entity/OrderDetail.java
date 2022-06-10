package com.shoeshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_detail")
@IdClass(OrderDetailPK.class)
@Data
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_size_id")
    private ProductSize productSize;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", nullable = false, columnDefinition = "real")
    private Double price;
}