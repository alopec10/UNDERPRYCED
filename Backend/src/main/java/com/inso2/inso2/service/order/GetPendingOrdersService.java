package com.inso2.inso2.service.order;

import com.inso2.inso2.dto.order.get.GetPendingOrderResponse;
import com.inso2.inso2.model.Order;
import com.inso2.inso2.model.Status;
import com.inso2.inso2.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetPendingOrdersService {
    private final OrderRepository orderRepository;

    public GetPendingOrdersService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<GetPendingOrderResponse> get(){
        List<Order> orders= orderRepository.findByStatus(Status.PENDING_APPROVAL);
        List<GetPendingOrderResponse> resp = new ArrayList<>();
        for(Order o: orders){
            resp.add(new GetPendingOrderResponse().build(o));
        }
        return resp;
    }
}
