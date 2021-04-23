package com.inso2.inso2.controller;

import com.inso2.inso2.dto.ask.getAll.GetAllAsksRequest;
import com.inso2.inso2.dto.bid.BidRequest;
import com.inso2.inso2.dto.bid.GetAllBidsByUserResponse;
import com.inso2.inso2.dto.bid.delete.DeleteBidRequest;
import com.inso2.inso2.dto.bid.getAll.GetAllBidsRequest;
import com.inso2.inso2.dto.bid.getAll.GetAllBidsResponse;
import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.BidRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import com.inso2.inso2.repository.ProductRepository;
import com.inso2.inso2.service.bid.*;
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

    private final LoadUserService loadUserService;

    private final DeleteBidService deleteBidService;

    private final GetBidsOfProductService getBidsOfProductService;

    private final GetBidsOfUserService getBidsOfUserService;

    private final CreateOrModifyBidService createOrModifyBidService;

    public BidController(LoadUserService loadUserService, DeleteBidService deleteBidService, GetBidsOfProductService getBidsOfProductService, GetBidsOfUserService getBidsOfUserService, CreateOrModifyBidService createOrModifyBidService) {
        this.loadUserService = loadUserService;
        this.deleteBidService = deleteBidService;
        this.getBidsOfProductService = getBidsOfProductService;
        this.getBidsOfUserService = getBidsOfUserService;
        this.createOrModifyBidService = createOrModifyBidService;
    }

    @RequestMapping(value = "/make", method = RequestMethod.POST)
    public ResponseEntity<?> makeBid(@RequestBody BidRequest req){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            return ResponseEntity.ok(createOrModifyBidService.createOrModify(req, user));
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
            deleteBidService.delete(req, user);
            return ResponseEntity.ok("Bid deleted");
        }catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    public ResponseEntity<?> getAll(@RequestBody GetAllBidsRequest req){
        try{
            return ResponseEntity.ok(getBidsOfProductService.get(req));
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
            return ResponseEntity.ok(getBidsOfUserService.get(user));
        }
        catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
