package com.shoeshop.controller.rest;

import com.shoeshop.dto.CartDto;
import com.shoeshop.dto.CartItemDto;
import com.shoeshop.entity.ProductSize;
import com.shoeshop.service.ProductSizeService;
import com.shoeshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/cart/")
public class ShoppingCartRest {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductSizeService productSizeService;

    @PostMapping("add/{productSizeId}")
    public CartDto addCartItem (@PathVariable Long productSizeId, @RequestParam  int quantity, HttpSession session){
        ProductSize productSize = productSizeService.getProductSizeById(productSizeId);
        if (productSize != null){
            CartItemDto cartItemDto = new CartItemDto();
            cartItemDto.setProductSizeId(productSizeId);
            cartItemDto.setProductSize(productSize.getSize().getName());
            cartItemDto.setProductName(productSize.getProduct().getName());
            cartItemDto.setPrice(productSize.getProduct().getPromotionPrice());
            cartItemDto.setAvatar(productSize.getProduct().getAvatar());
            cartItemDto.setQuantity(quantity);
            cartItemDto.setQuantityMax(productSize.getQuantity());
            shoppingCartService.addCartIem(cartItemDto);
        }


        return createCartDto(session);
    }

    @PutMapping("edit/{productSizeId}")
    public CartDto updateCartItem(@PathVariable Long productSizeId, @RequestParam  int quantity,HttpSession session){
        shoppingCartService.updateCartItem(productSizeId,quantity);

        return  createCartDto(session);
    }

    @GetMapping("data")
    public CartDto getCart(HttpSession session){
        return  createCartDto(session);
    }

    @DeleteMapping ("delete/{productSizeId}")
    public CartDto deleteCartItem(@PathVariable Long productSizeId,HttpSession session){
        shoppingCartService.removeCartItem(productSizeId);
        return  createCartDto(session);
    }

    private CartDto createCartDto(HttpSession session){
        CartDto cartDto = new CartDto();
        cartDto.setItems(shoppingCartService.getListCartItem());
        cartDto.setTotalAmount(shoppingCartService.getAmount());
        cartDto.setTotalQuantity(shoppingCartService.getTotalQuantity());

        session.setAttribute("cart",cartDto);

        return  cartDto;
    }
}
