package com.inso2.inso2.service.bid;

import com.inso2.inso2.dto.ask.getAll.GetAllAsksRequest;
import com.inso2.inso2.dto.bid.getAll.GetAllBidsRequest;
import com.inso2.inso2.dto.bid.getAll.GetAllBidsResponse;
import com.inso2.inso2.model.Product;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.repository.BidRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import com.inso2.inso2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetBidsOfProductService {
    private final ProductDetailsRepository productDetailsRepository;

    private final BidRepository bidRepository;

    private final ProductRepository productRepository;

    public GetBidsOfProductService(ProductDetailsRepository productDetailsRepository, BidRepository bidRepository, ProductRepository productRepository) {
        this.productDetailsRepository = productDetailsRepository;
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
    }

    public List<GetAllBidsResponse> get(GetAllBidsRequest req){
        Product product = productRepository.findByRef(req.getRef());
        ProductDetails productDetails = productDetailsRepository.findByProductAndSize(product, req.getSize());
        List<Integer> prices = bidRepository.findPriceByProductDetails(productDetails);
        Set<Integer> mySet = new HashSet<>(prices);
        List<GetAllBidsResponse> bids = new ArrayList<>();
        for(Integer i: mySet){
            bids.add(new GetAllBidsResponse(i, Collections.frequency(prices,i)));
        }
        return bids;
    }
}
