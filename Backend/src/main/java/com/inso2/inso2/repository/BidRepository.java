package com.inso2.inso2.repository;

import com.inso2.inso2.model.Ask;
import com.inso2.inso2.model.Bid;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    Bid findByUserAndProductDetails(User user, ProductDetails productDetails);
    @Query("SELECT b.price FROM Bid b WHERE b.productDetails = ?1")
    List<Integer> findPriceByProductDetails(ProductDetails productDetails);
}
