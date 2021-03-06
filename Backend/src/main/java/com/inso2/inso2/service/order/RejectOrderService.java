package com.inso2.inso2.service.order;

import com.inso2.inso2.dto.order.reject.RejectOrderRequest;
import com.inso2.inso2.model.Order;
import com.inso2.inso2.model.Shipment;
import com.inso2.inso2.model.Status;
import com.inso2.inso2.model.ShipmentType;
import com.inso2.inso2.repository.OrderRepository;
import com.inso2.inso2.repository.ShipmentRepository;
import com.inso2.inso2.service.alert.CreateAlertService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RejectOrderService {
    private final OrderRepository orderRepository;
    private final ShipmentRepository shipmentRepository;
    private final CreateAlertService createAlertService;

    public RejectOrderService(OrderRepository orderRepository, ShipmentRepository shipmentRepository, CreateAlertService createAlertService) {
        this.orderRepository = orderRepository;
        this.shipmentRepository = shipmentRepository;
        this.createAlertService = createAlertService;
    }

    public void reject(RejectOrderRequest req){
        Order order = orderRepository.findByRef(req.getOrderRef());
        Shipment warehouseShipment = this.getWarehouseShipment(order);
        Shipment homeShipment = this.getHomeShipment(order);
        warehouseShipment.setApproved(false);
        warehouseShipment.setCompleted(true);
        warehouseShipment.setStatus(Status.CANCELLED);
        this.shipmentRepository.saveAndFlush(warehouseShipment);
        homeShipment.setSent(false);
        homeShipment.setCompleted(true);
        homeShipment.setStatus(Status.CANCELLED);
        this.shipmentRepository.saveAndFlush(homeShipment);
        order.setStatus(Status.CANCELLED);
        this.orderRepository.save(order);
        createAlertService.createBuyRejectedAlert(order);
        createAlertService.createSellRejectedAlert(order);
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
