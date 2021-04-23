package com.inso2.inso2.controller;

import com.inso2.inso2.dto.order.create.CreateOrderBuyRequest;
import com.inso2.inso2.dto.order.create.CreateOrderSellRequest;
import com.inso2.inso2.dto.order.get.GetOrderInformationResponse;
import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.*;
import com.inso2.inso2.service.order.CreateBuyService;
import com.inso2.inso2.service.user.LoadUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderRepository orderRepository;
    private final LoadUserService loadUserService;
    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final AskRepository askRepository;
    private final BidRepository bidRepository;
    private final CreateBuyService createBuyService;

    public OrderController(OrderRepository orderRepository, LoadUserService loadUserService, ProductRepository productRepository, ProductDetailsRepository productDetailsRepository, PaymentMethodRepository paymentMethodRepository, AskRepository askRepository, BidRepository bidRepository, CreateBuyService createBuyService) {
        this.orderRepository = orderRepository;
        this.loadUserService = loadUserService;
        this.productRepository = productRepository;
        this.productDetailsRepository = productDetailsRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.askRepository = askRepository;
        this.bidRepository = bidRepository;
        this.createBuyService = createBuyService;
    }

    @RequestMapping(value = "/createBuy", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody CreateOrderBuyRequest req){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            createBuyService.create(req, user);
            return ResponseEntity.ok("Order created");
        }
        catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/createSell", method = RequestMethod.POST)
    public ResponseEntity<?> createSell(@RequestBody CreateOrderSellRequest req){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            Product product = productRepository.findByRef(req.getRef());
            ProductDetails productDetails = productDetailsRepository.findByProductAndSize(product, req.getSize());
            Bid bid = bidRepository.findFirstByProductDetailsAndPriceOrderByIdBidAsc(productDetails, productDetails.getHighestBid());
            if(bid.getUser().equals(user)){
                return new ResponseEntity<>(
                        "It's not possible to sell a product to yourself",
                        HttpStatus.SERVICE_UNAVAILABLE);
            }
            PaymentMethod sellerPaymentMethod = paymentMethodRepository.findByUserAndIdPayMethod(user, req.getIdPayMethod());
            PaymentMethod buyerPaymentMethod = paymentMethodRepository.findFirstByUserAndIsActiveOrderByIdPayMethodAsc(bid.getUser(), true);
            Order order = new Order(UUID.randomUUID().toString(),bid.getPrice(),new Date(),bid.getUser(), user, productDetails, buyerPaymentMethod, sellerPaymentMethod);
            orderRepository.saveAndFlush(order);
            bidRepository.deleteById(bid.getIdBid());
            return ResponseEntity.ok("Order created");
        }
        catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/getPurchases", method = RequestMethod.GET)
    public ResponseEntity<?> getPurchases(){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            List<Order> purchases = orderRepository.findByBuyerOrderByIdOrderDesc(user);
            List<GetOrderInformationResponse> response = new ArrayList<>();
            for(Order o:purchases){
                response.add(new GetOrderInformationResponse().build(o));
            }
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/getSells", method = RequestMethod.GET)
    public ResponseEntity<?> getSells(){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            List<Order> purchases = orderRepository.findBySellerOrderByIdOrderDesc(user);
            List<GetOrderInformationResponse> response = new ArrayList<>();
            for(Order o:purchases){
                response.add(new GetOrderInformationResponse().build(o));
            }
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
