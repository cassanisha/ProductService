package com.scaler.controllers;

import com.scaler.models.Product;
import com.scaler.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
//localhost:8080/products request will reach here
@RequestMapping("/product")
public class ProductController {
    ProductService productService;
    //constructor of product service
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //function of productController
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id); //function of productService
    }
    @GetMapping()
    public List<Product> getAllProduct(){
        return new ArrayList<Product>();
    }
    //create Product
    //delete Product
    //update Product->partial update (PATCH)
    //replace Product->replace (PUT)
}
