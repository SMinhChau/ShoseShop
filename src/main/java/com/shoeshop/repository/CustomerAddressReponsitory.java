package com.shoeshop.repository;

import com.shoeshop.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressReponsitory extends JpaRepository<CustomerAddress,Long> {
    CustomerAddress findCustomerAddressById(long id);
}
