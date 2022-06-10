package com.shoeshop.service.serviceImpl;

import com.shoeshop.dto.CartDto;
import com.shoeshop.dto.CartItemDto;
import com.shoeshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private Map<Long, CartItemDto> map = new HashMap<>();

    @Override
    public void addCartIem(CartItemDto item){
        CartItemDto existedItem = map.get(item.getProductSizeId());
        if (existedItem != null){
            if (existedItem.getQuantityMax()<(existedItem.getQuantity()+ item.getQuantity())){
                existedItem.setQuantity(existedItem.getQuantityMax());
            }else{
                existedItem.setQuantity(existedItem.getQuantity()+ item.getQuantity());
            }
        }
        else{
            map.put(item.getProductSizeId(),item);
        }
    }

    @Override
    public void removeCartItem(long productSizeId){
        map.remove(productSizeId);
    }

    @Override
    public void updateCartItem(Long productSizeId, int quantity){
        CartItemDto cartItemDto = map.get(productSizeId);
        cartItemDto.setQuantity(quantity);

        if (cartItemDto.getQuantity()<=0){
            map.remove(productSizeId);
        }
    }

    @Override
    public Collection<CartItemDto> getListCartItem(){
        return map.values();
    }


    @Override
    public double getAmount(){
        return map.values().stream().mapToDouble(item->item.getQuantity()*item.getPrice()).sum();
    }

    @Override
    public int getTotalQuantity(){
        return map.values().stream().mapToInt(item->item.getQuantity()).sum();
    }

    @Override
    public CartDto getCartDto(){
        CartDto cartDto = new CartDto();
        cartDto.setItems(getListCartItem());
        cartDto.setTotalAmount(getAmount());
        cartDto.setTotalQuantity(getTotalQuantity());
        return  cartDto;
    }

    @Override
    public void clearCart(){
        map.clear();
    }


}
