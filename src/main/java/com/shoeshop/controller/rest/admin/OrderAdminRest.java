package com.shoeshop.controller.rest.admin;

import com.shoeshop.dto.OrderAdminDto;
import com.shoeshop.entity.*;
import com.shoeshop.service.OrderService;
import com.shoeshop.service.ProductService;
import com.shoeshop.service.ProductSizeService;
import com.shoeshop.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin/api/order")
public class OrderAdminRest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductSizeService productSizeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PromotionService promotionService;

    @GetMapping("/search")
    public Page<OrderAdminDto> searchOrder(@RequestParam("phone") String phone, @RequestParam("name") String name,
            @RequestParam("status") String status,
            @RequestParam("from") String from, @RequestParam("to") String to,
            @RequestParam(value = "page") int page) {
        Page<OrderAdminDto> orderAdminDtoPage;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateFrom = dateFormat.parse(from);
            Date dateTo = dateFormat.parse(to);
            dateTo = new Date(dateTo.getTime() + 60 * 60 * 24 * 1000);

            Pageable pageable = PageRequest.of(page - 1, 10);
            Pageable pageable1 = PageRequest.of(page - 1, 1);
            if (!phone.trim().equalsIgnoreCase("")) {
                Page<Order> orderPage = orderService.findOrdersByCustomerPhone(phone, pageable);
                orderAdminDtoPage = new PageImpl<>(convertToOrderAdminDto(orderPage.getContent()), pageable1,
                        orderPage.getTotalPages());
                return orderAdminDtoPage;
            } else {
                if (status.equalsIgnoreCase("Tất cả")) {
                    status = "";
                }
                Page<Order> orderPage = orderService.findOrdersByStatusAndCreatedDateBetweenAndCustomerName(name,
                        status, dateFrom, dateTo, pageable);
                orderAdminDtoPage = new PageImpl<>(convertToOrderAdminDto(orderPage.getContent()), pageable1,
                        orderPage.getTotalPages());
                return orderAdminDtoPage;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<OrderAdminDto> convertToOrderAdminDto(List<Order> orders) {
        List<OrderAdminDto> orderAdminDtoList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        orders.forEach(item -> {
            OrderAdminDto orderAdminDto = new OrderAdminDto();
            orderAdminDto.setId(item.getId());
            orderAdminDto.setCode(item.generateCode());
            orderAdminDto.setCreatedDate(dateFormat.format(item.getCreatedDate()));
            orderAdminDto.setCustomerPhone(item.getCustomer().getPhone());
            orderAdminDto.setCustomerEmail(item.getCustomer().getEmail());
            orderAdminDto.setCustomerName(item.getCustomer().getFullName());
            orderAdminDto.setStatus(item.getStatus());
            orderAdminDto.setAmount(item.calculateAmount());
            orderAdminDtoList.add(orderAdminDto);
        });
        return orderAdminDtoList;
    }

    @GetMapping("/status")
    public List<String> getOrderStatusUpdate(@RequestParam("id") long id) {
        Order order = orderService.getOrderById(id);
        String currentStatus = order.getStatus();
        List<String> statuss = new ArrayList<>();
        switch (currentStatus) {
            case "Đang xử lý":
                statuss.add("Đang giao hàng");
                statuss.add("Đã hủy");
                break;
            case "Đang giao hàng":
                statuss.add("Giao hàng thất bại");
                statuss.add("Đã thanh toán");
                break;
            case "Giao hàng thất bại":
                statuss.add("Đã hủy");
                break;
        }
        return statuss;
    }

    @PutMapping(value = "/status/{id}")
    public boolean updateOrderStatus(@PathVariable("id") long id, @RequestBody String statusUrl) {

        String status = URLDecoder.decode(statusUrl, StandardCharsets.UTF_8);
        status = status.replace("status=", "");
        Order order = orderService.getOrderById(id);
        String currentStatus = order.getStatus();
        System.err.println(status);
        switch (currentStatus) {
            case "Đang xử lý":
                if (status.equalsIgnoreCase("Đang giao hàng") || status.equalsIgnoreCase("Đã hủy")) {
                    order.setStatus(status);
                    orderService.updateOrder(order);
                    if (status.equalsIgnoreCase("Đã hủy")){
                        returnBackProductSize(order.getOrderDetails());
                        returnBackPromotion(order.getPromotion());
                    }
                    return true;
                }
                break;
            case "Đang giao hàng":
                if (status.equalsIgnoreCase("Giao hàng thất bại") || status.equalsIgnoreCase("Đã thanh toán")) {
                    order.setStatus(status);
                    orderService.updateOrder(order);
                    return true;
                }
                break;
            case "Giao hàng thất bại":
                if (status.equalsIgnoreCase("Đã hủy")) {
                    order.setStatus(status);
                    orderService.updateOrder(order);
                    return true;
                }
                break;
        }
        return false;
    }

    private void returnBackPromotion(Promotion promotion) {
        if (promotion!=null){
            promotion.setQuantity(promotion.getQuantity()+1);
            promotionService.updatePromotion(promotion);
        }
    }

    private void returnBackProductSize(List<OrderDetail> orderDetails) {
        orderDetails.forEach(item->{
            ProductSize productSize = item.getProductSize();

            if (productSize.getQuantity()==0 && !productSize.getStatus()){
                productSize.setStatus(true);
            }

            productSize.setQuantity(productSize.getQuantity()+item.getQuantity());
            productSizeService.updateProductSize(productSize);

            Product product = productSize.getProduct();
            if (productService.getProductSizesStillSaleForProduct(product.getId()).isEmpty() && !product.getStatus()){
                product.setStatus(true);
                productService.updateProduct(product);
            }

        });

    }

}
