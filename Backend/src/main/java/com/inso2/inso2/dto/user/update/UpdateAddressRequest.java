package com.inso2.inso2.dto.user.update;

import java.io.Serializable;

public class UpdateAddressRequest implements Serializable {
    private String address, country, zipCode;

    public UpdateAddressRequest() {
    }

    public UpdateAddressRequest(String address, String country, String zipCode) {
        this.address = address;
        this.country = country;
        this.zipCode = zipCode;
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
