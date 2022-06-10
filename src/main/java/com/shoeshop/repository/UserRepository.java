package com.shoeshop.repository;

import com.shoeshop.entity.admin.Role;
import com.shoeshop.entity.admin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmailOrPhone(String email,String phone);
    User findUserByEmailOrPhoneAndStatus(String email,String phone,boolean status);
    List<User> findAll();
    List<User> findUsersByRolesContaining(Role role);

    User findUserByEmail(String email);
    User findUserByPhone(String phone);
    User findUserByPhoneAndRolesContaining(String phone, Role role);
    User findUserById(long id);

    @Query(nativeQuery = true,value = "select * from users where phone like ?1%")
    List<User> findUsersByPhoneStartingWith(String phone);
}
