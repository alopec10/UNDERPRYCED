package com.inso2.inso2.service.order;

import com.inso2.inso2.dto.order.create.CreateOrderSellRequest;
import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.*;
import com.inso2.inso2.service.shipment.CreateHomeShipmentService;
import com.inso2.inso2.service.shipment.CreateWarehouseShipmentService;
import com.inso2.inso2.service.user.LoadUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CreateSellService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final BidRepository bidRepository;
    private final CreateWarehouseShipmentService createWarehouseShipmentService;
    private final CreateHomeShipmentService createHomeShipmentService;

    public CreateSellService(OrderRepository orderRepository, ProductRepository productRepository, ProductDetailsRepository productDetailsRepository, PaymentMethodRepository paymentMethodRepository, BidRepository bidRepository, CreateWarehouseShipmentService createWarehouseShipmentService, CreateHomeShipmentService createHomeShipmentService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productDetailsRepository = productDetailsRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.bidRepository = bidRepository;
        this.createWarehouseShipmentService = createWarehouseShipmentService;
        this.createHomeShipmentService = createHomeShipmentService;
    }

    public void create(CreateOrderSellRequest req, User user) throws Exception{
        Product product = productRepository.findByRef(req.getRef());
        ProductDetails productDetails = productDetailsRepository.findByProductAndSize(product, req.getSize());
        Bid bid = bidRepository.findFirstByProductDetailsAndPriceOrderByIdBidAsc(productDetails, productDetails.getHighestBid());
        if(bid.getUser().equals(user)){
            throw new Exception("It's not possible to sell a product to yourself");
        }
        PaymentMethod sellerPaymentMethod = paymentMethodRepository.findByUserAndIdPayMethod(user, req.getIdPayMethod());
        PaymentMethod buyerPaymentMethod = paymentMethodRepository.findFirstByUserAndIsActiveOrderByIdPayMethodAsc(bid.getUser(), true);
        Order order = new Order(UUID.randomUUID().toString(),bid.getPrice(),new Date(),bid.getUser(), user, productDetails, buyerPaymentMethod, sellerPaymentMethod);
        orderRepository.saveAndFlush(order);
        bidRepository.deleteById(bid.getIdBid());
        Shipment warehouseShipment = createWarehouseShipmentService.create(order);
        createHomeShipmentService.create(order, warehouseShipment.getArrivalDate(), req.getAddress(), req.getZipCode(), req.getCountry());
    }
}
