package com.shoeshop.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.shoeshop.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shoeshop.dto.CartItemDto;
import com.shoeshop.dto.CustomerAddressDto;
import com.shoeshop.dto.OrderDto;
import com.shoeshop.service.CustomerAddressService;
import com.shoeshop.service.CustomerService;
import com.shoeshop.service.OrderDetailService;
import com.shoeshop.service.OrderService;
import com.shoeshop.service.ProductService;
import com.shoeshop.service.ProductSizeService;
import com.shoeshop.service.PromotionService;
import com.shoeshop.service.ShoppingCartService;

@Controller
@RequestMapping("user/order")
public class OrderController extends BaseController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductSizeService productSizeService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private CustomerAddressService customerAddressService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ModelAndView showPageOrder(Principal principal) {
        _mvShare.addObject("customer", customerService.getCustomerCurrent(principal.getName()));
        _mvShare.addObject("cart", shoppingCartService.getCartDto());
        _mvShare.addObject("customerAddress", new CustomerAddressDto());
        _mvShare.addObject("order", new OrderDto());
        _mvShare.setViewName("orders/order");
        return _mvShare;
    }

    @PostMapping("")
    public String addOrder(@ModelAttribute("order") OrderDto order, Principal principal,
            RedirectAttributes redirectAttributes) {

        Customer customer = customerService.getCustomerByPhoneOrEmail(order.getCustomerPhone());

        /* check customer */
        if ((customer != null) && (customer.getEmail().equalsIgnoreCase(principal.getName())
                || customer.getPhone().equalsIgnoreCase(principal.getName()))) {
            Collection<CartItemDto> listItem = shoppingCartService.getListCartItem();

            /* check list item */
            if (listItem != null && !listItem.isEmpty()) {

                /* check quantity of product size have in db */
                List<OrderDetail> orderDetailList = checkQuantityAndCreateOrderDetails(listItem.stream().toList());

                if (orderDetailList != null) {
                    Order orderNew = new Order();
                    orderNew.setCustomer(customer);
                    orderNew.setCreatedDate(new Date());
                    orderNew.setNote(order.getNote());
                    orderNew.setStatus("Đang xử lý");
                    CustomerAddress addressShip = customerAddressService.getCustomerAddressById(order.getShipAddressId());
                    orderNew.setShipAddress(addressShip);

                    /*Set address default customer*/
                    addressShip.setStatus(true);
                    customerAddressService.updateAllStatusCustomerAddressFalse(customer.getAddresses());
                    customerAddressService.addCustomerAddress(addressShip);

                    Promotion promotion = checkAndGetPromotion(order.getPromotionCode());


                    if (promotion != null && promotion.getMinValue() <= shoppingCartService.getAmount()) {
                        orderNew.setPromotion(promotion);
                        promotion.setQuantity(promotion.getQuantity() - 1);
                        promotionService.updatePromotion(promotion);
                    }

                    Order orderAdd = orderService.addOrder(orderNew);

                    orderDetailList.forEach(item -> {
                        item.setOrder(orderAdd);
                        ProductSize productSize = item.getProductSize();
                        productSize.setQuantity(productSize.getQuantity() - item.getQuantity());
                        if (productSize.getQuantity() == 0) {
                            productSize.setStatus(false);
                            productSizeService.updateProductSize(productSize);
                            Product product = productSize.getProduct();
                            if (productService.getProductSizesStillSaleForProduct(product.getId()).isEmpty()) {
                                product.setStatus(false);
                                productService.updateProduct(product);
                            }
                        } else {
                            productSizeService.updateProductSize(productSize);
                        }

                        orderDetailService.addOrderDetail(item);
                    });

                    shoppingCartService.clearCart();

                    redirectAttributes.addFlashAttribute("message_order",
                            "<script> alert(\"Đơn hàng của bạn đã được đặt thành công\");</script>");
                } else {
                    redirectAttributes.addFlashAttribute("message_order",
                            "<script>Swal.fire('Sản phẩm bạn chọn hiện tại không đủ số lượng');</script>");
                    return "redirect:/user/order";
                }
            } else {
                redirectAttributes.addFlashAttribute("message_order",
                        "<script> alert(\"Chưa có sản phẩm trong giỏ hàng!\");</script>");
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).toString();
        }

        return "redirect:/";
    }

    private Promotion checkAndGetPromotion(String code) {
        if (code.equalsIgnoreCase(""))
            return null;
        Promotion promotion = promotionService.getPromotionByCode(code);
        if (promotion == null)
            return null;
        if (promotion.getQuantity() == 0)
            return null;
        if (promotion.getBeginDate().after(new Date()) || promotion.getEndDate().before(new Date()))
            return null;

        return promotion;
    }

    private List<OrderDetail> checkQuantityAndCreateOrderDetails(List<CartItemDto> list) {
        List<OrderDetail> orderDetailList = new ArrayList<>();

        for (CartItemDto item : list) {
            ProductSize productSize = productSizeService.getProductSizeById(item.getProductSizeId());
            if (productSize.getQuantity() < item.getQuantity() && productSize.getStatus() == true)
                return null;
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setPrice(item.getPrice());
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setProductSize(productSize);

            orderDetailList.add(orderDetail);
        }

        return orderDetailList;
    }
}
