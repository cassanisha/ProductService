package com.scaler.services;

import com.scaler.exceptions.ProductNotFoundException;
import com.scaler.models.Category;
import com.scaler.models.Product;
import com.scaler.repositories.CategoryRepository;
import com.scaler.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("sps")
public class SelfProductService implements  ProductService{
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository cateogoryRepository ) {
        this.productRepository = productRepository;
        this.categoryRepository = cateogoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optionalproduct = productRepository.findById(id);
        if( optionalproduct==null ){
            throw new ProductNotFoundException(id, "Product not found");
        }
        return optionalproduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        //save category object in table before creating product object in table
        Category category= product.getCategory();
        // If new category, save it in category repo, then set product category to saved category
        if( category.getId()== null ){
            Category savedCategory= categoryRepository.save( category );
            product.setCategory(savedCategory);
        }
        Product savedProduct= productRepository.save(product);
        Optional<Category> category1= categoryRepository.findById(category.getId());
        savedProduct.setCategory(category1.get());
        return savedProduct;
    }

    @Override
    public boolean deleteProductById(Long id) {
        return false;
    }
}
