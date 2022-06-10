package com.shoeshop.controller.admin;

import com.shoeshop.controller.BaseController;
import com.shoeshop.entity.Category;
import com.shoeshop.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoriesAdminController extends BaseController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/category/add")
    public ModelAndView loadAddCategories() {
        _mvShare.addObject("category", new Category());
        _mvShare.setViewName("admin/categories_add_admin");
        return _mvShare;
    }

    @PostMapping("/category/{id}")
    public String updateCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, @RequestParam("name") String name, @PathVariable("id") Long id ) {
        if (bindingResult.hasErrors()) {
            return "admin/categories_add_admin";
        }
        Category c1 = categoriesService.findCategoryById(id);
        c1.setName(name);
        categoriesService.updateCategory(c1);
        System.err.println(id);
        System.err.println(name);
        return "redirect:/admin/categories";
    }

    @GetMapping("/category/{id}")
    public ModelAndView oneCategory(@PathVariable("id") Long id) {
        Category category = categoriesService.findCategoryById(id);
        _mvShare.addObject("category", category);
        _mvShare.setViewName("admin/categories_edit_admin");
        return _mvShare;
    }

    @PostMapping("/category")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/categories_add_admin";
        }
        categoriesService.addCategories(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories")
    public ModelAndView getListCategories() {
        List<Category> lisCategories = categoriesService.findAll();
        _mvShare.addObject("categories", lisCategories);
        _mvShare.setViewName("admin/categories_admin");
        return _mvShare;
    }

}
