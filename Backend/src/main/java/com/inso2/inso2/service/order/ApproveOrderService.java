package com.inso2.inso2.service.order;

import com.inso2.inso2.dto.order.approve.ApproveOrderRequest;
import com.inso2.inso2.model.Order;
import com.inso2.inso2.model.Shipment;
import com.inso2.inso2.model.ShipmentStatus;
import com.inso2.inso2.model.ShipmentType;
import com.inso2.inso2.repository.OrderRepository;
import com.inso2.inso2.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApproveOrderService {

    private final OrderRepository orderRepository;
    private final ShipmentRepository shipmentRepository;

    public ApproveOrderService(OrderRepository orderRepository, ShipmentRepository shipmentRepository) {
        this.orderRepository = orderRepository;
        this.shipmentRepository = shipmentRepository;
    }

    public void approve(ApproveOrderRequest req){
        Order order = orderRepository.findByRef(req.getOrderRef());
        Shipment warehouseShipment = this.getWarehouseShipment(order);
        Shipment homeShipment = this.getHomeShipment(order);
        warehouseShipment.setApproved(true);
        warehouseShipment.setCompleted(true);
        warehouseShipment.setStatus(ShipmentStatus.COMPLETED);
        this.shipmentRepository.saveAndFlush(warehouseShipment);
        homeShipment.setSent(true);
        homeShipment.setApproved(true);
        homeShipment.setStatus(ShipmentStatus.ON_WAY);
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
