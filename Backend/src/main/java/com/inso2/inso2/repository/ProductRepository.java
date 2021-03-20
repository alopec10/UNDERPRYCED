package com.inso2.inso2.repository;

import com.inso2.inso2.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
