package com.inso2.inso2.dto.order.get;

import com.inso2.inso2.model.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class GetOrderInformationResponse implements Serializable {
    private String orderRef, date, shipDate, arrivalDate, address, zipCode, country, trackingNumber, productRef, size, brand, colorway, title, url, type;
    private int price;
    private BigDecimal priceSeller, priceBuyer;
    private boolean completed, sent, approved;
    private Status status;

    public GetOrderInformationResponse() {
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductRef() {
        return productRef;
    }

    public void setProductRef(String productRef) {
        this.productRef = productRef;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColorway() {
        return colorway;
    }

    public void setColorway(String colorway) {
        this.colorway = colorway;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public BigDecimal getPriceSeller() {
        return priceSeller;
    }

    public void setPriceSeller(BigDecimal priceSeller) {
        this.priceSeller = priceSeller;
    }

    public BigDecimal getPriceBuyer() {
        return priceBuyer;
    }

    public void setPriceBuyer(BigDecimal priceBuyer) {
        this.priceBuyer = priceBuyer;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
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

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GetOrderInformationResponse build(Order o, ShipmentType shipmentType){
        this.orderRef = o.getRef();
        this.date = o.getDate().toString().substring(0,10);
        this.price = o.getPrice();
        this.priceSeller = o.getPriceSeller();
        this.priceBuyer = o.getPriceBuyer();
        this.size = o.getProductDetails().getSize();
        this.type = this.getType(shipmentType);
        Product p = o.getProductDetails().getProduct();
        this.productRef = p.getRef();
        this.brand = p.getBrand();
        this.colorway = p.getColorway();
        this.title = p.getTitle();
        this.url = p.getUrl();
        Shipment s = this.getWarehouseShipment(o.getShipments(), shipmentType);
        this.trackingNumber = s.getTrackingNumber();
        this.shipDate = s.getShipDate().toString().substring(0,10);
        this.arrivalDate = s.getArrivalDate().toString().substring(0,10);
        this.address = s.getAddress();
        this.zipCode = s.getZipCode();
        this.country = s.getCountry();
        this.completed = s.isCompleted();
        this.sent = s.isSent();
        this.approved = s.isApproved();
        this.status = s.getStatus();
        return this;
    }

    private Shipment getWarehouseShipment(List<Shipment> shipments, ShipmentType shipmentType){
        for(Shipment s: shipments){
            if (s.getType() == shipmentType){
                return s;
            }
        }
        return null;
    }

    private String getType(ShipmentType shipmentType){
        if(shipmentType == ShipmentType.HOME){
            return "Buy";
        }
        else{
            return "Sell";
        }
    }
}
