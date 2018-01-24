package com.andforce.bean.order;

import org.json.*;


public class Data {
	
    private String fieldFees;
    private long id;
    private long createdAt;
    private String state;
    private String amount;
    private String symbol;
    private String fieldCashAmount;
    private String source;
    private String type;
    private String price;
    private long accountId;
    private String fieldAmount;
    private long finishedAt;
    private long canceledAt;
    
    
	public Data () {
		
	}	
        
    public Data (JSONObject json) {
    
        this.fieldFees = json.optString("field-fees");
        this.id = json.optLong("id");
        this.createdAt = json.optLong("created-at");
        this.state = json.optString("state");
        this.amount = json.optString("amount");
        this.symbol = json.optString("symbol");
        this.fieldCashAmount = json.optString("field-cash-amount");
        this.source = json.optString("source");
        this.type = json.optString("type");
        this.price = json.optString("price");
        this.accountId = json.optLong("account-id");
        this.fieldAmount = json.optString("field-amount");
        this.finishedAt = json.optLong("finished-at");
        this.canceledAt = json.optLong("canceled-at");

    }
    
    public String getFieldFees() {
        return this.fieldFees;
    }

    public void setFieldFees(String fieldFees) {
        this.fieldFees = fieldFees;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getFieldCashAmount() {
        return this.fieldCashAmount;
    }

    public void setFieldCashAmount(String fieldCashAmount) {
        this.fieldCashAmount = fieldCashAmount;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getFieldAmount() {
        return this.fieldAmount;
    }

    public void setFieldAmount(String fieldAmount) {
        this.fieldAmount = fieldAmount;
    }

    public long getFinishedAt() {
        return this.finishedAt;
    }

    public void setFinishedAt(long finishedAt) {
        this.finishedAt = finishedAt;
    }

    public long getCanceledAt() {
        return this.canceledAt;
    }

    public void setCanceledAt(long canceledAt) {
        this.canceledAt = canceledAt;
    }


    
}
