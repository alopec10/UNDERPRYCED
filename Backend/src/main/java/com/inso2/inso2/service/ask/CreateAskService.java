package com.inso2.inso2.service.ask;


import com.inso2.inso2.model.Ask;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.AskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateAskService {
    private final AskRepository askRepository;

    public CreateAskService(AskRepository askRepository) {
        this.askRepository = askRepository;
    }

    public void create (int price, User user, ProductDetails productDetails) throws Exception{
        if(productDetails.getHighestBid() != null && productDetails.getHighestBid() >= price){
            throw new Exception("It's not possible to make an ask lower than the highest bid");
        }
        if(!this.isPriceValid(price)){
            throw new Exception("Price must be a positive integer number");
        }
        Ask ask = new Ask(price, new Date(),user, productDetails);
        askRepository.saveAndFlush(ask);
    }

    private boolean isPriceValid(int price){
        return price > 0;
    }

}
