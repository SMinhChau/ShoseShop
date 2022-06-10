package com.shoeshop.controller;

import com.shoeshop.entity.Customer;
import com.shoeshop.service.CustomerService;
import com.shoeshop.service.HomeService;
import com.shoeshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController extends BaseController{
    @Autowired
    private ProductService productService;
    @Autowired
    private  HomeService homeService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public ModelAndView Index(HttpSession session, Principal principal){
        _mvShare.addObject("productBestSells",productService.getProductBestSell());
        _mvShare.addObject("slideHomes",homeService.getDataSlide());
       _mvShare.setViewName("index");
       if (principal!=null){
           Customer user  = customerService.getCustomerByPhoneOrEmail(principal.getName());
           if (user!=null){
               if (!user.isStatus()){
                   SecurityContextHolder.clearContext();
                   _mvShare.addObject("message_login_index", "<script> Swal.fire(\"Tài khoản đã bị khóa!\",\"Vui lòng liên hệ shop để mở lại tài khoản!\",\"warning\"); </script>");
               }

           }
           setUserCurrentLogged(session);
       }else{
           _mvShare.addObject("message_login_index","");
       }

       return _mvShare;
    }

    @GetMapping("/403")
    public String showPage403(){
        return "errors/403";
    }

}
