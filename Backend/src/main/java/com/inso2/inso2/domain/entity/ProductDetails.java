package com.inso2.inso2.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "productDetails")
public class ProductDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "IdProductDetails")
    private long idProductDetails;

    @Column(length=4)
    @Size(min=1, max=4)
    private String size;

    @Column(name = "HighestBid")
    private int highestBid;

    @Column(name = "LowestAsk")
    private int lowestAsk;

    @Column(name = "LastSale")
    private int lastSale;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdProduct")
    private Product product;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="productDetails")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Ask> asks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Bid> bids;


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

    public int getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(int highestBid) {
        this.highestBid = highestBid;
    }

    public int getLowestAsk() {
        return lowestAsk;
    }

    public void setLowestAsk(int lowestAsk) {
        this.lowestAsk = lowestAsk;
    }

    public int getLastSale() {
        return lastSale;
    }

    public void setLastSale(int lastSale) {
        this.lastSale = lastSale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
