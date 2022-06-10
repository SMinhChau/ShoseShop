package com.shoeshop.controller.admin;

import com.shoeshop.controller.BaseController;
import com.shoeshop.entity.Customer;
import com.shoeshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CustomerAdminController extends BaseController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/add")
    public ModelAndView loadAddCustomers() {
        _mvShare.addObject("customer", new Customer());
        _mvShare.setViewName("admin/customers_admin");
        return _mvShare;
    }
    @PostMapping("/customer")
    public String addCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "admin/customers_admin";
        }
        Date createDate= new Date();
        customer.setCreatedDate(createDate);
        customer.setStatus(true);
        return "redirect:customer/add";
    }
    @GetMapping("/customers")
    public ModelAndView getListCustomers(@RequestParam(value = "phone", required = false)String phone){
        List<Customer> listCustomers ;
        System.err.println(phone);
        if(phone == null ||  phone.equalsIgnoreCase("")){
            listCustomers= customerService.findAll();
        }
        else{
            listCustomers= customerService.searchCustomerByPhone(String.valueOf(phone));
            _mvShare.addObject("phone",phone);
        }
        _mvShare.addObject("customers",listCustomers);
        _mvShare.setViewName("admin/customers_admin");
        return  _mvShare;
    }

    @GetMapping("/customer/{id}")
    public ModelAndView oneCusromer(@PathVariable("id") Long id) {
        Customer customer= customerService.findCustomerById(id);
        _mvShare.addObject("customer", customer);
        _mvShare.setViewName("admin/customers_edit_admin");
        return _mvShare;
    }

    @PostMapping("/customer/{id}")
    public String updateCustomer(@RequestParam("status") boolean status, @PathVariable("id") Long id) {
        Customer customer =  customerService.findCustomerById(id);
        customer.setStatus(status);
        customerService.updateCustomer(customer );
        System.err.println(id);
        System.err.println(status);
        return "redirect:/admin/customers";
    }


}
