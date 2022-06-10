package com.shoeshop.service.serviceImpl;

import com.shoeshop.entity.Address;
import com.shoeshop.repository.AddressRepository;
import com.shoeshop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<String> getAllCitys(){
        return  addressRepository.getAllCitys();
    }

    @Override
    public List<String> getAllDistrictsByCity(String city){
        return addressRepository.getAllDistrictsByCity(city);
    }

    @Override
    public List<Map<Long,String>> getAllWardsByCityAndDistrict(String city, String district){
        List<Address> list = addressRepository.findAddressesByCityAndAndDistrict(city,district);
        List<Map<Long,String>> mapList = new ArrayList<>();
        list.forEach(item->{
            Map<Long,String>  map = new HashMap<>();
            map.put(item.getId(), item.getWard());
            mapList.add(map);
        });
        return mapList;
    }

    @Override
    public Address getAddressById(long id){
        return addressRepository.findAddressById(id);
    }
}
