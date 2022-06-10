package com.shoeshop.service;

import com.shoeshop.entity.admin.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AdminService extends UserDetailsService {
    User getUserByPhoneOrEmail(String username);

    List<User> getAllUser();

    void addUser(User user);

    boolean checkPhoneNotExist(String phone);

    boolean checkEmailNotExist(String email);

    List<User> searchlUserByPhone(String phone);

    User getUserById(long id);

    User getUserByPhone(String phone);

    void updateUser(User user);
}
