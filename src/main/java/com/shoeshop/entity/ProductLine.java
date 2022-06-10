package com.shoeshop.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_line")
@Data
public class ProductLine extends  BaseEntity{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Họ tên không được rỗng!")
    @Pattern(regexp = "^([a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+ {0,1})+$", message = "Tên không hợp lệ!")
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Transient
    private List<Product> products = new ArrayList<>();


}