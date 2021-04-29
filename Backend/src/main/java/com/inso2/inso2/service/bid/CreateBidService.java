package com.inso2.inso2.service.bid;

import com.inso2.inso2.model.Bid;
import com.inso2.inso2.model.PaymentMethod;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.BidRepository;
import com.inso2.inso2.repository.PaymentMethodRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateBidService {

    private final BidRepository bidRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public CreateBidService(BidRepository bidRepository, PaymentMethodRepository paymentMethodRepository) {
        this.bidRepository = bidRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public void create(int price, User user, ProductDetails productDetails) throws Exception {
        if(!this.hasPaymentMethod(user)){
            throw new Exception("It's not possible to create a bid if you don't have at least one payment method added");
        }
        if(!this.hasValidAddress(user)){
            throw new Exception("It's not possible to create a bid if you don't have a valid address");
        }
        if (productDetails.getLowestAsk() != null && productDetails.getLowestAsk() <= price) {
            throw new Exception("It's not possible to make a bid higher than the lowest ask");
        }
        if (!this.isPriceValid(price)) {
            throw new Exception("Price must be a positive integer number");
        }
        Bid bid = new Bid(price, new Date(), user, productDetails);
        bidRepository.saveAndFlush(bid);
    }

    private boolean isPriceValid(int price) {
        return price > 0;
    }

    private boolean hasPaymentMethod(User user){
        PaymentMethod paymentMethod = paymentMethodRepository.findFirstByUserAndIsActiveOrderByIdPayMethodAsc(user, true);
        return paymentMethod != null;
    }

    private boolean hasValidAddress(User user){
        return user.getAddress() != null && user.getZipCode() != null && user.getCountry() != null;
    }
}
