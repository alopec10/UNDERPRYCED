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
    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AskRepository askRepository;

    @RequestMapping(value = "/make", method = RequestMethod.POST)
    public ResponseEntity<?> makeAsk(@RequestBody AskRequest req){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String email = userDetails.getUsername();
            User user = userRepository.findByEmail(email);
            ProductDetails productDetails = productDetailsRepository.findByIdProductDetails(req.getIdProductDetails());
            Ask ask = new Ask(req.getPrice(), new Date(),user, productDetails);
            askRepository.saveAndFlush(ask);
            if(productDetails.getLowestAsk() == null || productDetails.getLowestAsk() > req.getPrice()){
                ProductDetails pDetails = productDetailsRepository.findByIdProductDetails(req.getIdProductDetails());
                pDetails.setLowestAsk(req.getPrice());
                productDetailsRepository.save(pDetails);
            }
            return ResponseEntity.ok("Ask created");
        }catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }

    }


}
