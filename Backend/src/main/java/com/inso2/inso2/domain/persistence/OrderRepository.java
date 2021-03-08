package com.inso2.inso2.domain.persistence;

import com.inso2.inso2.domain.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
