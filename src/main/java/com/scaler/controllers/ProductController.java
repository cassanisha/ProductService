package com.scaler.controllers;

import com.scaler.dtos.FakeStoreProductDto;
import com.scaler.exceptions.ProductNotFoundException;
import com.scaler.models.Product;
import com.scaler.services.FakeStoreProductService;
import com.scaler.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
//localhost:8090/products request will reach here
@RestController
@RequestMapping("/product")
public class ProductController {
    private final RestTemplate restTemplate;
    private ProductService productService;
    //constructor of product service
    public ProductController(@Qualifier("fkps") ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }
    //function of productController
    //*********
    /*@GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id); //function of productService
    }*/
    //*********
    //ResponseEntity is a generic class that contains T type parameter, status codes, header files etc.
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {

        Product product=productService.getProductById(id); //method of productService
        //declaring the generic class
        ResponseEntity<Product> responseEntity;
////Earlier NULL Pointer Exception was handled in ProductController class ke method me hi. Now we will handle it in service class only.
//        if( product == null ){
//            //HttpStatus is enum stored in Spring Framework
//            responseEntity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            return responseEntity;
//        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Product>>getAllProduct(){
        List<Product> products = productService.getAllProducts();
        if( products.isEmpty() ){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //create Product
    @PostMapping //Request Body mean: Whatever body is coming in req, map that body to product object, can use DTO as well
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product createdproduct= productService.createProduct( product );
        if( createdproduct == null ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(createdproduct, HttpStatus.CREATED);
    }
    //delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        boolean isDeleted = productService.deleteProductById(id);
        if( !isDeleted ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    //update Product->partial update (PATCH)
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }
    //replace Product->replace (PUT)

    //making local exception handler for this controller only. and not global exception handling
    @ExceptionHandler( FileNotFoundException.class )
    public ResponseEntity<Void> handleFileNotFound(FileNotFoundException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
