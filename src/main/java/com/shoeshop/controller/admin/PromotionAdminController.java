package com.shoeshop.controller.admin;


import com.shoeshop.controller.BaseController;
import com.shoeshop.entity.Promotion;
import com.shoeshop.repository.PromotionReponsitory;
import com.shoeshop.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class PromotionAdminController extends BaseController {

    @Autowired
    private PromotionReponsitory promotionReponsitory;

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/promotion/add")
    public ModelAndView loadAddPromotion() {
        _mvShare.addObject("promotion", new Promotion());
        _mvShare.setViewName("admin/promotion_add_admin");
        return _mvShare;
    }

//    @PostMapping("/promotion/{id}")
//    public String updateCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, @RequestParam("name") String name, @PathVariable("id") Long id ) {
//        if (bindingResult.hasErrors()) {
//            return "admin/categories_add_admin";
//        }
//        Category c1 = categoriesService.findCategoryById(id);
//        c1.setName(name);
//        categoriesService.updateCategory(c1);
//        System.err.println(id);
//        System.err.println(name);
//        return "redirect:/admin/categories";
//    }
//

    @GetMapping("/promotion/{id}")
    public ModelAndView onePromotion(@PathVariable("id") Long id) {
        Promotion promotion = promotionReponsitory.findPromotionById(id);
        _mvShare.addObject("promotion", promotion);
        _mvShare.setViewName("admin/promotions_edit_admin");
        return _mvShare;
    }

    @PostMapping("/promotion/{id}")
    public String addPromotion(@Valid @ModelAttribute("promotion") Promotion promotion, BindingResult bindingResult,
                               @RequestParam("startDate1") String startDate,  @RequestParam("endDate1") String endDate
    ) {
        System.err.println(bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/promotion_add_admin";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            promotion.setBeginDate(dateFormat.parse(startDate));
            promotion.setEndDate(dateFormat.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        promotion.setQuantity(promotion.getTotalCode());
        promotionService.addPromotion(promotion);

        return "redirect:/admin/promotions";
    }

    @GetMapping("/promotions")
    public ModelAndView getListCategories() {
        List<Promotion> promotionList = promotionReponsitory.findAll();
        _mvShare.addObject("promotions", promotionList);
        _mvShare.setViewName("admin/promotions_admin");
        return _mvShare;
    }

    @PostMapping("/promotion")
    public String updatePromotion(@Valid @ModelAttribute("promotion") Promotion promotion, BindingResult bindingResult,
                               @RequestParam("startDate1") String startDate,  @RequestParam("endDate1") String endDate
    ) {
        System.err.println(bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/promotions_edit_admin";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            promotion.setBeginDate(dateFormat.parse(startDate));
            promotion.setEndDate(dateFormat.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        promotion.setQuantity(promotion.getTotalCode());
        promotionService.addPromotion(promotion);

        return "redirect:/admin/promotions";
    }

}
