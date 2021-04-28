package com.inso2.inso2.dto.order.create;

import java.io.Serializable;

public class CreateOrderBuyRequest implements Serializable {
    private long idPayMethod;
    private String ref, size, address, country, zipCode;

    public CreateOrderBuyRequest() {
    }

    public CreateOrderBuyRequest(long idPayMethod, String ref, String size, String address, String country, String zipCode) {
        this.idPayMethod = idPayMethod;
        this.ref = ref;
        this.size = size;
        this.address = address;
        this.country = country;
        this.zipCode = zipCode;
    }

    public long getIdPayMethod() {
        return idPayMethod;
    }

    public void setIdPayMethod(long idPayMethod) {
        this.idPayMethod = idPayMethod;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
