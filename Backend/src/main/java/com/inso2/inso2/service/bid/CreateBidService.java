package com.inso2.inso2.service.bid;

import com.inso2.inso2.model.Bid;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.BidRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateBidService {

private BidRepository bidRepository;

    public CreateBidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public ResponseEntity<?> create (int price, User user, ProductDetails productDetails){
        if(productDetails.getLowestAsk() != null && productDetails.getLowestAsk() <= price){
            // IMPORTANT TO DETERMINE THE STRATEGY IN THIS SPECIFIC CASE
            return new ResponseEntity<>(
                    "It's not possible to make a bid higher than the lowest ask",
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
        Bid bid = new Bid(price, new Date(),user, productDetails);
        bidRepository.saveAndFlush(bid);
        return ResponseEntity.ok("Bid created");
    }
}
