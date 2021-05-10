package com.inso2.inso2.service.product;

import com.inso2.inso2.repository.ProductDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetSizesService {

    private final ProductDetailsRepository productDetailsRepository;

    public GetSizesService(ProductDetailsRepository productDetailsRepository) {
        this.productDetailsRepository = productDetailsRepository;
    }

    public List<String> get(){
        return productDetailsRepository.getSizes();
    }
}
