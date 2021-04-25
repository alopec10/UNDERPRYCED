package com.inso2.inso2.dto.order.approve;

import java.io.Serializable;

public class ApproveOrderRequest implements Serializable {

    private String orderRef;

    public ApproveOrderRequest() {
    }

    public ApproveOrderRequest(String orderRef) {
        this.orderRef = orderRef;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }
}
