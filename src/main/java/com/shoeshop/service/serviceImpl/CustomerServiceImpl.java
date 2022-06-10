package com.shoeshop.service.serviceImpl;

import com.shoeshop.dto.CustomerDto;
import com.shoeshop.entity.Customer;
import com.shoeshop.repository.CustomerRepository;
import com.shoeshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean checkPhoneNotExist(String phone) {
        if (customerRepository.findCustomerByPhone(phone) == null)
            return true;
        return false;
    }

    @Override
    public boolean checkEmailNotExist(String email) {
        if (customerRepository.findCustomerByEmail(email) == null)
            return true;
        return false;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        if (customerRepository.save(customer) != null)
            return true;
        return false;
    }

    @Override
    public Page<Customer> getCustomersNews(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer findCustomerById(long id) {
        Customer customer = customerRepository.findCustomerById(id);
        return customer;
    }

    @Override
    public Customer findLastProduct() {
        return customerRepository.findTopByOrderByIdDesc();
    }

    @Override
    public Customer getCustomerByPhoneOrEmail(String username) {
        return customerRepository.findCustomerByEmailOrPhone(username, username);
    }

    @Override
    public CustomerDto getCustomerCurrent(String username) {
        Customer customer = customerRepository.findCustomerByEmailOrPhone(username, username);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFullName(customer.getFullName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhone(customer.getPhone());
        customerDto.setAddresses(customer.getAddresses());
        return customerDto;
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerRepository.findCustomerById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void updateResetPasswordToken(String token, String email) throws Exception {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer != null) {
            customer.setResetPasswordToken(token);
            customerRepository.save(customer);
        } else {
            throw new Exception("Could not find any customer with the email " + email);
        }
    }

    @Override
    public Customer getByResetPasswordToken(String token) {
        return customerRepository.findCustomerByResetPasswordToken(token);
    }

    @Override
    public void updatePassword(Customer customer, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        customer.setPassword(encodedPassword);
        customer.setResetPasswordToken(null);
        customerRepository.save(customer);
    }

    public List<Customer> searchCustomerByPhone(String phone) {
        return customerRepository.findCustomersByPhone(phone);
    }

}
