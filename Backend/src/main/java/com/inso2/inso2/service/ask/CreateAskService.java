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
    private AskRepository askRepository;

    public CreateAskService(AskRepository askRepository) {
        this.askRepository = askRepository;
    }

    public ResponseEntity<?> create (int price, User user, ProductDetails productDetails){
        if(productDetails.getHighestBid() != null && productDetails.getHighestBid() >= price){
            // IMPORTANT TO DETERMINE THE STRATEGY IN THIS SPECIFIC CASE
            return new ResponseEntity<>(
                    "It's not possible to make an ask lower than the highest bid",
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
        Ask ask = new Ask(price, new Date(),user, productDetails);
        askRepository.saveAndFlush(ask);
        return ResponseEntity.ok("Ask created");
    }

}
