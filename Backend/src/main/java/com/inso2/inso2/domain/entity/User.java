package com.inso2.inso2.domain.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "IdUser")
    private long idUser;

    private String name, surname, email, password, address, country;

    @Column(name = "ZIPCode")
    private String zipCode;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "SellsCompleted")
    private int sellsCompleted;

    @Column(name = "PurchasesCompleted")
    private int purchasesCompleted;

    public User() {
    }

    public User(String name, String surname, String email, String password, String address, String zipCode, String country, String phoneNumber, int sellsCompleted, int purchasesCompleted) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.zipCode = zipCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.sellsCompleted = sellsCompleted;
        this.purchasesCompleted = purchasesCompleted;
    }

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getIdUser() == user.getIdUser() &&
                getSellsCompleted() == user.getSellsCompleted() &&
                getPurchasesCompleted() == user.getPurchasesCompleted() &&
                getName().equals(user.getName()) &&
                getSurname().equals(user.getSurname()) &&
                getEmail().equals(user.getEmail()) &&
                getPassword().equals(user.getPassword()) &&
                Objects.equals(getAddress(), user.getAddress()) &&
                Objects.equals(getZipCode(), user.getZipCode()) &&
                Objects.equals(getCountry(), user.getCountry()) &&
                Objects.equals(getPhoneNumber(), user.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUser(), getName(), getSurname(), getEmail(), getPassword(), getAddress(), getZipCode(), getCountry(), getPhoneNumber(), getSellsCompleted(), getPurchasesCompleted());
    }
}
