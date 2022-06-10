package com.shoeshop.service.serviceImpl;


import com.shoeshop.entity.Promotion;
import com.shoeshop.repository.PromotionReponsitory;
import com.shoeshop.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionReponsitory  promotionReponsitory;


    @Override
    public Promotion getPromotionByCode(String code) {
        return promotionReponsitory.findPromotionByCode(code);
    }

    @Override
    public Promotion findPromotionById(long id) {
        Promotion promotion= promotionReponsitory.findPromotionById(id);
        if(promotion == null){
            throw  new RuntimeException("Category is null!");
        }
        return promotion;
    }

    @Override
    public List<Promotion> findAll() {
        return promotionReponsitory.findAll();
    }

    @Override
    public void updatePromotion(Promotion promotion){
        promotionReponsitory.save(promotion);
    }

    @Override
    public Promotion addPromotion(Promotion promotion) {
        return promotionReponsitory.save(promotion);
    }

}
