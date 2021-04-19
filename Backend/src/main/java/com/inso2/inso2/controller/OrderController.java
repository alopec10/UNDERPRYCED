package com.inso2.inso2.controller;

import com.inso2.inso2.dto.order.create.CreateOrderBuyRequest;
import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.*;
import com.inso2.inso2.service.user.LoadUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderRepository orderRepository;
    private LoadUserService loadUserService;
    private ProductRepository productRepository;
    private ProductDetailsRepository productDetailsRepository;
    PaymentMethodRepository paymentMethodRepository;
    private AskRepository askRepository;

    public OrderController(OrderRepository orderRepository, LoadUserService loadUserService, ProductRepository productRepository, ProductDetailsRepository productDetailsRepository, PaymentMethodRepository paymentMethodRepository, AskRepository askRepository) {
        this.orderRepository = orderRepository;
        this.loadUserService = loadUserService;
        this.productRepository = productRepository;
        this.productDetailsRepository = productDetailsRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.askRepository = askRepository;
    }

    @RequestMapping(value = "/createBuy", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody CreateOrderBuyRequest req){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            Product product = productRepository.findByRef(req.getRef());
            ProductDetails productDetails = productDetailsRepository.findByProductAndSize(product, req.getSize());
            Ask ask = askRepository.findFirstByProductDetailsAndPriceOrderByIdAskAsc(productDetails, productDetails.getLowestAsk());
            if(ask.getUser().equals(user)){
                return new ResponseEntity<>(
                        "It's not possible to buy your own product",
                        HttpStatus.SERVICE_UNAVAILABLE);
            }
            PaymentMethod buyerPaymentMethod = paymentMethodRepository.findByUserAndIdPayMethod(user, req.getIdPayMethod());
            PaymentMethod sellerPaymentMethod = paymentMethodRepository.findFirstByUserOrderByIdPayMethodAsc(ask.getUser());
            Order order = new Order(UUID.randomUUID().toString(),ask.getPrice(),new Date(),user, ask.getUser(), productDetails, buyerPaymentMethod, sellerPaymentMethod);
            orderRepository.saveAndFlush(order);
            askRepository.deleteById(ask.getIdAsk());
            return ResponseEntity.ok("Order created");
        }
        catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
