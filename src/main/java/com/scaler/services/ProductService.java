package com.scaler.services;
import com.scaler.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product getProductById( Long id );
    List<Product> getAllProducts();
    //3
    //FakestorePSDTO not given in args in place of product. Product ko convert krenge
    //in FakestorePSDTO and then send. cz not necessary hmesha relpace krte wqt fakeStoreDTO object is used.
    Product replaceProduct(Long id, Product product);
    Product createProduct(Product product);
    boolean deleteProductById(Long id);

}
