package com.shoeshop.service;

import com.shoeshop.dto.CustomerDto;
import com.shoeshop.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    boolean checkPhoneNotExist(String phone);

    boolean checkEmailNotExist(String email);

    boolean addCustomer(Customer customer);

    Page<Customer> getCustomersNews(Pageable pageable);

    Customer findCustomerById(long id);

    Customer findLastProduct();

    Customer getCustomerByPhoneOrEmail(String username);

    CustomerDto getCustomerCurrent(String username);

    Customer getCustomerById(long id);

    List<Customer> findAll();

    Customer updateCustomer(Customer customer);

    void updateResetPasswordToken(String token, String email) throws Exception;

    Customer getByResetPasswordToken(String token);

    void updatePassword(Customer customer, String newPassword);

    List<Customer> searchCustomerByPhone(String phone);
}
