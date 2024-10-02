package com.scaler.dtos;

import com.scaler.models.Category;
import lombok.Getter;
import lombok.Setter;

//class object recieved by ProductService method as response. while using restTemplate object method.
//jackson library converts json code to object. but we need to give dto object to store response
@Getter
@Setter
public class FakeStoreProductDto {
    private int id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String category;

    public String getDesc() {
        return category;
    }
}
