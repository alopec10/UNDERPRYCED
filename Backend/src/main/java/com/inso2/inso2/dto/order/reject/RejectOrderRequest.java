package com.inso2.inso2.dto.order.reject;

import java.io.Serializable;

public class RejectOrderRequest implements Serializable {
    private String orderRef;

    public RejectOrderRequest() {
    }

    public RejectOrderRequest(String orderRef) {
        this.orderRef = orderRef;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }
}
