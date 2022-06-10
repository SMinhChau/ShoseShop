package com.shoeshop.service;

import com.shoeshop.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AddressService {

    List<String> getAllCitys();

    List<String> getAllDistrictsByCity(String city);

    List<Map<Long,String>> getAllWardsByCityAndDistrict(String city, String district);

    Address getAddressById(long id);
}
