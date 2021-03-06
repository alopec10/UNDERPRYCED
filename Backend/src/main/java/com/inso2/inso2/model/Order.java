package com.inso2.inso2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IdOrder")
    private long idOrder;

    @Size(min = 1, max = 100)
    @Column(name = "Ref", length = 100)
    private String ref;

    @Column(name = "Price", nullable = false)
    private int price;

    @Digits(integer = 6, fraction = 2)
    @Column(name = "PriceSeller", nullable = false)
    private BigDecimal priceSeller;

    @Digits(integer = 6, fraction = 2)
    @Column(name = "PriceBuyer", nullable = false)
    private BigDecimal priceBuyer;

    @Column(name = "Date", columnDefinition = "DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdBuyer", nullable = false)
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdSeller", nullable = false)
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdProductDetails", nullable = false)
    private ProductDetails productDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Shipment> shipments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPayMethodBuyer", nullable = false)
    private PaymentMethod paymentMethodBuyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPayMethodSeller", nullable = false)
    private PaymentMethod paymentMethodSeller;


    public Order() {
    }

    public Order(@Size(min = 1, max = 100) String ref, int price, BigDecimal priceSeller, BigDecimal priceBuyer, Date date, User buyer, User seller, ProductDetails productDetails, PaymentMethod paymentMethodBuyer, PaymentMethod paymentMethodSeller, Status status) {
        this.ref = ref;
        this.price = price;
        this.priceSeller = priceSeller;
        this.priceBuyer = priceBuyer;
        this.date = date;
        this.buyer = buyer;
        this.seller = seller;
        this.productDetails = productDetails;
        this.paymentMethodBuyer = paymentMethodBuyer;
        this.paymentMethodSeller = paymentMethodSeller;
        this.status = status;
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

    public BigDecimal getPriceSeller() {
        return priceSeller;
    }

    public void setPriceSeller(BigDecimal priceSeller) {
        this.priceSeller = priceSeller;
    }

    public BigDecimal getPriceBuyer() {
        return priceBuyer;
    }

    public void setPriceBuyer(BigDecimal priceBuyer) {
        this.priceBuyer = priceBuyer;
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

    public List<Shipment> getShipments() {
        return shipments;
    }

    public void setShipments(List<Shipment> shipments) {
        this.shipments = shipments;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public PaymentMethod getPaymentMethodBuyer() {
        return paymentMethodBuyer;
    }

    public void setPaymentMethodBuyer(PaymentMethod paymentMethodBuyer) {
        this.paymentMethodBuyer = paymentMethodBuyer;
    }

    public PaymentMethod getPaymentMethodSeller() {
        return paymentMethodSeller;
    }

    public void setPaymentMethodSeller(PaymentMethod paymentMethodSeller) {
        this.paymentMethodSeller = paymentMethodSeller;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
