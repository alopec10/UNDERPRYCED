package com.inso2.inso2.service.ask;

import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.AskRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import com.inso2.inso2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetPriceAskService {

    private final AskRepository askRepository;
    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;

    public GetPriceAskService(AskRepository askRepository, ProductRepository productRepository, ProductDetailsRepository productDetailsRepository) {
        this.askRepository = askRepository;
        this.productRepository = productRepository;
        this.productDetailsRepository = productDetailsRepository;
    }

    public Map<String,Integer> get (User user, String ref, String size) {
        Product product = productRepository.findByRef(ref);
        ProductDetails productDetails = productDetailsRepository.findByProductAndSize(product, size);
        Ask ask = askRepository.findByUserAndProductDetails(user, productDetails);
        int price = 0;
        if (ask != null) {
            price = ask.getPrice();
        }
        int finalPrice = price;
        Map<String, Integer> response = new HashMap<>() {
            {
                put("price", Integer.valueOf(finalPrice));
            }
        };

        return response;
    }
}
