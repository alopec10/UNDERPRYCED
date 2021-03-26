package com.inso2.inso2.model;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "paymentMethods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "IdPayMethod")
    private long idPayMethod;

    @Size(min = 16, max = 16)
    @Column(name = "Number", length = 16, nullable = false)
    private String number;

    @Size(min = 3, max = 3)
    @Column(name = "CVV", length = 3, nullable = false)
    private String cvv;

    @Size(min = 2, max = 2)
    @Column(name = "ExpMonth", length = 2, nullable = false)
    private String expMonth;

    @Size(min = 2, max = 2)
    @Column(name = "ExpYear", length = 2, nullable = false)
    private String expYear;

    @Column(name = "DefaultMethod", nullable = false)
    private boolean defaultMethod;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdUser", nullable = false)
    private User user;

    public PaymentMethod() {
    }

    public PaymentMethod(@Size(min = 16, max = 16) String number, @Size(min = 3, max = 3) String cvv, @Size(min = 2, max = 2) String expMonth, @Size(min = 2, max = 2) String expYear, boolean defaultMethod, User user) {
        this.number = number;
        this.cvv = cvv;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.defaultMethod = defaultMethod;
        this.user = user;
    }

    public long getIdPayMethod() {
        return idPayMethod;
    }

    public void setIdPayMethod(long idPayMethod) {
        this.idPayMethod = idPayMethod;
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
