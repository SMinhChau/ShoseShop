package com.shoeshop.controller.rest;

import com.shoeshop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressRest {
    @Autowired
    private AddressService addressService;

    @GetMapping("/city")
    public List<String> getAllCitys(){
        return addressService.getAllCitys();
    }

    @GetMapping("/district")
    public List<String> getAllDistricts(@RequestParam String city){
        return addressService.getAllDistrictsByCity(city);
    }

    @GetMapping("/ward")
    public List<Map<Long,String>> getAllWards(@RequestParam String city, @RequestParam  String district){
        return addressService.getAllWardsByCityAndDistrict(city,district);
    }
}
