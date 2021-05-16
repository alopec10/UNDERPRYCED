package com.inso2.inso2.repository;

import com.inso2.inso2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findByRefIn(Collection<String> refs);
    Product findByRef(String ref);
    @Query("SELECT DISTINCT p.brand FROM Product p")
    List<String> getBrands();
    @Query("SELECT DISTINCT p.year FROM Product p")
    List<String> getYears();
}
