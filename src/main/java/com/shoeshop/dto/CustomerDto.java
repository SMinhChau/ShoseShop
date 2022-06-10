package com.shoeshop.dto;

import com.shoeshop.entity.CustomerAddress;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerDto {
    private Long id;

    @NotEmpty(message = "Họ tên không được rỗng!")
    @Pattern(regexp = "^ *([a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+ *)+$", message = "Tên không hợp lệ!")
    private String fullName;

    @NotEmpty(message = "Email không được rỗng!")
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Email không hợp lệ!")
    private String email;

    private String phone;

    @Pattern(regexp = "^((.=?){0,0}||(.=?){6,})$" ,message = "Mật khẩu phải từ 8 kí tự trở lên")
    private String password;

    private String rePassword;

    private boolean status;
    private List<CustomerAddress> addresses = new ArrayList<>();
}
