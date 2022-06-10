package com.shoeshop.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "promotion")
@Data
public class Promotion extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Họ tên không được rỗng!")
    @Pattern(regexp = "^([a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+ {0,1})+$", message = "Tên không hợp lệ!")
    @Column(name = "code", length = 50, nullable = false)
    private String code;

    @Column(name = "begin_date",nullable = false)
    private Date beginDate;

    @Column(name = "end_date",nullable = false)
    private Date endDate;

    @Column(name = "quantity",nullable = false)
    private int quantity;

    @Column(name = "percentile",nullable = false)
    private int percentile;

    @Column(name = "min_value", nullable = false)
    private double minValue;

    @Column(name = "max_value", nullable = false)
    private double maxValue;

    @Column(name = "total_code", nullable = false)
    private int totalCode;


}