package com.scaler.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
        private String title;
        private String description;
        private double price;
        private String imageUrl;
        @ManyToOne
        @JoinColumn(name = "category_fk")//sets cardinality in case more than one table related
        private Category category;
}
