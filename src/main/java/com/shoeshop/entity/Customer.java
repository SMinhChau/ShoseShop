package com.shoeshop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Họ tên không được rỗng!")
    @Pattern(regexp = "^ *([a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+ *)+$", message = "Tên không hợp lệ!")
    @Column(name = "fullname",columnDefinition = "nvarchar(255)",nullable = false)
    private String fullName;

   @NotEmpty(message = "Email không được rỗng!")
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",message = "Email không hợp lệ!")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "Số điện thoại không được rỗng!")
    @Pattern(regexp = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$",message = "Số điện thoại không hợp lệ!")
    @Column(name = "phone", nullable = false, unique = true, length = 20)
    private String phone;

    @Size(min = 8,message = "Mật khẩu phải từ 8 kí tự trở lên")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_date", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Transient
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<CustomerAddress> addresses = new ArrayList<>();

    @Column(name = "reset_password_token", columnDefinition = "varchar(30)")
    private String resetPasswordToken;

}