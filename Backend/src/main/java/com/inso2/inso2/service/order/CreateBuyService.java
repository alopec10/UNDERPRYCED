package com.inso2.inso2.service.order;

import com.inso2.inso2.dto.order.create.CreateOrderBuyRequest;
import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.*;
import com.inso2.inso2.service.CreateWarehouseShipmentService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CreateBuyService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final AskRepository askRepository;
    private final CreateWarehouseShipmentService createWarehouseShipmentService;

    public CreateBuyService(OrderRepository orderRepository, ProductRepository productRepository, ProductDetailsRepository productDetailsRepository, PaymentMethodRepository paymentMethodRepository, AskRepository askRepository, CreateWarehouseShipmentService createWarehouseShipmentService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productDetailsRepository = productDetailsRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.askRepository = askRepository;
        this.createWarehouseShipmentService = createWarehouseShipmentService;
    }

    public void create(CreateOrderBuyRequest req, User user) throws Exception{
        if(!this.validateShipmentData(req.getAddress(), req.getCountry(), req.getZipCode())){
            throw new Exception("Invalid shipment data");
        }
        Product product = productRepository.findByRef(req.getRef());
        ProductDetails productDetails = productDetailsRepository.findByProductAndSize(product, req.getSize());
        Ask ask = askRepository.findFirstByProductDetailsAndPriceOrderByIdAskAsc(productDetails, productDetails.getLowestAsk());
        if(ask.getUser().equals(user)){
            throw new Exception("It's not possible to buy your own product");
        }
        PaymentMethod buyerPaymentMethod = paymentMethodRepository.findByUserAndIdPayMethod(user, req.getIdPayMethod());
        PaymentMethod sellerPaymentMethod = paymentMethodRepository.findFirstByUserAndIsActiveOrderByIdPayMethodAsc(ask.getUser(), true);
        Order order = new Order(UUID.randomUUID().toString(),ask.getPrice(),new Date(),user, ask.getUser(), productDetails, buyerPaymentMethod, sellerPaymentMethod);
        orderRepository.saveAndFlush(order);
        askRepository.deleteById(ask.getIdAsk());
        Shipment warehouseShipment = createWarehouseShipmentService.create(order);
    }

    private boolean validateShipmentData(String address, String country, String zipCode){
        return isAddressValid(address) && isCountryValid(country) && isZipCodeValid(zipCode);
    }

    private boolean isAddressValid(String address){
        Pattern pattern = Pattern.compile(".{10,150}");
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

    private boolean isCountryValid(String country){
        Pattern pattern = Pattern.compile("^[A-ZÀ-Ö][A-Za-zÀ-ÖØ-öø-ÿ\\s\\-]+$");
        Matcher matcher = pattern.matcher(country);
        return matcher.matches();
    }

    private boolean isZipCodeValid(String zipCode){
        Pattern pattern = Pattern.compile("^\\d{5}(?:[-\\s]\\d{4})?$");
        Matcher matcher = pattern.matcher(zipCode);
        return matcher.matches();
    }
}
