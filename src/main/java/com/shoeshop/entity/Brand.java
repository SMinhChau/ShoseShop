package com.shoeshop.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brand")
@Data
public class Brand extends  BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Họ tên không được rỗng!")
    @Pattern(regexp = "^([a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+ {0,1})+$", message = "Tên không hợp lệ!")
    @Column(nullable = false)
    private String name;

    @Column(name = "image", nullable = false)
    private String image;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "brand", orphanRemoval = true)
    private List<ProductLine> productLines = new ArrayList<>();

    
}