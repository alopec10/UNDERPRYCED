package com.inso2.inso2.repository;

import com.inso2.inso2.model.Bid;
import com.inso2.inso2.model.ProductDetails;
import com.inso2.inso2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {
    Bid findByUserAndProductDetails(User user, ProductDetails productDetails);
    List<Bid> findByUser(User user);
    Bid findFirstByProductDetailsAndPriceOrderByIdBidAsc(ProductDetails productDetails, int price);
    @Query("SELECT b.price FROM Bid b WHERE b.productDetails = ?1 ORDER BY b.price DESC")
    List<Integer> findPriceByProductDetails(ProductDetails productDetails);
}
