package com.shoeshop.controller.admin;

import com.shoeshop.controller.BaseController;
import com.shoeshop.entity.Brand;
import com.shoeshop.service.BrandService;
import com.shoeshop.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BrandAdminController extends  BaseController{
    @Autowired
     private BrandService brandService;

    @Autowired
    private IStorageService iStorageService;

    @GetMapping("/brand/add")
    public ModelAndView loadAddBrand(){
        _mvShare.addObject("brand", new Brand());
        _mvShare.setViewName("admin/brands_add_admin");
        return _mvShare;
    }

    @PostMapping("/brand")
    public String addBrand(@Valid  @ModelAttribute("brand")Brand brand, BindingResult bindingResult,
                           @RequestParam("avatarFile") MultipartFile files){
        if (bindingResult.hasErrors()) {
            return "admin/brands_add_admin";
        }
        if(files != null){
            iStorageService.setStorageFolder("brands");
            String fileName= iStorageService.storeFile(files);
            brand.setImage(fileName);
        }

        Date createDate= new Date();
        brand.setCreatedDate(createDate);
        brandService.addBrands(brand);
        return "redirect:brands";
    }

    @GetMapping("/brands")
    public ModelAndView getListBrand(){
        List<Brand> listBrands= brandService.findAll();
        _mvShare.addObject("brands", listBrands);
        _mvShare.setViewName("admin/brands_admin");
        return _mvShare;
    }

    @PostMapping("/brand/{id}")
    public String updateCategory(@Valid @ModelAttribute("brand") Brand brand,
                                 BindingResult bindingResult, @RequestParam("name") String name,
                                 @PathVariable("id") Long id,
                                 @RequestParam("avatarFile") MultipartFile files) {
        if (bindingResult.hasErrors()) {
            return "admin/brands_add_admin";
        }
        Brand brand1= brandService.findBrandById(id);
        if(files != null  && !files.isEmpty() ){
            iStorageService.setStorageFolder("brands");
            iStorageService.deleteFile(brand1.getImage());
            String fileName= iStorageService.storeFile(files);
            brand.setImage(fileName);
        }
        Date createDate= new Date();
        brand.setCreatedDate(createDate);
        brandService.addBrands(brand);
        brand.setName(name);
        brandService.updateBrand(brand);
        System.err.println(id);
        System.err.println(name);
        return "redirect:/admin/brands";
    }

    @GetMapping("/brand/{id}")
    public ModelAndView oneCategory(@PathVariable("id") Long id) {
        Brand brand = brandService.findBrandById(id);
        _mvShare.addObject("brand", brand);
        _mvShare.setViewName("admin/brands_edit_admin");
        return _mvShare;
    }
}
