package com.shoeshop.service;

import com.shoeshop.entity.CustomerAddress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerAddressService {
    void addCustomerAddress(CustomerAddress customerAddress);

    void updateCustomerAddress(CustomerAddress customerAddress);

    void updateAllStatusCustomerAddressFalse(List<CustomerAddress> addresses);

    CustomerAddress getCustomerAddressById(long id);

}
