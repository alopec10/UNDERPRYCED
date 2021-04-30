package com.inso2.inso2.dto.order.price;

import java.io.Serializable;
import java.math.BigDecimal;

public class PriceCalculationResponse implements Serializable {
    private BigDecimal fees, shipping, total;

    public PriceCalculationResponse() {
    }

    public PriceCalculationResponse(BigDecimal fees, BigDecimal shipping, BigDecimal total) {
        this.fees = fees;
        this.shipping = shipping;
        this.total = total;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    public BigDecimal getShipping() {
        return shipping;
    }

    public void setShipping(BigDecimal shipping) {
        this.shipping = shipping;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
