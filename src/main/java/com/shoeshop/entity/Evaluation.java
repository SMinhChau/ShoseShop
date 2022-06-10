package com.shoeshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "evaluation")
@Data
@IdClass(EvaluationPK.class)
public class Evaluation implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "rate", nullable = false)
    private Integer rate;

    @Column(name = "comment", columnDefinition = "nvarchar(255)")
    private String comment;

}