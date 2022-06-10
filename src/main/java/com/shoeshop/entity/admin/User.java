package com.shoeshop.entity.admin;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email","phone"}))
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Họ tên không được rỗng!")
    @Pattern(regexp = "^ *([a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+ *)+$", message = "Họ tên không hợp lệ!")
    @Column(name = "full_name", columnDefinition = "nvarchar(255)",nullable = false)
    private String fullName;

    @NotEmpty(message = "Số điện thoại không được rỗng!")
    @Pattern(regexp = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$",message = "Số điện thoại không hợp lệ!")
    @Column(nullable = false,unique = true)
    private String phone;

    @NotEmpty(message = "Email không được rỗng!")
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Email không hợp lệ!")
    @Column(nullable = false,unique = true)
    private String email;

    @Size(min = 8,message = "Mật khẩu phải từ 8 kí tự trở lên")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean status;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"
            )

    )
    private Collection<Role> roles;

}
