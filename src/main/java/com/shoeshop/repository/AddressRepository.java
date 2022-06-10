package com.shoeshop.repository;

import com.shoeshop.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("select ar.city from Address ar group by ar.city")
    List<String> getAllCitys();

    @Query("select ar.district from Address ar where ar.city like :city group by ar.district")
    List<String> getAllDistrictsByCity(String city);

    List<Address> findAddressesByCityAndAndDistrict(String city, String district);

    Address findAddressById(Long id);
}
