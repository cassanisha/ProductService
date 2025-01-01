package com.scaler.repositories;

import com.scaler.models.Product;
//jpa provided by spring, its an interface.
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//for each class/table a different repository to be made
@Repository //responsible for communicating w JPA
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override //optional for null check, to avoid null ptr exception
    Optional<Product> findById(Long id); //predefined methods by hibernate

    List<Product> findByTitle( String word ); //search word: VWash, found word: VWash

    List<Product> findByTitleContains(String str ); //search word: VW, found word: VWash

    Product save(Product product); //create or update. if input product has no id: create a new product, db assigns id to that product and returns it.
    //if input product has id: update the product.

    @Override
    void delete (Product product); //delete product whose id, title, descrip, etc is this

    //HQL
    @Query(" select p.title, p.description from Product p where p.id=:ID ")
    List<Product> someRandomQuery (@Param("ID")long id);

    @Query(value = " select title, description from product where id=:ID ", nativeQuery = true)
    List<Product> someeRandomQuery (@Param("ID")long id);

    @Override
    List<Product> findAll();

}
