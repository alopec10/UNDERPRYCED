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

    public void modify (int price, ProductDetails productDetails, Bid bid) throws Exception {
        if(productDetails.getLowestAsk() != null && productDetails.getLowestAsk() <= price){
            throw new Exception("It's not possible to make a bid higher than the lowest ask");
        }
        if(!this.isPriceValid(price)){
            throw new Exception("Price must be a positive integer number");
        }
        bid.setPrice(price);
        bidRepository.saveAndFlush(bid);
    }

    private boolean isPriceValid(int price){
        return price > 0;
    }
}
