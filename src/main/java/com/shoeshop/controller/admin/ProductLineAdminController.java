package com.shoeshop.controller.admin;

import com.shoeshop.controller.BaseController;
import com.shoeshop.entity.ProductLine;
import com.shoeshop.service.BrandService;
import com.shoeshop.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductLineAdminController extends BaseController {

    @Autowired
    private ProductLineService productLineService;

    @Autowired
    private BrandService brandService;

    @GetMapping("/product-line/add")
    public ModelAndView loadAddProductLine() {

        _mvShare.addObject("productLine", new ProductLine());
        _mvShare.setViewName("admin/product_line_add_admin");
        return _mvShare;
    }

    @PostMapping("/product-line")
    public String addProductLine(@Valid @ModelAttribute("productLine") ProductLine productLine,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/product_line_add_admin";
        }

        productLine.setCreatedDate(new Date());
        productLineService.addProductLine(productLine);
        return "redirect:/admin/product-lines";
    }

    @GetMapping("/product-lines")
    public ModelAndView getListProductLines() {
        List<ProductLine> productLineList = productLineService.findAll();
        _mvShare.addObject("productLines", productLineList);
        _mvShare.setViewName("admin/product_lines_admin");
        return _mvShare;
    }

    @GetMapping("/product-line/{id}")
    public ModelAndView oneProductLine(@PathVariable("id") Long id) {
        ProductLine productLine= productLineService.getProductLine(id);
        _mvShare.addObject("productLine", productLine);
        _mvShare.setViewName("admin/product_lines_edit_admin");
        return _mvShare;
    }

    @PostMapping("/product-line/{id}")
    public String updateCategory(@Valid @ModelAttribute("productLine") ProductLine productLine, BindingResult bindingResult,
                                 @RequestParam("name") String name,
                                 @PathVariable("id") Long id
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/product_line_add_admin";
        }
        ProductLine productLine1 = productLineService.getProductLine(id);
        productLine1.setName(name);
        System.err.println("IDD Product Line");
        System.err.println(productLine1.getId());
        System.err.println(name);
        productLineService.updateProductLine(productLine1);
        return "redirect:/admin/product-lines";
    }


}
