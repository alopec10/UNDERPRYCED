package com.inso2.inso2.controller;

import com.inso2.inso2.dto.order.create.CreateOrderBuyRequest;
import com.inso2.inso2.model.Product;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.AskRepository;
import com.inso2.inso2.repository.OrderRepository;
import com.inso2.inso2.repository.ProductDetailsRepository;
import com.inso2.inso2.repository.ProductRepository;
import com.inso2.inso2.service.user.LoadUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderRepository orderRepository;
    private LoadUserService loadUserService;
    private ProductRepository productRepository;
    private ProductDetailsRepository productDetailsRepository;
    private AskRepository askRepository;

    @RequestMapping(value = "/createBuy", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody CreateOrderBuyRequest req){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            Product product = productRepository.findByRef(req.getRef());
            ProductDetails productDetails = productDetailsRepository.findByProductAndSize(product, req.getSize());
            return ResponseEntity.ok("Order created");
        }
        catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
