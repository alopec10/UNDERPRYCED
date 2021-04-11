package com.inso2.inso2.repository;

import com.inso2.inso2.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
    ProductDetails findByIdProductDetails(long id);
}
