package com.shoeshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.*;

@Entity
@Table(name = "product")
@Data
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @NotEmpty(message = "Tên sản phẩm không được rỗng!")
    @Column(name = "name", nullable = false, columnDefinition = "nvarchar(255)")
    private String name;
    
    @DecimalMin(value = "0.0", message = "Giá > 0")
    @NotNull(message = "Giá không được rỗng!")
    @Column(name = "price", nullable = false, columnDefinition = "real")
    private Double price;
    
    @DecimalMin(value = "0.0", message = "Giá cũ > 0")
    @NotNull(message = "Giá cũ không được rỗng!")
    @Column(name = "promotion_price", nullable = false, columnDefinition = "real")
    private Double promotionPrice;

    @Column(name = "avatar")
    private String avatar;

    @JsonIgnore
    @Column(name = "url", nullable = false)
    @ElementCollection
    @JoinTable(name = "product_image", joinColumns = @JoinColumn(name = "product_id"))
    private List<String> images;

//    @NotEmpty(message = "Dòng sản phẩm không được rỗng!")
    @JsonIgnore
    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "product_line_id")
    private ProductLine productLine;

//    @NotEmpty(message = "Danh mục sản phẩm không được rỗng")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "status")
    private Boolean status;

    @JsonIgnore
    @Transient
    private List<ProductSize> productSizes = new ArrayList<>();

    @JsonIgnore
    @Transient
    private Set<Tag> tags = new HashSet<>();

}