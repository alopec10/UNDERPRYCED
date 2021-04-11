package com.inso2.inso2.repository;

import com.inso2.inso2.model.Ask;
import com.inso2.inso2.model.Bid;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
    Bid findByUserAndProductDetails(User user, ProductDetails productDetails);
}
