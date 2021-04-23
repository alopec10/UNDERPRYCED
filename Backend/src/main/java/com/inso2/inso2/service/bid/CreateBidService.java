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

private final BidRepository bidRepository;

    public CreateBidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public void create (int price, User user, ProductDetails productDetails) throws Exception{
        if(productDetails.getLowestAsk() != null && productDetails.getLowestAsk() <= price){
            throw new Exception("It's not possible to make a bid higher than the lowest ask");
        }
        if(!this.isPriceValid(price)){
            throw new Exception("Price must be a positive integer number");
        }
        Bid bid = new Bid(price, new Date(),user, productDetails);
        bidRepository.saveAndFlush(bid);
    }

    private boolean isPriceValid(int price){
        return price > 0;
    }
}
