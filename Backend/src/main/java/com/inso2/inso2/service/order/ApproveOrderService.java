package com.inso2.inso2.service.order;

import com.inso2.inso2.dto.order.approve.ApproveOrderRequest;
import com.inso2.inso2.model.Order;
import com.inso2.inso2.model.Shipment;
import com.inso2.inso2.model.Status;
import com.inso2.inso2.model.ShipmentType;
import com.inso2.inso2.repository.OrderRepository;
import com.inso2.inso2.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
        warehouseShipment.setStatus(Status.COMPLETED);
        this.shipmentRepository.saveAndFlush(warehouseShipment);
        homeShipment.setSent(true);
        homeShipment.setShipDate(new Date());
        homeShipment.setArrivalDate(this.calculateDatePeriod(new Date(),3));
        homeShipment.setApproved(true);
        homeShipment.setStatus(Status.ON_WAY);
        this.shipmentRepository.saveAndFlush(homeShipment);
        order.setStatus(Status.ON_WAY);
        this.orderRepository.save(order);
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

    private long getAllDays(int dayOfWeek, long businessDays) {
        long result = 0;
        if (businessDays != 0) {
            boolean isStartOnWorkday = dayOfWeek < 6;
            long absBusinessDays = Math.abs(businessDays);

            if (isStartOnWorkday) {
                // if negative businessDays: count backwards by shifting weekday
                int shiftedWorkday = businessDays > 0 ? dayOfWeek : 6 - dayOfWeek;
                result = absBusinessDays + (absBusinessDays + shiftedWorkday - 1) / 5 * 2;
            } else { // start on weekend
                // if negative businessDays: count backwards by shifting weekday
                int shiftedWeekend = businessDays > 0 ? dayOfWeek : 13 - dayOfWeek;
                result = absBusinessDays + (absBusinessDays - 1) / 5 * 2 + (7 - shiftedWeekend);
            }
        }
        return result;
    }

    private Date calculateDatePeriod(Date date, long businessDays){
        LocalDate startDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate endDate = startDate.plusDays(getAllDays(startDate.getDayOfWeek().getValue(), businessDays));
        return Date.from(endDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
