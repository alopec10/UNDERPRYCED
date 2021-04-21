package com.inso2.inso2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "productDetails")
public class ProductDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "IdProductDetails")
    private long idProductDetails;

    @Column(name  = "Size", length=4, nullable = false)
    @Size(min=1, max=4)
    private String size;

    @Column(name = "HighestBid")
    private Integer highestBid;

    @Column(name = "LowestAsk")
    private Integer lowestAsk;

    @Column(name = "LastSale")
    private Integer lastSale;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdProduct")
    private Product product;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="productDetails")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Ask> asks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="productDetails")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Bid> bids;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="productDetails")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Order> orders;

    public ProductDetails() {
    }

    public ProductDetails(@Size(min = 1, max = 4) String size, int highestBid, int lowestAsk, int lastSale, Product product) {
        this.size = size;
        this.highestBid = highestBid;
        this.lowestAsk = lowestAsk;
        this.lastSale = lastSale;
        this.product = product;
    }

    public long getIdProductDetails() {
        return idProductDetails;
    }

    public void setIdProductDetails(long idProductDetails) {
        this.idProductDetails = idProductDetails;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(Integer highestBid) {
        this.highestBid = highestBid;
    }

    public Integer getLowestAsk() {
        return lowestAsk;
    }

    public void setLowestAsk(Integer lowestAsk) {
        this.lowestAsk = lowestAsk;
    }

    public Integer getLastSale() {
        return lastSale;
    }

    public void setLastSale(Integer lastSale) {
        this.lastSale = lastSale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Ask> getAsks() {
        return asks;
    }

    public void setAsks(List<Ask> asks) {
        this.asks = asks;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "idProductDetails=" + idProductDetails +
                ", size='" + size + '\'' +
                ", highestBid=" + highestBid +
                ", lowestAsk=" + lowestAsk +
                ", lastSale=" + lastSale +
                ", product=" + product +
                ", asks=" + asks +
                ", bids=" + bids +
                ", orders=" + orders +
                '}';
    }
}
