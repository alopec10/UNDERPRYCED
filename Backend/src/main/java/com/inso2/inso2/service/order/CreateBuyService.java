package com.inso2.inso2.service.order;

import com.inso2.inso2.dto.order.create.CreateOrderBuyRequest;
import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.*;
import com.inso2.inso2.service.user.LoadUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CreateBuyService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final AskRepository askRepository;

    public CreateBuyService(OrderRepository orderRepository, ProductRepository productRepository, ProductDetailsRepository productDetailsRepository, PaymentMethodRepository paymentMethodRepository, AskRepository askRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productDetailsRepository = productDetailsRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.askRepository = askRepository;
    }

    public void create(CreateOrderBuyRequest req, User user) throws Exception{
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
    }
}
