package com.inso2.inso2.controller;

import com.inso2.inso2.dto.product.ProductSpecificationRequest;
import com.inso2.inso2.dto.product.SpecificationDTO;
import com.inso2.inso2.dto.register.RegisterRequest;
import com.inso2.inso2.model.Product;
import com.inso2.inso2.repository.ProductRepository;
import com.inso2.inso2.repository.specification.SearchCriteria;
import com.inso2.inso2.repository.specification.SearchOperation;
import com.inso2.inso2.repository.specification.SearchOperationUtils;
import com.inso2.inso2.repository.specification.product.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/specification", method = RequestMethod.POST)
    public Iterable<Product> getProductsBySpecification(@RequestBody ProductSpecificationRequest req){
        ProductSpecification specification = new ProductSpecification();
        for(SpecificationDTO sp: req.getSpecifications()){
            specification.add(new SearchCriteria(sp.getKey(), sp.getValue(), SearchOperationUtils.convert(sp.getOperator())));
        }
        return productRepository.findAll(specification);
    }
}
