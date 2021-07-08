package com.shopbike.controller;

import com.shopbike.service.cart.ICartService;
import com.shopbike.service.product.IProductService;
import com.shopbike.service.size.ISizeService;
import com.shopbike.service.type.ITypeService;
import com.shopbike.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class Controller {
    @Autowired
    private IProductService productService;
    @Autowired
    private ITypeService typeService;
    @Autowired
    private ISizeService sizeService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICartService cartService;

    //Product Manager
    @GetMapping
    public ModelAndView showProducts() {
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }
}
