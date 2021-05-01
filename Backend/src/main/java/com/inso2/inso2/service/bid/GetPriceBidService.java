package com.inso2.inso2.service.bid;

import com.inso2.inso2.model.Bid;
import com.inso2.inso2.model.Product;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.BidRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import com.inso2.inso2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetPriceBidService {
    private final BidRepository bidRepository;
    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;

    public GetPriceBidService(BidRepository bidRepository, ProductRepository productRepository, ProductDetailsRepository productDetailsRepository) {
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
        this.productDetailsRepository = productDetailsRepository;
    }

    public Map<String,Integer> get (User user, String ref, String size) {
        Product product = productRepository.findByRef(ref);
        ProductDetails productDetails = productDetailsRepository.findByProductAndSize(product, size);
        Bid bid = bidRepository.findByUserAndProductDetails(user, productDetails);
        int price = 0;
        if (bid != null) {
            price = bid.getPrice();
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
