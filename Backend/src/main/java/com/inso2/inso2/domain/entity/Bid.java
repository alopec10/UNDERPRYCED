package com.inso2.inso2.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "IdBid")
    private long idBid;

    @Column(name = "Price")
    private int price;

    @Column(name = "Date", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "Completed")
    private boolean completed;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdUser")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdProductDetails")
    private ProductDetails productDetails;

    public Bid() {
    }

    public Bid(int price, Date date, boolean completed, User user, ProductDetails productDetails) {
        this.price = price;
        this.date = date;
        this.completed = completed;
        this.user = user;
        this.productDetails = productDetails;
    }

    public long getIdBid() {
        return idBid;
    }

    public void setIdBid(long idBid) {
        this.idBid = idBid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }
}
