package com.andforce.assets;

import com.andforce.utils.NumberUtils;

import java.util.List;

/**
 * Created by andforce on 2018/1/24.
 */
public class Asset {


    private String currency;
    private String type;
    private double balance;

    public Asset(String currency, String type, double balance) {
        this.currency = currency;
        this.type = type;
        this.balance = NumberUtils.shortDouble(balance, 8);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
