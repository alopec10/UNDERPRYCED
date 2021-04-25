package com.inso2.inso2.service.order;

import com.inso2.inso2.dto.order.reject.RejectOrderRequest;
import com.inso2.inso2.model.Order;
import com.inso2.inso2.model.Shipment;
import com.inso2.inso2.model.ShipmentStatus;
import com.inso2.inso2.model.ShipmentType;
import com.inso2.inso2.repository.OrderRepository;
import com.inso2.inso2.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RejectOrderService {
    private final OrderRepository orderRepository;
    private final ShipmentRepository shipmentRepository;

    public RejectOrderService(OrderRepository orderRepository, ShipmentRepository shipmentRepository) {
        this.orderRepository = orderRepository;
        this.shipmentRepository = shipmentRepository;
    }

    public void reject(RejectOrderRequest req){
        Order order = orderRepository.findByRef(req.getOrderRef());
        Shipment warehouseShipment = this.getWarehouseShipment(order);
        Shipment homeShipment = this.getHomeShipment(order);
        warehouseShipment.setApproved(false);
        warehouseShipment.setCompleted(true);
        warehouseShipment.setStatus(ShipmentStatus.CANCELLED);
        this.shipmentRepository.saveAndFlush(warehouseShipment);
        homeShipment.setSent(false);
        homeShipment.setCompleted(false);
        homeShipment.setStatus(ShipmentStatus.CANCELLED);
        this.shipmentRepository.saveAndFlush(homeShipment);
    }

    private Shipment getWarehouseShipment(Order o){
        List<Shipment> shipments = o.getShipments();
        for(Shipment s:shipments){
            if(s.getType() == ShipmentType.WAREHOUSE){
                return s;
            }
        }
        return null;
    }

    private Shipment getHomeShipment(Order o){
        List<Shipment> shipments = o.getShipments();
        for(Shipment s:shipments){
            if(s.getType() == ShipmentType.HOME){
                return s;
            }
        }
        return null;
    }
}
