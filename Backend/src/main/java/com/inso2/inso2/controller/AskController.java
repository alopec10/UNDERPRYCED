package com.inso2.inso2.controller;

import com.inso2.inso2.dto.ask.AskRequest;
import com.inso2.inso2.model.Ask;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.AskRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import com.inso2.inso2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/ask")
public class AskController {

    private ProductDetailsRepository productDetailsRepository;

    private UserRepository userRepository;

    private AskRepository askRepository;

    public AskController(ProductDetailsRepository productDetailsRepository, UserRepository userRepository, AskRepository askRepository) {
        this.productDetailsRepository = productDetailsRepository;
        this.userRepository = userRepository;
        this.askRepository = askRepository;
    }

    @RequestMapping(value = "/make", method = RequestMethod.POST)
    public ResponseEntity<?> makeAsk(@RequestBody AskRequest req){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email);
            ProductDetails productDetails = productDetailsRepository.findByIdProductDetails(req.getIdProductDetails());
            if(productDetails.getHighestBid() != null && productDetails.getHighestBid() >= req.getPrice()){
                // IMPORTANT TO DETERMINE THE STRATEGY IN THIS SPECIFIC CASE
                return new ResponseEntity<>(
                        "It's not possible to make an ask lower than the highest bid",
                        HttpStatus.SERVICE_UNAVAILABLE);
            }
            Ask ask = new Ask(req.getPrice(), new Date(),user, productDetails);
            askRepository.saveAndFlush(ask);
            return ResponseEntity.ok("Ask created");
        }catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<?> modify(@RequestBody AskRequest req){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email);
            ProductDetails productDetails = productDetailsRepository.findByIdProductDetails(req.getIdProductDetails());
            Ask ask = askRepository.findByUserAndProductDetails(user, productDetails);
            if(ask == null){
                return new ResponseEntity<>(
                        "The ask doesn't exist",
                        HttpStatus.SERVICE_UNAVAILABLE);
            }
            if(productDetails.getHighestBid() != null && productDetails.getHighestBid() >= req.getPrice()){
                // IMPORTANT TO DETERMINE THE STRATEGY IN THIS SPECIFIC CASE
                return new ResponseEntity<>(
                        "It's not possible to make an ask lower than the highest bid",
                        HttpStatus.SERVICE_UNAVAILABLE);
            }
            ask.setPrice(req.getPrice());
            askRepository.saveAndFlush(ask);
            return ResponseEntity.ok("Ask modified");
        }catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }

    }


}
