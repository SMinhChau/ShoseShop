package com.shoeshop.service.serviceImpl;

import com.shoeshop.entity.CustomerAddress;
import com.shoeshop.repository.CustomerAddressReponsitory;
import com.shoeshop.service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {
    @Autowired
    private CustomerAddressReponsitory customerAddressReponsitory;

    @Override
    public void addCustomerAddress(CustomerAddress customerAddress) {
        customerAddressReponsitory.save(customerAddress);
    }

    @Override
    public void updateCustomerAddress(CustomerAddress customerAddress) {
        customerAddressReponsitory.save(customerAddress);
    }

    @Override
    public void updateAllStatusCustomerAddressFalse(List<CustomerAddress> addresses) {
        addresses.forEach(item->{
            item.setStatus(false);
            customerAddressReponsitory.save(item);
        });
    }

    @Override
    public CustomerAddress getCustomerAddressById(long id){
        return customerAddressReponsitory.findCustomerAddressById(id);
    }
}
