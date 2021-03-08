package com.inso2.inso2.domain.persistence;

import com.inso2.inso2.domain.entity.ProductDetails;
import org.springframework.data.repository.CrudRepository;

public interface ProductDetailsRepository extends CrudRepository<ProductDetails, Long> {
}
