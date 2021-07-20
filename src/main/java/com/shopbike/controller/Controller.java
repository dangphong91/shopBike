package com.shopbike.controller;

import com.shopbike.model.Product;
import com.shopbike.model.Size;
import com.shopbike.model.Type;
import com.shopbike.service.cart.ICartService;
import com.shopbike.service.product.IProductService;
import com.shopbike.service.size.ISizeService;
import com.shopbike.service.type.ITypeService;
import com.shopbike.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class Controller {
    @Value("${file-upload}")
    private String fileUpload;
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
    @ModelAttribute("types")
    public Iterable<Type> types() {
        return typeService.findAll();
    }
    @ModelAttribute("sizes")
    public Iterable<Size> sizes() {
        return sizeService.findAll();
    }

    //Product Manager
    @GetMapping
    public ModelAndView showProducts() {
        ModelAndView modelAndView = new ModelAndView("/product/manager");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }
    @GetMapping("/listProduct")
    public ResponseEntity<Iterable<Product>> allProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.NO_CONTENT);
    }

    //Type Manager
    @GetMapping("/types")
    public ModelAndView showTypes() {
        ModelAndView modelAndView = new ModelAndView("/type/manager");
        modelAndView.addObject("types", typeService.findAll());
        return modelAndView;
    }
    @GetMapping("/listType")
    public ResponseEntity<Iterable<Type>> allTypes() {
        return new ResponseEntity<>(typeService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/createType")
    public ResponseEntity<Type> createType(@RequestBody Type type) {
        return new ResponseEntity<>(typeService.save(type), HttpStatus.CREATED);
    }
    @DeleteMapping("/type/{id}")
    public ResponseEntity<Type> deleteType(@PathVariable Long id) {
        Optional<Type> typeOptional = typeService.findById(id);
        if (!typeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        typeService.remove(id);
        return new ResponseEntity<>(typeOptional.get(), HttpStatus.NO_CONTENT);
    }

    //Size manager
    @GetMapping("/sizes")
    public ModelAndView showSizes() {
        ModelAndView modelAndView = new ModelAndView("/size/manager");
        modelAndView.addObject("sizes", sizeService.findAll());
        return modelAndView;
    }
    @GetMapping("/listSize")
    public ResponseEntity<Iterable<Size>> allSizes() {
        return new ResponseEntity<>(sizeService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/createSize")
    public ResponseEntity<Size> createSize(@RequestBody Size size) {
        return new ResponseEntity<>(sizeService.save(size), HttpStatus.CREATED);
    }
    @DeleteMapping("/size/{id}")
    public ResponseEntity<Size> deleteSize(@PathVariable Long id) {
        Optional<Size> sizeOptional = sizeService.findById(id);
        if (!sizeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        sizeService.remove(id);
        return new ResponseEntity<>(sizeOptional.get(), HttpStatus.NO_CONTENT);
    }

    //User Manager
    @GetMapping("/users")
    public ModelAndView showUsers() {
        ModelAndView modelAndView = new ModelAndView("/user/manager");
        modelAndView.addObject("users", userService.findAll());
        return modelAndView;
    }

    //Cart Manager
    @GetMapping("/carts")
    public ModelAndView showCarts() {
        ModelAndView modelAndView = new ModelAndView("/cart/manager");
        modelAndView.addObject("carts", cartService.findAll());
        return modelAndView;
    }

    //Admin Manager
    @GetMapping("/admin")
    public ModelAndView showAdmin() {
        ModelAndView modelAndView = new ModelAndView("/admin/set");
        modelAndView.addObject("admin", userService.findById(1L));
        return modelAndView;
    }
}
