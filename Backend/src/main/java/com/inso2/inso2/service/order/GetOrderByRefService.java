package com.inso2.inso2.service.order;

import com.inso2.inso2.dto.order.get.GetOrderInformationResponse;
import com.inso2.inso2.model.Order;
import com.inso2.inso2.model.ShipmentType;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class GetOrderByRefService {

    private final OrderRepository orderRepository;


    public GetOrderByRefService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public GetOrderInformationResponse get(User user, String ref) throws Exception{
        Order buyOrder = orderRepository.findByBuyerAndRef(user, ref);
        Order sellOrder = orderRepository.findBySellerAndRef(user, ref);
        if(buyOrder == null && sellOrder == null){
            throw new Exception("The order doesnt exists");
        }
        if(buyOrder != null){
            return new GetOrderInformationResponse().build(buyOrder, ShipmentType.HOME);
        }
        else{
            return new GetOrderInformationResponse().build(sellOrder, ShipmentType.WAREHOUSE);
        }
    }
}
