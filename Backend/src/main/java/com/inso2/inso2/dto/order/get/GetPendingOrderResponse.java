package com.inso2.inso2.dto.order.get;

import com.inso2.inso2.model.Order;
import com.inso2.inso2.model.Product;
import com.inso2.inso2.model.Shipment;
import com.inso2.inso2.model.ShipmentType;

import java.io.Serializable;
import java.util.List;

public class GetPendingOrderResponse implements Serializable {

    private String orderRef, date, size, productRef, brand, colorway, title, url, address, zipCode, country, trackingNumber;
    private int price;

    public GetPendingOrderResponse() {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProductRef() {
        return productRef;
    }

    public void setProductRef(String productRef) {
        this.productRef = productRef;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public GetPendingOrderResponse build(Order o){
        this.orderRef = o.getRef();
        this.date = o.getDate().toString().substring(0,10);
        this.price = o.getPrice();
        this.size = o.getProductDetails().getSize();
        Product p = o.getProductDetails().getProduct();
        this.productRef = p.getRef();
        this.brand = p.getBrand();
        this.colorway = p.getColorway();
        this.title = p.getTitle();
        this.url = p.getUrl();
        Shipment warehouseShipment = this.getShipment(o.getShipments(), ShipmentType.WAREHOUSE);
        this.trackingNumber = warehouseShipment.getTrackingNumber();
        Shipment homeShipment = this.getShipment(o.getShipments(), ShipmentType.HOME);
        this.address = homeShipment.getAddress();
        this.zipCode = homeShipment.getZipCode();
        this.country = homeShipment.getCountry();
        return this;
    }

    private Shipment getShipment(List<Shipment> shipments, ShipmentType shipmentType){
        for(Shipment s: shipments){
            if (s.getType() == shipmentType){
                return s;
            }
        }
        return null;
    }
}
