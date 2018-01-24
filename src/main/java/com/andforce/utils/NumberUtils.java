package com.andforce.utils;

import java.math.BigDecimal;

public class NumberUtils {

    public static float shortFloat(float price, int len) {
        BigDecimal b = new BigDecimal(price);
        return b.setScale(len, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static double shortDouble(double price, int len) {
        BigDecimal b = new BigDecimal(price);
        return b.setScale(len, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
