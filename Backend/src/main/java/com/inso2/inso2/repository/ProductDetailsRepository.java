package com.inso2.inso2.repository;

import com.inso2.inso2.model.Product;
import com.inso2.inso2.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
    ProductDetails findByIdProductDetails(long id);
    ProductDetails findByProductAndSize(Product product, String size);
    @Query("SELECT DISTINCT p.size FROM ProductDetails p")
    List<String> getSizes();
}
