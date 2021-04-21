package com.inso2.inso2.service.ask;

import com.inso2.inso2.model.Ask;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.repository.AskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ModifyAskService {
    private final AskRepository askRepository;

    public ModifyAskService(AskRepository askRepository) {
        this.askRepository = askRepository;
    }

    public ResponseEntity<?> modify (int price, ProductDetails productDetails, Ask ask) {
        if(productDetails.getHighestBid() != null && productDetails.getHighestBid() >= price){
            // IMPORTANT TO DETERMINE THE STRATEGY IN THIS SPECIFIC CASE
            return new ResponseEntity<>(
                    "It's not possible to make an ask lower than the highest bid",
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
        ask.setPrice(price);
        askRepository.saveAndFlush(ask);
        return ResponseEntity.ok("Ask modified");
    }
}
