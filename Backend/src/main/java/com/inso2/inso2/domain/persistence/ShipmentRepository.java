package com.inso2.inso2.domain.persistence;

import com.inso2.inso2.domain.entity.Shipment;
import org.springframework.data.repository.CrudRepository;

public interface ShipmentRepository extends CrudRepository<Shipment, Long> {
}
