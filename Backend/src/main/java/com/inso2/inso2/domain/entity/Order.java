package com.inso2.inso2.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "IdOrder")
    private long idOrder;

    @Column(name = "Price")
    private int price;

    @Column(name = "Date", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdBuyer")
    private User buyer;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdSeller")
    private User seller;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdProductDetails")
    private ProductDetails productDetails;

    @OneToOne(mappedBy = "order")
    private Shipment shipment;

    public Order() {
    }

    public Order(int price, Date date, User buyer, User seller, ProductDetails productDetails) {
        this.price = price;
        this.date = date;
        this.buyer = buyer;
        this.seller = seller;
        this.productDetails = productDetails;
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
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

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}
