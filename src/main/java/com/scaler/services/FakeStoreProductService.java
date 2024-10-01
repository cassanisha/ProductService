package com.scaler.services;
import com.scaler.configs.RestTemplateConfig;
import com.scaler.dtos.FakeStoreProductDto;
import com.scaler.models.Category;
import com.scaler.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;


@Service
public class FakeStoreProductService implements ProductService {
    //REST TEMPLATE OBJECT IS NEEDED TO USE ITS METHODS
    private RestTemplate restTemplate;

    //constructor D Inj
    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }
    private Product convertFakeStoreProductDtoToProduct ( FakeStoreProductDto fsd ){
        Product product=new Product();
        product.setId(fsd.getId());
        product.setTitle(fsd.getTitle());
        product.setDescription(fsd.getDescription());
        product.setPrice(fsd.getPrice());
        product.setImageUrl(fsd.getImageUrl());

        Category category=new Category();
        category.setDesc(fsd.getDesc());
        product.setCategory(category);

        return product;
    }

    //The @Override annotation in Java is used to indicate that a method in a subclass is intended to override a method
    // in its superclass or interface
    @Override
    public Product getProductById(Long id) {
        //Fakestore API will send a response in JSON. Here category class will be in string. DTO used
        //to recieve data from client to controller
        //to send data from controller to client
        FakeStoreProductDto fsd=restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        //we are getting object as response where category is string. we want it to be an object.
        if( fsd==null )return null;
        return convertFakeStoreProductDtoToProduct(fsd);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<Product>();
    }
}
