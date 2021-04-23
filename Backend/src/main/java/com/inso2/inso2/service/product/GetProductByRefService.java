package com.inso2.inso2.service.product;

import com.inso2.inso2.dto.product.byRef.ProductByRefRequest;
import com.inso2.inso2.dto.product.specification.ProductSpecificationResponse;
import com.inso2.inso2.model.Product;
import com.inso2.inso2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetProductByRefService {
    private final ProductRepository productRepository;

    public GetProductByRefService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ArrayList<ProductSpecificationResponse> get(ProductByRefRequest req){
        List<Product> products = productRepository.findByRefIn(req.getRefs());
        ArrayList<ProductSpecificationResponse> resp = new ArrayList<>();
        for(Product p: products){
            resp.add(new ProductSpecificationResponse().build(p));
        }
        return resp;
    }
}
