package com.inso2.inso2.controller;

import com.inso2.inso2.model.Product;
import com.inso2.inso2.repository.ProductRepository;
import com.inso2.inso2.repository.specification.SearchCriteria;
import com.inso2.inso2.repository.specification.SearchOperation;
import com.inso2.inso2.repository.specification.product.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/products")
    public Iterable<Product> getProducts(){
        ProductSpecification specification = new ProductSpecification();
        specification.add(new SearchCriteria("brand", "Nike", SearchOperation.EQUAL));
        specification.add(new SearchCriteria("title", "Adapt", SearchOperation.MATCH));
        return productRepository.findAll(specification);
    }
}
