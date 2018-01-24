package com.andforce.assets;

/**
 * Created by andforce on 2018/1/24.
 */
public class OrderInfo {

    private long id;

    private String mSymbol;         // btcusdt

    private String mType;           //buy-market

    private double mBuyAccount;      //field-amount

    private double mBuyCashAccount;        //field-cash-amount

    private String mStates;

    public OrderInfo(long id, String symbol, String type, String buyAccount, String buyCashAccount, String states) {
        this.id = id;
        mSymbol = symbol;
        mType = type;
        if (buyAccount == null || buyAccount.equals("")){
            mBuyAccount = 0;
        } else {
            mBuyAccount = Double.valueOf(buyAccount);
        }

        if (buyCashAccount == null || buyCashAccount.equals("")){
            mBuyCashAccount = 0;
        } else {
            mBuyCashAccount = Double.valueOf(buyCashAccount);
        }

        mStates = states;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public void setSymbol(String symbol) {
        mSymbol = symbol;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public double getBuyAccount() {
        return mBuyAccount;
    }

    public void setBuyAccount(double buyAccount) {
        mBuyAccount = buyAccount;
    }

    public double getBuyCashAccount() {
        return mBuyCashAccount;
    }

    public void setBuyCashAccount(double buyCashAccount) {
        mBuyCashAccount = buyCashAccount;
    }

    public String getStates() {
        return mStates;
    }

    public void setStates(String states) {
        mStates = states;
    }
}
