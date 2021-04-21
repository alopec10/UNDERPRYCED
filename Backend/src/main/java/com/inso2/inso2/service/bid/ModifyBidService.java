package com.inso2.inso2.service.bid;

import com.inso2.inso2.model.Bid;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.repository.BidRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ModifyBidService {

    private final BidRepository bidRepository;

    public ModifyBidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public ResponseEntity<?> modify (int price, ProductDetails productDetails, Bid bid) {
        if(productDetails.getLowestAsk() != null && productDetails.getLowestAsk() <= price){
            // IMPORTANT TO DETERMINE THE STRATEGY IN THIS SPECIFIC CASE
            return new ResponseEntity<>(
                    "It's not possible to make a bid higher than the lowest ask",
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
        bid.setPrice(price);
        bidRepository.saveAndFlush(bid);
        return ResponseEntity.ok("Bid modified");
    }
}
