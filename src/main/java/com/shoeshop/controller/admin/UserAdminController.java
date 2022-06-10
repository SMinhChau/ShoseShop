package com.shoeshop.controller.admin;

import com.shoeshop.dto.UserDto;
import com.shoeshop.entity.admin.User;
import com.shoeshop.service.AdminService;
import com.shoeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class UserAdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public String showPageUser(Model model, @RequestParam(name = "phone",defaultValue = "") String phone){
        if (phone.equalsIgnoreCase("")){
            model.addAttribute("users", adminService.getAllUser());
        }
        else{
            model.addAttribute("phone",phone);
            model.addAttribute("users", adminService.searchlUserByPhone(phone));
        }
        return "admin/user_admin";
    }

    @GetMapping("/user/add")
    public String showPageAddUser(Model model){
        model.addAttribute("user", new User());
        return "admin/user_add_admin";
    }

    @PostMapping("/user/add")
    public String addUser(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "admin/user_add_admin";
        }

        if (!adminService.checkEmailNotExist(user.getEmail())){
            model.addAttribute("message","Email này đã tồn tại!");
            return "admin/user_add_admin";
        }

        if (!adminService.checkPhoneNotExist(user.getPhone())){
            model.addAttribute("message","Số điện thoại này đã tồn tại!");
            return "admin/user_add_admin";
        }
        user.setStatus(true);
        adminService.addUser(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/user/edit/{id}")
   public String showPageUpdateUser(@PathVariable("id") long id,Model model){
        User user = adminService.getUserById(id);
        UserDto userDto = new UserDto();
        userDto.setFullName(user.getFullName());
        userDto.setPhone(user.getPhone());
        userDto.setEmail(user.getEmail());
        userDto.setStatus(user.isStatus());
        model.addAttribute("user", userDto);
        return "admin/user_update_admin";
   }

    @PostMapping("/user/edit")
    public String updateUser(Model model, @Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "admin/user_update_admin";
        }
        System.err.println(userDto);

        User user = adminService.getUserByPhone(userDto.getPhone());
        user.setFullName(userDto.getFullName());
        if (!user.getEmail().equalsIgnoreCase(userDto.getEmail())){
            if (!adminService.checkEmailNotExist(userDto.getEmail())){
                model.addAttribute("message","Email này đã tồn tại!");
                return "admin/user_add_admin";
            }else{
                user.setEmail(userDto.getEmail());
            }
        }
        if (!userDto.getPassword().equalsIgnoreCase("")){
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        }

        user.setStatus(userDto.isStatus());

        adminService.updateUser(user);

        return "redirect:/admin/users";
    }
}
