package com.inso2.inso2.service.bid;

import com.inso2.inso2.dto.bid.BidRequest;
import com.inso2.inso2.model.Bid;
import com.inso2.inso2.model.Product;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.BidRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import com.inso2.inso2.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateOrModifyBidService {
    private final ProductDetailsRepository productDetailsRepository;

    private final BidRepository bidRepository;

    private final ProductRepository productRepository;

    private final CreateBidService createBidService;

    private final ModifyBidService modifyBidService;

    public CreateOrModifyBidService(ProductDetailsRepository productDetailsRepository, BidRepository bidRepository, ProductRepository productRepository, CreateBidService createBidService, ModifyBidService modifyBidService) {
        this.productDetailsRepository = productDetailsRepository;
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
        this.createBidService = createBidService;
        this.modifyBidService = modifyBidService;
    }

    public String createOrModify(BidRequest req, User user) throws Exception{
        Product product = productRepository.findByRef(req.getRef());
        ProductDetails productDetails = productDetailsRepository.findByProductAndSize(product, req.getSize());
        Bid bid = bidRepository.findByUserAndProductDetails(user, productDetails);
        if (bid == null){
            createBidService.create(req.getPrice(), user, productDetails);
            return "Bid created";
        }
        else {
            modifyBidService.modify(req.getPrice(), productDetails, bid);
            return "Bid modified";
        }
    }
}
