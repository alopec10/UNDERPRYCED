package com.inso2.inso2.dto.bid.delete;

import java.io.Serializable;

public class DeleteBidRequest implements Serializable {
    private long idProductDetails;

    public DeleteBidRequest() {
    }

    public DeleteBidRequest(long idProductDetails) {
        this.idProductDetails = idProductDetails;
    }

    public long getIdProductDetails() {
        return idProductDetails;
    }

    public void setIdProductDetails(long idProductDetails) {
        this.idProductDetails = idProductDetails;
    }
}
