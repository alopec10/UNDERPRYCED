package com.inso2.inso2.repository;

import com.inso2.inso2.model.Order;
import com.inso2.inso2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByBuyerOrderByIdOrderDesc(User buyer);
    List<Order> findBySellerOrderByIdOrderDesc(User seller);
}
