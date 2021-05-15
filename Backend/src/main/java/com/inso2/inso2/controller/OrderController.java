package com.inso2.inso2.controller;

import com.inso2.inso2.dto.order.approve.ApproveOrderRequest;
import com.inso2.inso2.dto.order.create.CreateOrderBuyRequest;
import com.inso2.inso2.dto.order.create.CreateOrderSellRequest;
import com.inso2.inso2.dto.order.get.GetOrderInformationResponse;
import com.inso2.inso2.dto.order.reject.RejectOrderRequest;
import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.*;
import com.inso2.inso2.service.order.*;
import com.inso2.inso2.service.user.LoadUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final LoadUserService loadUserService;
    private final CreateBuyService createBuyService;
    private final CreateSellService createSellService;
    private final GetPurchasesOfUserService getPurchasesOfUserService;
    private final GetSellsOfUserService getSellsOfUserService;
    private final ApproveOrderService approveOrderService;
    private final RejectOrderService rejectOrderService;
    private final GetPendingOrdersService getPendingOrdersService;
    private final ConfirmDeliveryService confirmDeliveryService;
    private final PriceCalculationService priceCalculationService;
    private final GetOrderByRefService getOrderByRefService;


    public OrderController(LoadUserService loadUserService, CreateBuyService createBuyService, CreateSellService createSellService, GetPurchasesOfUserService getPurchasesOfUserService, GetSellsOfUserService getSellsOfUserService, ApproveOrderService approveOrderService, RejectOrderService rejectOrderService, GetPendingOrdersService getPendingOrdersService, ConfirmDeliveryService confirmDeliveryService, PriceCalculationService priceCalculationService, GetOrderByRefService getOrderByRefService) {
        this.loadUserService = loadUserService;
        this.createBuyService = createBuyService;
        this.createSellService = createSellService;
        this.getPurchasesOfUserService = getPurchasesOfUserService;
        this.getSellsOfUserService = getSellsOfUserService;
        this.approveOrderService = approveOrderService;
        this.rejectOrderService = rejectOrderService;
        this.getPendingOrdersService = getPendingOrdersService;
        this.confirmDeliveryService = confirmDeliveryService;
        this.priceCalculationService = priceCalculationService;
        this.getOrderByRefService = getOrderByRefService;
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
            createSellService.create(req, user);
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
            return ResponseEntity.ok(getPurchasesOfUserService.get(user));
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
            return ResponseEntity.ok(getSellsOfUserService.get(user));
        }
        catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/getOrderByRef", method = RequestMethod.GET)
    public ResponseEntity<?> getOrderByRef(@RequestParam String ref){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            return ResponseEntity.ok(getOrderByRefService.get(user, ref));
        }
        catch (Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public ResponseEntity<?> approve(@RequestBody ApproveOrderRequest req){
        try{
            approveOrderService.approve(req);
            return ResponseEntity.ok("Order approved");
        }
        catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    public ResponseEntity<?> reject(@RequestBody RejectOrderRequest req){
        try{
            rejectOrderService.reject(req);
            return ResponseEntity.ok("Order rejected");
        }
        catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/getPending", method = RequestMethod.GET)
    public ResponseEntity<?> getPending(){
        try{
            return ResponseEntity.ok(getPendingOrdersService.get());
        }
        catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/confirmDelivery", method = RequestMethod.GET)
    public ResponseEntity<?> confirmDelivery(@RequestParam String ref){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = loadUserService.load(auth);
            confirmDeliveryService.confirm(user, ref);
            return ResponseEntity.ok("Delivery confirmed");
        }
        catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/getPriceSell", method = RequestMethod.GET)
    public ResponseEntity<?> getPriceSell(@RequestParam int price){
        try{
            return ResponseEntity.ok(priceCalculationService.calculateSell(price));
        }
        catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/getPriceBuy", method = RequestMethod.GET)
    public ResponseEntity<?> getPriceBuy(@RequestParam int price){
        try{
            return ResponseEntity.ok(priceCalculationService.calculateBuy(price));
        }
        catch(Exception e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
