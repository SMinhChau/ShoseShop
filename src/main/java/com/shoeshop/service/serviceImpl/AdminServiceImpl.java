package com.shoeshop.service.serviceImpl;

import com.shoeshop.entity.admin.Role;
import com.shoeshop.entity.admin.User;
import com.shoeshop.repository.RoleRepository;
import com.shoeshop.repository.UserRepository;
import com.shoeshop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmailOrPhoneAndStatus(username,username,true);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> rules){
        return rules.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName())).collect(Collectors.toList());
    }

    @Override
    public User getUserByPhoneOrEmail(String username){
        return userRepository.findUserByEmailOrPhone(username,username);
    }

    @Override
    public List<User> getAllUser(){
        return userRepository.findUsersByRolesContaining(roleRepository.findRoleByName("EMPLOYEE"));
    }

    @Override
    public void addUser(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findRoleByName("EMPLOYEE");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public boolean checkPhoneNotExist(String phone) {
        if (userRepository.findUserByPhone(phone) == null)
            return true;
        return false;
    }

    @Override
    public boolean checkEmailNotExist(String email) {
        if (userRepository.findUserByEmail(email) == null)
            return true;
        return false;
    }

    @Override
    public List<User> searchlUserByPhone(String phone){
        List<User> list = new ArrayList<User>();
        User ur = userRepository.findUserByPhoneAndRolesContaining(phone, roleRepository.findRoleByName("EMPLOYEE"));
    	if(ur != null) {
    		list.add(ur);
    	}
        return list;
    }

    @Override
    public User getUserById(long id){
        return userRepository.findUserById(id);
    }

    @Override
    public User getUserByPhone(String phone){
        return userRepository.findUserByPhone(phone);
    }

    @Override
    public void updateUser(User user){
        userRepository.save(user);
    }
}
