package com.inso2.inso2.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "IdOrder")
    private long idOrder;

    @Size(min = 1, max = 100)
    @Column(name = "Ref", length = 100)
    private String ref;

    @Column(name = "Price", nullable = false)
    private int price;

    @Column(name = "Date", columnDefinition="DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdBuyer", nullable = false)
    private User buyer;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdSeller", nullable = false)
    private User seller;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdProductDetails", nullable = false)
    private ProductDetails productDetails;

    @OneToOne(mappedBy = "order")
    private Shipment shipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPayMethodBuyer", nullable = false)
    private PaymentMethod paymentMethodBuyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPayMethodSeller", nullable = false)
    private PaymentMethod paymentMethodSeller;


    public Order() {
    }

    public Order(@Size(min = 1, max = 100) String ref, int price, Date date, User buyer, User seller, ProductDetails productDetails, PaymentMethod paymentMethodBuyer, PaymentMethod paymentMethodSeller) {
        this.ref = ref;
        this.price = price;
        this.date = date;
        this.buyer = buyer;
        this.seller = seller;
        this.productDetails = productDetails;
        this.paymentMethodBuyer = paymentMethodBuyer;
        this.paymentMethodSeller = paymentMethodSeller;
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

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", price=" + price +
                ", date=" + date +
                ", buyer=" + buyer +
                ", seller=" + seller +
                ", productDetails=" + productDetails +
                ", shipment=" + shipment +
                '}';
    }
}
