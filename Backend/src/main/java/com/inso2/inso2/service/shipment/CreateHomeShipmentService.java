package com.inso2.inso2.service.shipment;

import com.inso2.inso2.model.Order;
import com.inso2.inso2.model.Shipment;
import com.inso2.inso2.model.ShipmentStatus;
import com.inso2.inso2.model.ShipmentType;
import com.inso2.inso2.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
public class CreateHomeShipmentService {
    private final ShipmentRepository shipmentRepository;

    public CreateHomeShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public void create(Order order, Date shipDate, String address, String zipCode, String country){
        Shipment shipment = new Shipment();
        shipment.setTrackingNumber(UUID.randomUUID().toString());
        shipment.setShipDate(shipDate);
        shipment.setArrivalDate(this.calculateDatePeriod(shipDate,3));
        shipment.setAddress(address);
        shipment.setZipCode(zipCode);
        shipment.setCountry(country);
        shipment.setCompleted(false);
        shipment.setSent(false);
        shipment.setApproved(false);
        shipment.setType(ShipmentType.HOME);
        shipment.setOrder(order);
        shipment.setStatus(ShipmentStatus.PENDING_APPROVAL);
        shipmentRepository.saveAndFlush(shipment);
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
