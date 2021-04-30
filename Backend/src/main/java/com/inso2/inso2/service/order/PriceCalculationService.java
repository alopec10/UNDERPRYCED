package com.inso2.inso2.service.order;

import com.inso2.inso2.dto.order.price.PriceCalculationResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PriceCalculationService {
    public PriceCalculationResponse calculateSell (int price) {
        BigDecimal pr = new BigDecimal(price);
        BigDecimal ONE_HUNDRED = new BigDecimal(100);
        BigDecimal TEN = new BigDecimal(10);
        BigDecimal shipping = new BigDecimal(15);
        BigDecimal fees = pr.multiply(TEN).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP);
        BigDecimal total = pr.subtract(fees).subtract(shipping);
        PriceCalculationResponse pcr = new PriceCalculationResponse(fees, shipping, total);
        return pcr;
    }

    public PriceCalculationResponse calculateBuy (int price) {
        BigDecimal pr = new BigDecimal(price);
        BigDecimal ONE_HUNDRED = new BigDecimal(100);
        BigDecimal TEN = new BigDecimal(10);
        BigDecimal shipping = new BigDecimal(15);
        BigDecimal fees = pr.multiply(TEN).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP);
        BigDecimal total = pr.add(fees).add(shipping);
        PriceCalculationResponse pcr = new PriceCalculationResponse(fees, shipping, total);
        return pcr;
    }
}
