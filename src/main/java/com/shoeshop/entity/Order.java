package com.shoeshop.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "status", columnDefinition = "nvarchar(255)", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "customer_address_id")
    private CustomerAddress shipAddress;

    @Column(name = "note", columnDefinition = "nvarchar(255)")
    private String note;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public double calculateTotal() {
        double total = 0;
        for (OrderDetail item : orderDetails) {
            total = total + (item.getPrice() * item.getQuantity());
        }
        return total;
    }

    public double calculateDiscount() {
        if (promotion == null) {
            return 0;
        }
        double discount = this.calculateTotal() * 1.0 * promotion.getPercentile() / 100;
        if (discount >= promotion.getMaxValue()) {
            discount = promotion.getMaxValue();
        }
        return discount;
    }

    public double calculateAmount() {
        return this.calculateTotal() - this.calculateDiscount();
    }

    public String generateCode() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YY");
        String code = "HD" + dateFormat.format(this.createdDate);
        code = code.replaceAll("-", "") + "N" + this.id;
        return code;
    }
}