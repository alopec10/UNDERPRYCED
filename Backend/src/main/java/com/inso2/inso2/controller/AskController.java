package com.inso2.inso2.controller;

import com.inso2.inso2.dto.ask.AskRequest;
import com.inso2.inso2.dto.ask.GetAllAsksByUserResponse;
import com.inso2.inso2.dto.ask.delete.DeleteAskRequest;
import com.inso2.inso2.dto.ask.getAll.GetAllAsksRequest;
import com.inso2.inso2.dto.ask.getAll.GetAllAsksResponse;
import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.AskRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import com.inso2.inso2.repository.ProductRepository;
import com.inso2.inso2.repository.UserRepository;
import com.inso2.inso2.service.ask.CreateAskService;
import com.inso2.inso2.service.ask.ModifyAskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/ask")
public class AskController {

    private ProductDetailsRepository productDetailsRepository;

    private UserRepository userRepository;

    private AskRepository askRepository;

    private ProductRepository productRepository;

    private CreateAskService createAskService;

    private ModifyAskService modifyAskService;

    public AskController(ProductDetailsRepository productDetailsRepository, UserRepository userRepository, AskRepository askRepository, ProductRepository productRepository, CreateAskService createAskService, ModifyAskService modifyAskService) {
        this.productDetailsRepository = productDetailsRepository;
        this.userRepository = userRepository;
        this.askRepository = askRepository;
        this.productRepository = productRepository;
        this.createAskService = createAskService;
        this.modifyAskService = modifyAskService;
    }

    @RequestMapping(value = "/make", method = RequestMethod.POST)
    public ResponseEntity<?> makeAsk(@RequestBody AskRequest req){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email);
            Product product = productRepository.findByRef(req.getRef());
            ProductDetails productDetails = productDetailsRepository.findByProductAndSize(product, req.getSize());
            Ask ask = askRepository.findByUserAndProductDetails(user, productDetails);
            if(ask == null){
                return createAskService.create(req.getPrice(), user, productDetails);
            }
            else {
                return modifyAskService.modify(req.getPrice(), productDetails, ask);
            }
        }catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<?> delete(@RequestBody DeleteAskRequest req){
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
            askRepository.deleteById(ask.getIdAsk());
            return ResponseEntity.ok("Ask deleted");
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
            List<Integer> prices = askRepository.findPriceByProductDetails(productDetails);
            Set<Integer> mySet = new HashSet<>(prices);
            List<GetAllAsksResponse> asks = new ArrayList<>();
            for(Integer i: mySet){
                asks.add(new GetAllAsksResponse(i, Collections.frequency(prices,i)));
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
            List<Ask> asks = askRepository.findByUser(user);
            List<GetAllAsksByUserResponse> aresp = new ArrayList<>();
            for(Ask a: asks){
                aresp.add(new GetAllAsksByUserResponse().build(a));
            }
            return ResponseEntity.ok(aresp);
        }
        catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
