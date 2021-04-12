package com.inso2.inso2.dto.user.data;

import com.inso2.inso2.model.User;

import java.io.Serializable;

public class UserDataResponse implements Serializable {
    private String name, surname, email, address, country, zipCode, phoneNumber;
    private int sellsCompleted, purchasesCompleted;

    public UserDataResponse() {
    }

    public UserDataResponse build(User user){
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.country = user.getCountry();
        this.zipCode = user.getZipCode();
        this.phoneNumber = user.getPhoneNumber();
        this.sellsCompleted = user.getSellsCompleted();
        this.purchasesCompleted = user.getPurchasesCompleted();
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSellsCompleted() {
        return sellsCompleted;
    }

    public void setSellsCompleted(int sellsCompleted) {
        this.sellsCompleted = sellsCompleted;
    }

    public int getPurchasesCompleted() {
        return purchasesCompleted;
    }

    public void setPurchasesCompleted(int purchasesCompleted) {
        this.purchasesCompleted = purchasesCompleted;
    }
}
