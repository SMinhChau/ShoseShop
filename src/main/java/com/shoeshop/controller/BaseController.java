package com.shoeshop.controller;

import com.shoeshop.entity.Customer;
import com.shoeshop.entity.Order;
import com.shoeshop.entity.admin.User;
import com.shoeshop.repository.OrderRepository;
import com.shoeshop.service.AdminService;
import com.shoeshop.service.BrandService;
import com.shoeshop.service.CustomerService;
import com.shoeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {
    @Autowired
    private  BrandService brandService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminService adminService;

    public ModelAndView ors = new ModelAndView();
    public ModelAndView _mvShare = new ModelAndView();
    
    @PostConstruct
    public ModelAndView Init(){
        _mvShare.addObject("brands",brandService.getAllBrandAndListProduct());
        return  _mvShare;
    }

    public void setUserCurrentLogged(HttpSession session){
        if (SecurityContextHolder.getContext().getAuthentication()!=null){
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails)principal).getUsername();
                Customer customer = customerService.getCustomerByPhoneOrEmail(username);
                if (customer!=null){
                        session.setAttribute("fullName",customer.getFullName());
                        session.setAttribute("phone",customer.getPhone());
                        session.setAttribute("email",customer.getEmail());
                }else{
                   User user  = adminService.getUserByPhoneOrEmail(username);
                    if (user!=null) {
                        session.setAttribute("fullName", user.getFullName());
                        session.setAttribute("phone", user.getPhone());
                        session.setAttribute("email", user.getEmail());
                    }
                }
            }
        }
    }

}
