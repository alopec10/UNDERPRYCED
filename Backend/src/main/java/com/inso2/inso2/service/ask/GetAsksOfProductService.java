package com.inso2.inso2.service.ask;

import com.inso2.inso2.dto.ask.getAll.GetAllAsksRequest;
import com.inso2.inso2.dto.ask.getAll.GetAllAsksResponse;
import com.inso2.inso2.model.Product;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.repository.AskRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import com.inso2.inso2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetAsksOfProductService {

    private final AskRepository askRepository;

    private final ProductDetailsRepository productDetailsRepository;

    private final ProductRepository productRepository;

    public GetAsksOfProductService(AskRepository askRepository, ProductDetailsRepository productDetailsRepository, ProductRepository productRepository) {
        this.askRepository = askRepository;
        this.productDetailsRepository = productDetailsRepository;
        this.productRepository = productRepository;
    }

    public List<GetAllAsksResponse> get(GetAllAsksRequest req){
        Product product = productRepository.findByRef(req.getRef());
        ProductDetails productDetails = productDetailsRepository.findByProductAndSize(product, req.getSize());
        List<Integer> prices = askRepository.findPriceByProductDetails(productDetails);
        Set<Integer> mySet = new HashSet<>(prices);
        List<GetAllAsksResponse> asks = new ArrayList<>();
        for(Integer i: mySet){
            asks.add(new GetAllAsksResponse(i, Collections.frequency(prices,i)));
        }
        return asks;
    }
}
