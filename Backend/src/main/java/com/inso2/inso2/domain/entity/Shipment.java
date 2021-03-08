package com.inso2.inso2.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "IdShipment")
    private long idShipment;

    @Size(min = 1, max = 20)
    @Column(name = "TrackingNumber", length = 20)
    private String trackingNumber;

    @Column(name = "ShipDate", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipDate;

    @Size(min = 1, max = 40)
    @Column(name = "Address", length = 40)
    private String address;

    @Size(min = 1, max = 10)
    @Column(name = "ZIPCode", length = 10)
    private String zipCode;

    @Size(min = 1, max = 20)
    @Column(name = "Country", length = 20)
    private String country;

    @Column(name = "Completed")
    private boolean completed;

    @OneToOne
    @JoinColumn(name = "IdOrder", referencedColumnName = "IdOrder")
    private Order order;

    public Shipment() {
    }

    public Shipment(@Size(min = 1, max = 20) String trackingNumber, Date shipDate, @Size(min = 1, max = 40) String address, @Size(min = 1, max = 10) String zipCode, @Size(min = 1, max = 20) String country, boolean completed, Order order) {
        this.trackingNumber = trackingNumber;
        this.shipDate = shipDate;
        this.address = address;
        this.zipCode = zipCode;
        this.country = country;
        this.completed = completed;
        this.order = order;
    }

    public long getIdShipment() {
        return idShipment;
    }

    public void setIdShipment(long idShipment) {
        this.idShipment = idShipment;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
