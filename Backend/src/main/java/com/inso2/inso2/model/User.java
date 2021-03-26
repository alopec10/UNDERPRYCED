package com.inso2.inso2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "IdUser")
    private long idUser;

    @Size(min = 1, max = 20)
    @Column(name = "Name", length = 20, nullable = false)
    private String name;

    @Size(min = 1, max = 30)
    @Column(name = "Surname", length = 30, nullable = false)
    private String surname;

    @Size(min = 5, max = 30)
    @Column(name = "Email", length = 30, nullable = false)
    private String email;

    @Size(min = 1, max = 100)
    @Column(name = "Password", length = 100, nullable = false)
    private String password;

    @Size(min = 1, max = 40)
    @Column(name = "Address", length = 40)
    private String address;

    @Size(min = 1, max = 20)
    @Column(name = "Country", length = 20)
    private String country;

    @Size(min = 1, max = 10)
    @Column(name = "ZIPCode", length = 10)
    private String zipCode;

    @Size(min = 1, max = 20)
    @Column(name = "PhoneNumber", length = 20)
    private String phoneNumber;

    @Column(name = "SellsCompleted", nullable = false)
    private int sellsCompleted;

    @Column(name = "PurchasesCompleted", nullable = false)
    private int purchasesCompleted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<PaymentMethod> paymentMethods;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Ask> asks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Bid> bids;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="seller")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Order> sells;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="buyer")
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Order> purchases;

    public User() {
    }

    public User(@Size(min = 1, max = 20) String name, @Size(min = 1, max = 30) String surname, @Size(min = 5, max = 30) String email, @Size(min = 1, max = 20) String password, @Size(min = 1, max = 40) String address, @Size(min = 1, max = 20) String country, @Size(min = 1, max = 10) String zipCode, @Size(min = 1, max = 20) String phoneNumber, int sellsCompleted, int purchasesCompleted) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.country = country;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.sellsCompleted = sellsCompleted;
        this.purchasesCompleted = purchasesCompleted;
    }

    public User(@Size(min = 1, max = 20) String name, @Size(min = 1, max = 30) String surname, @Size(min = 5, max = 30) String email, @Size(min = 1, max = 20) String password, int sellsCompleted, int purchasesCompleted) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.sellsCompleted = sellsCompleted;
        this.purchasesCompleted = purchasesCompleted;
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

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<Ask> getAsks() {
        return asks;
    }

    public void setAsks(List<Ask> asks) {
        this.asks = asks;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public List<Order> getSells() {
        return sells;
    }

    public void setSells(List<Order> sells) {
        this.sells = sells;
    }

    public List<Order> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Order> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sellsCompleted=" + sellsCompleted +
                ", purchasesCompleted=" + purchasesCompleted +
                ", paymentMethods=" + paymentMethods +
                ", asks=" + asks +
                ", bids=" + bids +
                ", sells=" + sells +
                ", purchases=" + purchases +
                '}';
    }
}
