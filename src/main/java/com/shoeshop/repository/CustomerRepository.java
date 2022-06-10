package com.shoeshop.repository;

import com.shoeshop.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>, JpaRepository<Customer, Long> {
    Customer findCustomerByPhone(String phone);

    Customer findCustomerByEmail(String email);

    Customer findCustomerById(long id);

    Page<Customer> findAll(Pageable pageable);

    Customer findTopByOrderByIdDesc();

    Customer findCustomerByEmailOrPhone(String email, String phone);

    List<Customer> findAll();

    Customer findCustomerByResetPasswordToken(String token);

    @Query(nativeQuery = true, value = "select * from customer where phone like ?1%")
    List<Customer> findCustomersByPhone(String phone);

}
