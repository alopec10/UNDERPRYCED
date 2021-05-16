package com.inso2.inso2.service.order;

import com.inso2.inso2.dto.order.get.GetPendingOrderResponse;
import com.inso2.inso2.model.Order;
import com.inso2.inso2.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPendingOrderByRefService {

    private final OrderRepository orderRepository;

    public GetPendingOrderByRefService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public GetPendingOrderResponse get(String ref) throws Exception {
         Order order = orderRepository.findByRef(ref);
         if (order == null) {
             throw new Exception("The order doesnt exist");
         }
         return new GetPendingOrderResponse().build(order);

    }
}
