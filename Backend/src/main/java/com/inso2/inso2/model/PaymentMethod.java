package com.inso2.inso2.model;


import javax.persistence.*;
import javax.validation.constraints.Size;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdUser", nullable = false)
    private User user;

    @OneToOne(mappedBy = "buyerPaymentMethod")
    private Order buyerOrder;

    @OneToOne(mappedBy = "sellerPaymentMethod")
    private Order sellerOrder;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getBuyerOrder() {
        return buyerOrder;
    }

    public void setBuyerOrder(Order buyerOrder) {
        this.buyerOrder = buyerOrder;
    }

    public Order getSellerOrder() {
        return sellerOrder;
    }

    public void setSellerOrder(Order sellerOrder) {
        this.sellerOrder = sellerOrder;
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
