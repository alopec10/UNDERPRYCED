package com.inso2.inso2.service.order;

import com.inso2.inso2.dto.order.get.GetOrderInformationResponse;
import com.inso2.inso2.model.Order;
import com.inso2.inso2.model.ShipmentType;
import com.inso2.inso2.model.User;
import com.inso2.inso2.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetSellsOfUserService {
    private final OrderRepository orderRepository;

    public GetSellsOfUserService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<GetOrderInformationResponse> get(User user){
        List<Order> purchases = orderRepository.findBySellerOrderByIdOrderDesc(user);
        List<GetOrderInformationResponse> response = new ArrayList<>();
        for(Order o:purchases){
            response.add(new GetOrderInformationResponse().build(o, ShipmentType.WAREHOUSE));
        }
        return response;
    }
}
