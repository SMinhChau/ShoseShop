package com.shoeshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customer_address")
@Data
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "detail", columnDefinition = "nvarchar(255)", nullable = false)
    private String detail;

    @Column(name = "status")
    private boolean status;
}