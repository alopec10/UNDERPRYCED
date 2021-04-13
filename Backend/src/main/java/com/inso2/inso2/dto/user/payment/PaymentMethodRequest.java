package com.inso2.inso2.dto.user.payment;

import java.io.Serializable;

public class PaymentMethodRequest  implements Serializable {
    private String number, cvv, expMonth, expYear;
    private boolean defaultMethod;

    public PaymentMethodRequest() {
    }

    public PaymentMethodRequest(String number, String cvv, String expMonth, String expYear, boolean defaultMethod) {
        this.number = number;
        this.cvv = cvv;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.defaultMethod = defaultMethod;
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
}
