package com.inso2.inso2.domain.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "paymentMethods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "IdPayMethod")
    private long idPayMethod;

    private String number, cvv;

    @Column(name = "ExpMonth")
    private String expMonth;

    @Column(name = "ExpYear")
    private String expYear;

    @Column(name = "DefaultMethod")
    private boolean defaultMethod;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "IdUser")
    private User user;

    public PaymentMethod() {
    }

    public PaymentMethod(String number, String cvv, String expMonth, String expYear, boolean defaultMethod, User user) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod that = (PaymentMethod) o;
        return getIdPayMethod() == that.getIdPayMethod() && isDefaultMethod() == that.isDefaultMethod() && Objects.equals(getNumber(), that.getNumber()) && Objects.equals(getCvv(), that.getCvv()) && Objects.equals(getExpMonth(), that.getExpMonth()) && Objects.equals(getExpYear(), that.getExpYear()) && Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPayMethod(), getNumber(), getCvv(), getExpMonth(), getExpYear(), isDefaultMethod(), getUser());
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
