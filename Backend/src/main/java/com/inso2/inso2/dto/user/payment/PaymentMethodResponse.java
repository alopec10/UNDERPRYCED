package com.inso2.inso2.dto.user.payment;

public class PaymentMethodResponse {
    private long idPayMethod;
    private String name, number, expMonth, expYear;
    private boolean defaultMethod;

    public PaymentMethodResponse() {
    }

    public PaymentMethodResponse(long idPayMethod, String name, String number, String expMonth, String expYear, boolean defaultMethod) {
        this.idPayMethod = idPayMethod;
        this.name = name;
        this.number = number;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.defaultMethod = defaultMethod;
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
