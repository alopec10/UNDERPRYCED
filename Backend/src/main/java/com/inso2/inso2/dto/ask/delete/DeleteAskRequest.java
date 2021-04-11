package com.inso2.inso2.dto.ask.delete;

import java.io.Serializable;

public class DeleteAskRequest implements Serializable {
    private long idProductDetails;

    public DeleteAskRequest() {
    }

    public DeleteAskRequest(long idProductDetails) {
        this.idProductDetails = idProductDetails;
    }

    public long getIdProductDetails() {
        return idProductDetails;
    }

    public void setIdProductDetails(long idProductDetails) {
        this.idProductDetails = idProductDetails;
    }
}
