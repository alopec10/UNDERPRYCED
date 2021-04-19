package com.inso2.inso2.controller;

import com.inso2.inso2.dto.ask.GetAllAsksByUserResponse;
import com.inso2.inso2.dto.ask.getAll.GetAllAsksRequest;
import com.inso2.inso2.dto.ask.getAll.GetAllAsksResponse;
import com.inso2.inso2.dto.bid.BidRequest;
import com.inso2.inso2.dto.bid.GetAllBidsByUserResponse;
import com.inso2.inso2.dto.bid.delete.DeleteBidRequest;
import com.inso2.inso2.dto.bid.getAll.GetAllBidsResponse;
import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.BidRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import com.inso2.inso2.repository.ProductRepository;
import com.inso2.inso2.repository.UserRepository;
import com.inso2.inso2.service.ask.CreateAskService;
import com.inso2.inso2.service.ask.ModifyAskService;
import com.inso2.inso2.service.bid.CreateBidService;
import com.inso2.inso2.service.bid.ModifyBidService;
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

import java.util.*;

@RestController
@RequestMapping("/bid")
public class BidController {

    private ProductDetailsRepository productDetailsRepository;

    private UserRepository userRepository;

    private BidRepository bidRepository;

    private ProductRepository productRepository;

    private CreateBidService createBidService;

    private ModifyBidService modifyBidService;

    public BidController(ProductDetailsRepository productDetailsRepository, UserRepository userRepository, BidRepository bidRepository, ProductRepository productRepository, CreateBidService createBidService, ModifyBidService modifyBidService) {
        this.productDetailsRepository = productDetailsRepository;
        this.userRepository = userRepository;
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
        this.createBidService = createBidService;
        this.modifyBidService = modifyBidService;
    }

    @RequestMapping(value = "/make", method = RequestMethod.POST)
    public ResponseEntity<?> makeBid(@RequestBody BidRequest req){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email);
            Product product = productRepository.findByRef(req.getRef());
            ProductDetails productDetails = productDetailsRepository.findByProductAndSize(product, req.getSize());
            Bid bid = bidRepository.findByUserAndProductDetails(user, productDetails);
            if (bid == null){
                return createBidService.create(req.getPrice(), user, productDetails);
            }
            else {
                return modifyBidService.modify(req.getPrice(), productDetails, bid);
            }

        }catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<?> delete(@RequestBody DeleteBidRequest req){
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
            bidRepository.deleteById(bid.getIdBid());
            return ResponseEntity.ok("Bid deleted");
        }catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public ResponseEntity<?> getAll(@RequestBody GetAllAsksRequest req){
        try{
            Product product = productRepository.findByRef(req.getRef());
            ProductDetails productDetails = productDetailsRepository.findByProductAndSize(product, req.getSize());
            List<Integer> prices = bidRepository.findPriceByProductDetails(productDetails);
            Set<Integer> mySet = new HashSet<>(prices);
            List<GetAllBidsResponse> asks = new ArrayList<>();
            for(Integer i: mySet){
                asks.add(new GetAllBidsResponse(i, Collections.frequency(prices,i)));
            }
            return ResponseEntity.ok(asks);
        }
        catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/getAllByUser", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByUser(){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email);
            List<Bid> bids = bidRepository.findByUser(user);
            List<GetAllBidsByUserResponse> bresp = new ArrayList<>();
            for(Bid b: bids){
                bresp.add(new GetAllBidsByUserResponse().build(b));
            }
            return ResponseEntity.ok(bresp);
        }
        catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
