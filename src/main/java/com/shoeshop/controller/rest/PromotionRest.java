package com.shoeshop.controller.rest;

import com.shoeshop.dto.PromotionDto;
import com.shoeshop.entity.Promotion;
import com.shoeshop.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/promotion")
public class PromotionRest {
    @Autowired
    private PromotionService promotionService;

    @GetMapping("")
    public PromotionDto getPromotion(@RequestParam("code") String code, @RequestParam("amount") double amount){
        Promotion promotion = promotionService.getPromotionByCode(code);
        PromotionDto promotionDto = new PromotionDto();
        promotionDto.setStatus(false);
        if (promotion == null){
            promotionDto.setMessage("Mã khuyến mãi này không tồn tại");
        }
        else{
            if (promotion.getBeginDate().after(new Date())){
                promotionDto.setMessage("Khuyến mãi này chưa bắt đầu");
            }else
                if(promotion.getEndDate().before(new Date())){
                    promotionDto.setMessage("Khuyến mãi này đã kết thúc");
                }else
                    if (promotion.getQuantity()==0){
                        promotionDto.setMessage("Mã khuyến mãi này đã hết số lượng");
                    }else
                        if (promotion.getMinValue()>amount){
                            promotionDto.setMessage("Đơn hàng chưa đạt giá trị tối thiều");
                        }else{
                            promotionDto.setStatus(true);
                            double moneyLess = (promotion.getPercentile()*1.00/100)*amount;
                            if (moneyLess > promotion.getMaxValue())
                                moneyLess = promotion.getMaxValue();
                            promotionDto.setDiscount(moneyLess);
                            promotionDto.setCode(code);
                        }
        }

        return promotionDto;
    }
}
