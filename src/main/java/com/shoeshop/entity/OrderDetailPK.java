package com.shoeshop.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class OrderDetailPK implements Serializable {
    private long productSize;
    private long order;
}
