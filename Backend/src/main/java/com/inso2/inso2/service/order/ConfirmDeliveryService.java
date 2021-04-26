package com.inso2.inso2.service.order;

import com.inso2.inso2.model.*;
import com.inso2.inso2.repository.OrderRepository;
import com.inso2.inso2.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConfirmDeliveryService {
    private final OrderRepository orderRepository;
    private final ShipmentRepository shipmentRepository;

    public ConfirmDeliveryService(OrderRepository orderRepository, ShipmentRepository shipmentRepository) {
        this.orderRepository = orderRepository;
        this.shipmentRepository = shipmentRepository;
    }

    public void confirm(User user, String ref) throws Exception{
        Order order = orderRepository.findByBuyerAndRef(user, ref);
        if(order.getStatus() != Status.ON_WAY){
            throw new Exception("It's not possible to confirm an order that is not on way");
        }
        order.setStatus(Status.COMPLETED);
        orderRepository.saveAndFlush(order);
        Shipment homeShipment = this.getShipment(order.getShipments(), ShipmentType.HOME);
        homeShipment.setCompleted(true);
        homeShipment.setStatus(Status.COMPLETED);
        homeShipment.setArrivalDate(new Date());
        shipmentRepository.saveAndFlush(homeShipment);
    }

    private Shipment getShipment(List<Shipment> shipments, ShipmentType shipmentType){
        for(Shipment s: shipments){
            if (s.getType() == shipmentType){
                return s;
            }
        }
        return null;
    }
}
