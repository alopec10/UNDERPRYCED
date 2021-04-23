package com.inso2.inso2.controller;

import com.inso2.inso2.dto.product.byRef.ProductByRefRequest;
import com.inso2.inso2.dto.product.specification.ProductSpecificationRequest;
import com.inso2.inso2.dto.product.specification.ProductSpecificationResponse;
import com.inso2.inso2.dto.product.specification.SpecificationDTO;
import com.inso2.inso2.model.Product;
import com.inso2.inso2.repository.ProductRepository;
import com.inso2.inso2.repository.specification.SearchCriteria;
import com.inso2.inso2.repository.specification.SearchOperationUtils;
import com.inso2.inso2.repository.specification.product.ProductSpecification;
import com.inso2.inso2.service.product.GetProductByRefService;
import com.inso2.inso2.service.product.GetProductBySpecificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final GetProductBySpecificationService getProductBySpecificationService;

    private final GetProductByRefService getProductByRefService;

    public ProductController(GetProductBySpecificationService getProductBySpecificationService, GetProductByRefService getProductByRefService) {
        this.getProductBySpecificationService = getProductBySpecificationService;
        this.getProductByRefService = getProductByRefService;
    }

    @RequestMapping(value = "/specification", method = RequestMethod.POST)
    public ResponseEntity<?> getProductsBySpecification(@RequestBody ProductSpecificationRequest req){
        return ResponseEntity.ok(getProductBySpecificationService.get(req));
    }

    @RequestMapping(value = "/ref", method = RequestMethod.POST)
    public ResponseEntity<?> getProductsByRef(@RequestBody ProductByRefRequest req){
        return ResponseEntity.ok(getProductByRefService.get(req));
    }
}
