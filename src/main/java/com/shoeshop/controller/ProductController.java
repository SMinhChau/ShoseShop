package com.shoeshop.controller;

import com.shoeshop.dto.BrandDto;
import com.shoeshop.dto.ProductLineDto;
import com.shoeshop.entity.Product;
import com.shoeshop.entity.ProductLine;
import com.shoeshop.service.BrandService;
import com.shoeshop.service.ProductLineService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shoeshop.service.ProductService;

import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductLineService productLineService;

    @GetMapping("/{id}")
    public ModelAndView productDetail(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product.getStatus()){
            product.setProductSizes(productService.getProductSizesStillSaleForProduct(product.getId()));
        }
        _mvShare.addObject("product", product);
        _mvShare.addObject("relatedProducts", productService.getRelativedProducts(product.getProductLine().getId()));
        _mvShare.setViewName("products/product_detail");
        return _mvShare;
    }

    @GetMapping("/brand/{id}")
    public ModelAndView productBrand(@PathVariable Long id) {
        Pageable pageable = PageRequest.of(0, 8);
        _mvShare.addObject("brand", brandService.getBrandByIdAndProductPageOfProductLine(id, pageable));
        _mvShare.setViewName("products/brand");
        return _mvShare;
    }

    @GetMapping("/product-line/{id}")
    public ModelAndView productLine(@PathVariable Long id) {
        Pageable pageable = PageRequest.of(0, 8);
        ProductLine productLine = productLineService.getProductLine(id);
        BrandDto brand = brandService.getBrandByIdAndProductPageOfProductLine(productLine.getBrand().getId(), pageable);
        AtomicReference<ProductLineDto> productLineDto = new AtomicReference<>();
        brand.getProductLines().forEach(item -> {
            if (item.getId() == id) {
                productLineDto.set(item);
            }
        });
        _mvShare.addObject("productLine", productLineDto.get());
        _mvShare.addObject("brand", brand);
        _mvShare.setViewName("products/product_line");
        return _mvShare;
    }

    @GetMapping("/search")
    public ModelAndView productSearch(@RequestParam("key") String key,@RequestParam(value = "page", required = false) Integer page){
        _mvShare.addObject("key",key);
        if (key.trim().equalsIgnoreCase("")){
            _mvShare.addObject("pageProduct", null);
        }else{
            if (page == null)
                page = 0;
            else
                page--;
            Pageable pageable = PageRequest.of(page, 8);
            key = key.replace(" ","%");
            _mvShare.addObject("pageProduct", productService.findProductsByKeyInName(key,pageable));
        }
        _mvShare.setViewName("products/search");
        return _mvShare;
    }


}
