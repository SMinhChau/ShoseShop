package com.shoeshop.service.serviceImpl;

import com.shoeshop.entity.Customer;
import com.shoeshop.repository.CustomerRepository;
import com.shoeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user = customerRepository.findCustomerByEmailOrPhone(username,username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        String nameLogin = user.getEmail();
        if (user.getPhone().equalsIgnoreCase(username))
            nameLogin = user.getPhone();
        return new User(nameLogin,user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
    }

}
