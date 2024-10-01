package com.scaler.services;
import com.scaler.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product getProductById( Long id );
    List<Product> getAllProducts();
}
