package com.shoeshop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoeshop.service.OrderService;

@Controller
@RequestMapping("/admin/order")
public class OrderAdminController {
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public String showListOrder(){
        return "admin/order_admin";
    }

    @GetMapping("/detail/{id}")
    public String showOrderDetail(@PathVariable("id") long id, Model model){
        model.addAttribute("order", orderService.getOrderById(id));
        return "admin/order_detail_admin";
    }

}
