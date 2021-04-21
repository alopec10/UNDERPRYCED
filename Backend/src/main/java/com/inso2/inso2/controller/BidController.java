package com.inso2.inso2.controller;

import com.inso2.inso2.dto.ask.getAll.GetAllAsksRequest;
import com.inso2.inso2.dto.bid.BidRequest;
import com.inso2.inso2.dto.bid.GetAllBidsByUserResponse;
import com.inso2.inso2.dto.bid.delete.DeleteBidRequest;
import com.inso2.inso2.dto.bid.getAll.GetAllBidsResponse;
import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.BidRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import com.inso2.inso2.repository.ProductRepository;
import com.inso2.inso2.service.bid.CreateBidService;
import com.inso2.inso2.service.bid.ModifyBidService;
import com.inso2.inso2.service.user.LoadUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/bid")
public class BidController {

    private final ProductDetailsRepository productDetailsRepository;


    private final BidRepository bidRepository;

    private final ProductRepository productRepository;

    private final CreateBidService createBidService;

    private final ModifyBidService modifyBidService;

    private final LoadUserService loadUserService;

    public BidController(ProductDetailsRepository productDetailsRepository, BidRepository bidRepository, ProductRepository productRepository, CreateBidService createBidService, ModifyBidService modifyBidService, LoadUserService loadUserService) {
        this.productDetailsRepository = productDetailsRepository;
        this.bidRepository = bidRepository;
        this.productRepository = productRepository;
        this.createBidService = createBidService;
        this.modifyBidService = modifyBidService;
        this.loadUserService = loadUserService;
    }

    @RequestMapping(value = "/make", method = RequestMethod.POST)
    public ResponseEntity<?> makeBid(@RequestBody BidRequest req){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
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
            User user = loadUserService.load(auth);
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
            User user = loadUserService.load(auth);
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
