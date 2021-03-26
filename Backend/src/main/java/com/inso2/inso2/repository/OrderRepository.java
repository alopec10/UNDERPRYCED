package com.inso2.inso2.repository;

import com.inso2.inso2.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
