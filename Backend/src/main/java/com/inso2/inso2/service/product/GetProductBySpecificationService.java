package com.inso2.inso2.service.product;

import com.inso2.inso2.dto.product.specification.ProductSpecificationRequest;
import com.inso2.inso2.dto.product.specification.ProductSpecificationResponse;
import com.inso2.inso2.dto.product.specification.SpecificationDTO;
import com.inso2.inso2.model.Product;
import com.inso2.inso2.repository.ProductRepository;
import com.inso2.inso2.repository.specification.SearchCriteria;
import com.inso2.inso2.repository.specification.SearchOperationUtils;
import com.inso2.inso2.repository.specification.product.ProductSpecification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetProductBySpecificationService {
    private final ProductRepository productRepository;

    public GetProductBySpecificationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ArrayList<ProductSpecificationResponse> get(ProductSpecificationRequest req){
        ProductSpecification specification = new ProductSpecification();
        for(SpecificationDTO sp: req.getSpecifications()){
            specification.add(new SearchCriteria(sp.getKey(), sp.getValue(), SearchOperationUtils.convert(sp.getOperator())));
        }
        List<Product> products = productRepository.findAll(specification);
        ArrayList<ProductSpecificationResponse> resp = new ArrayList<>();
        for(Product p: products){
            resp.add(new ProductSpecificationResponse().build(p));
        }
        return resp;
    }
}
