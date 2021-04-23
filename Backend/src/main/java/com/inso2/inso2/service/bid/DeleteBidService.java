package com.inso2.inso2.service.bid;

import com.inso2.inso2.dto.bid.delete.DeleteBidRequest;
import com.inso2.inso2.model.Bid;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.BidRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteBidService {

    private final ProductDetailsRepository productDetailsRepository;

    private final BidRepository bidRepository;

    public DeleteBidService(ProductDetailsRepository productDetailsRepository, BidRepository bidRepository) {
        this.productDetailsRepository = productDetailsRepository;
        this.bidRepository = bidRepository;
    }

    public void delete(DeleteBidRequest req, User user) throws Exception{
        ProductDetails productDetails = productDetailsRepository.findByIdProductDetails(req.getIdProductDetails());
        Bid bid = bidRepository.findByUserAndProductDetails(user, productDetails);
        if (bid == null){
            throw new Exception("The bid doesn't exist");
        }
        bidRepository.deleteById(bid.getIdBid());
    }
}
