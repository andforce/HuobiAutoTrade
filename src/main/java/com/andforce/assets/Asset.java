package com.andforce.assets;

import java.util.List;

/**
 * Created by andforce on 2018/1/24.
 */
public class Asset {


    private String currency;
    private String type;
    private String balance;

    public Asset(String currency, String type, String balance) {
        this.currency = currency;
        this.type = type;
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public String getType() {
        return type;
    }

    public String getBalance() {
        return balance;
    }
}
