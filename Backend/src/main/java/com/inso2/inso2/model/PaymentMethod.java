package com.inso2.inso2.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "paymentMethods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IdPayMethod")
    private long idPayMethod;

    @Size(min = 1, max = 50)
    @Column(name = "Name", length = 50, nullable = false)
    private String name;

    @Size(min = 1, max = 100)
    @Column(name = "Number", length = 100, nullable = false)
    private String number;

    @Size(min = 1, max = 100)
    @Column(name = "CVV", length = 100, nullable = false)
    private String cvv;

    @Size(min = 1, max = 100)
    @Column(name = "ExpMonth", length = 100, nullable = false)
    private String expMonth;

    @Size(min = 1, max = 100)
    @Column(name = "ExpYear", length = 100, nullable = false)
    private String expYear;

    @Column(name = "DefaultMethod", nullable = false)
    private boolean defaultMethod;

    @Column(name = "IsActive", nullable = false)
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdUser", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="paymentMethodBuyer")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Order> buyerOrders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="paymentMethodSeller")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Order> sellerOrders;

    public PaymentMethod() {
    }

    public PaymentMethod(@Size(min = 1, max = 50) String name, @Size(min = 1, max = 100) String number, @Size(min = 1, max = 100) String cvv, @Size(min = 1, max = 100) String expMonth, @Size(min = 1, max = 100) String expYear) {
        this.name = name;
        this.number = number;
        this.cvv = cvv;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

    public long getIdPayMethod() {
        return idPayMethod;
    }

    public void setIdPayMethod(long idPayMethod) {
        this.idPayMethod = idPayMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public boolean isDefaultMethod() {
        return defaultMethod;
    }

    public void setDefaultMethod(boolean defaultMethod) {
        this.defaultMethod = defaultMethod;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getBuyerOrders() {
        return buyerOrders;
    }

    public void setBuyerOrders(List<Order> buyerOrders) {
        this.buyerOrders = buyerOrders;
    }

    public List<Order> getSellerOrders() {
        return sellerOrders;
    }

    public void setSellerOrders(List<Order> sellerOrders) {
        this.sellerOrders = sellerOrders;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "idPayMethod=" + idPayMethod +
                ", number='" + number + '\'' +
                ", cvv='" + cvv + '\'' +
                ", expMonth='" + expMonth + '\'' +
                ", expYear='" + expYear + '\'' +
                ", defaultMethod=" + defaultMethod +
                ", user=" + user +
                '}';
    }
}
