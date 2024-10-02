package com.scaler.controllers;

import com.scaler.dtos.FakeStoreProductDto;
import com.scaler.models.Product;
import com.scaler.services.FakeStoreProductService;
import com.scaler.services.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
//localhost:8080/products request will reach here
@RequestMapping("/product")
public class ProductController {
    private final RestTemplate restTemplate;
    ProductService productService;
    //constructor of product service
    public ProductController(ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }
    //function of productController
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id); //function of productService
    }
    @GetMapping()
    public List<Product> getAllProduct(){
        return productService.getAllProducts();
    }
    //create Product
    //delete Product
    //update Product->partial update (PATCH)
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }
    //replace Product->replace (PUT)
}
