package com.inso2.inso2.service.order;

import com.inso2.inso2.dto.order.create.CreateOrderSellRequest;
import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.*;
import com.inso2.inso2.service.alert.CreateAlertService;
import com.inso2.inso2.service.shipment.CreateHomeShipmentService;
import com.inso2.inso2.service.shipment.CreateWarehouseShipmentService;
import com.inso2.inso2.service.user.LoadUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private final CreateAlertService createAlertService;

    public CreateSellService(OrderRepository orderRepository, ProductRepository productRepository, ProductDetailsRepository productDetailsRepository, PaymentMethodRepository paymentMethodRepository, BidRepository bidRepository, CreateWarehouseShipmentService createWarehouseShipmentService, CreateHomeShipmentService createHomeShipmentService, CreateAlertService createAlertService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productDetailsRepository = productDetailsRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.bidRepository = bidRepository;
        this.createWarehouseShipmentService = createWarehouseShipmentService;
        this.createHomeShipmentService = createHomeShipmentService;
        this.createAlertService = createAlertService;
    }

    public void create(CreateOrderSellRequest req, User user) throws Exception{
        Product product = productRepository.findByRef(req.getRef());
        ProductDetails productDetails = productDetailsRepository.findByProductAndSize(product, req.getSize());
        Bid bid = bidRepository.findFirstByProductDetailsAndPriceOrderByIdBidAsc(productDetails, productDetails.getHighestBid());
        User buyer = bid.getUser();
        if(bid.getUser().equals(user)){
            throw new Exception("It's not possible to sell a product to yourself");
        }
        PaymentMethod sellerPaymentMethod = paymentMethodRepository.findByUserAndIdPayMethod(user, req.getIdPayMethod());
        PaymentMethod buyerPaymentMethod = paymentMethodRepository.findFirstByUserAndIsActiveOrderByIdPayMethodAsc(bid.getUser(), true);
        Order order = new Order(UUID.randomUUID().toString(),bid.getPrice(), calcPriceSeller(bid.getPrice()), calcPriceBuyer(bid.getPrice()), new Date(),bid.getUser(), user, productDetails, buyerPaymentMethod, sellerPaymentMethod, Status.PENDING_APPROVAL);
        orderRepository.saveAndFlush(order);
        bidRepository.deleteById(bid.getIdBid());
        Shipment warehouseShipment = createWarehouseShipmentService.create(order);
        createHomeShipmentService.create(order, warehouseShipment.getArrivalDate(), buyer.getAddress(), buyer.getZipCode(), buyer.getCountry());
        createAlertService.createBuyAlert(order);
    }

    private BigDecimal calcPriceBuyer (int price) {
        BigDecimal pr = new BigDecimal(price);
        BigDecimal ONE_HUNDRED = new BigDecimal(100);
        BigDecimal TEN = new BigDecimal(10);
        BigDecimal FIFTEEN = new BigDecimal(15);
        BigDecimal percent = pr.multiply(TEN).divide(ONE_HUNDRED, RoundingMode.HALF_UP);
        return pr.add(percent).add(FIFTEEN);
    }

    private BigDecimal calcPriceSeller (int price) {
        BigDecimal pr = new BigDecimal(price);
        BigDecimal ONE_HUNDRED = new BigDecimal(100);
        BigDecimal TEN = new BigDecimal(10);
        BigDecimal FIFTEEN = new BigDecimal(15);
        BigDecimal percent = pr.multiply(TEN).divide(ONE_HUNDRED, RoundingMode.HALF_UP);
        return pr.subtract(percent).subtract(FIFTEEN);
    }
}
