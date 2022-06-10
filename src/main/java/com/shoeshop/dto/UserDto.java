package com.shoeshop.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserDto {
    @NotEmpty(message = "Họ tên không được rỗng!")
    @Pattern(regexp = "^ *([a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+ *)+$", message = "Họ tên không hợp lệ!")
    private String fullName;

    @NotEmpty(message = "Số điện thoại không được rỗng!")
    @Pattern(regexp = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$",message = "Số điện thoại không hợp lệ!")
    private String phone;

    @NotEmpty(message = "Email không được rỗng!")
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Email không hợp lệ!")
    private String email;

    @Pattern(regexp = "^((.=?){0,0}||(.=?){6,})$" ,message = "Mật khẩu phải từ 8 kí tự trở lên")
    private String password;
    private boolean status;
}
