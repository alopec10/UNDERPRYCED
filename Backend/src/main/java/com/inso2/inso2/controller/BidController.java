package com.inso2.inso2.controller;

import com.inso2.inso2.dto.bid.BidRequest;
import com.inso2.inso2.model.Bid;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.BidRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import com.inso2.inso2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/bid")
public class BidController {

    private ProductDetailsRepository productDetailsRepository;

    private UserRepository userRepository;

    private BidRepository bidRepository;

    public BidController(ProductDetailsRepository productDetailsRepository, UserRepository userRepository, BidRepository bidRepository) {
        this.productDetailsRepository = productDetailsRepository;
        this.userRepository = userRepository;
        this.bidRepository = bidRepository;
    }

    @RequestMapping(value = "/make", method = RequestMethod.POST)
    public ResponseEntity<?> makeBid(@RequestBody BidRequest req){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email);
            ProductDetails productDetails = productDetailsRepository.findByIdProductDetails(req.getIdProductDetails());
            if(productDetails.getLowestAsk() != null && productDetails.getLowestAsk() <= req.getPrice()){
                // IMPORTANT TO DETERMINE THE STRATEGY IN THIS SPECIFIC CASE
                return new ResponseEntity<>(
                        "It's not possible to make a bid higher than the lowest ask",
                        HttpStatus.SERVICE_UNAVAILABLE);
            }
            Bid bid = new Bid(req.getPrice(), new Date(),user, productDetails);
            bidRepository.saveAndFlush(bid);
            return ResponseEntity.ok("Bid created");
        }catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<?> modify(@RequestBody BidRequest req){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email);
            ProductDetails productDetails = productDetailsRepository.findByIdProductDetails(req.getIdProductDetails());
            Bid bid = bidRepository.findByUserAndProductDetails(user, productDetails);
            if (bid == null){
                return new ResponseEntity<>(
                        "The bid doesn't exist",
                        HttpStatus.SERVICE_UNAVAILABLE);
            }
            if(productDetails.getLowestAsk() != null && productDetails.getLowestAsk() <= req.getPrice()){
                // IMPORTANT TO DETERMINE THE STRATEGY IN THIS SPECIFIC CASE
                return new ResponseEntity<>(
                        "It's not possible to make a bid higher than the lowest ask",
                        HttpStatus.SERVICE_UNAVAILABLE);
            }
            bid.setPrice(req.getPrice());
            bidRepository.saveAndFlush(bid);
            return ResponseEntity.ok("Bid modified");
        }catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }

    }
}
