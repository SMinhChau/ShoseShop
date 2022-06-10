package com.shoeshop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag")
@Data
@NoArgsConstructor
public class Tag extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", columnDefinition = "nvarchar(255)", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_tag",
            joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<Product> products = new ArrayList<>();

    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}