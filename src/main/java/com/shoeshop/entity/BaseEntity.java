package com.shoeshop.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "createdBy", columnDefinition = "nvarchar(255)")
    private String createdBy;

    @Column(name = "modifiedDate")
    private Date modifiedDate;

    @Column(name = "modifiedBy", columnDefinition = "nvarchar(255)")
    private String modifiedBy;

}