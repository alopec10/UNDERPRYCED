package com.inso2.inso2.domain.persistence;

import com.inso2.inso2.domain.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
