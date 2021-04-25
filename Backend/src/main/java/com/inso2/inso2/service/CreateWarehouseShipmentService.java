package com.inso2.inso2.service;

import com.inso2.inso2.model.Order;
import com.inso2.inso2.model.Shipment;
import com.inso2.inso2.model.ShipmentType;
import com.inso2.inso2.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
public class CreateWarehouseShipmentService {

    private final ShipmentRepository shipmentRepository;
    private static final String ADDRESS = "Poligono Industrial Fase 2, 3G., Calle 18, Ribaseca, León";
    private static final String ZIP_CODE = "24391";
    private static final String COUNTRY = "Spain";

    public CreateWarehouseShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public Shipment create(Order order){
        Shipment shipment = new Shipment();
        shipment.setTrackingNumber(UUID.randomUUID().toString());
        shipment.setShipDate(new Date());
        shipment.setArrivalDate(this.calculateDatePeriod(3));
        shipment.setAddress(ADDRESS);
        shipment.setZipCode(ZIP_CODE);
        shipment.setCountry(COUNTRY);
        shipment.setCompleted(false);
        shipment.setSent(true);
        shipment.setApproved(false);
        shipment.setType(ShipmentType.WAREHOUSE);
        shipment.setOrder(order);
        shipmentRepository.saveAndFlush(shipment);
        return shipment;
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

    private Date calculateDatePeriod(long businessDays){
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(getAllDays(startDate.getDayOfWeek().getValue(), businessDays));
        return Date.from(endDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
