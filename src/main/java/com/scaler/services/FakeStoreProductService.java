package com.scaler.services;
import com.scaler.configs.RestTemplateConfig;
import com.scaler.dtos.FakeStoreProductDto;
import com.scaler.models.Category;
import com.scaler.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
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
        //code in next class
        FakeStoreProductDto[] fakeStoreProductDtos=restTemplate.getForObject(("https://fakestoreapi.com/products"), FakeStoreProductDto[].class);
        List<Product> products=new ArrayList<>();
        assert fakeStoreProductDtos != null;
        for( FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos){
            if( fakeStoreProductDto==null )continue;
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }
//postForObject function of restTemplate does not return Product details. we want a non void function.
    //we are using low level function from restTemplate and changing the arguments to get non void function.
    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto=new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImageUrl(product.getImageUrl());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>( FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response=restTemplate.execute("https://fakestoreapi.com/products"+id, HttpMethod.PUT, requestCallback, responseExtractor);
        if( response == null )return null;
        return convertFakeStoreProductDtoToProduct(response);
    }
}
