package com.andforce.bean.historybuy;

import org.json.*;


public class Data {
	
    private String symbol;
    private String source;
    private String filledFees;
    private long id;
    private long matchId;
    private String price;
    private long createdAt;
    private String type;
    private String filledAmount;
    private String filledPoints;
    private long orderId;
    
    
	public Data () {
		
	}	
        
    public Data (JSONObject json) {
    
        this.symbol = json.optString("symbol");
        this.source = json.optString("source");
        this.filledFees = json.optString("filled-fees");
        this.id = json.optLong("id");
        this.matchId = json.optLong("match-id");
        this.price = json.optString("price");
        this.createdAt = json.optLong("created-at");
        this.type = json.optString("type");
        this.filledAmount = json.optString("filled-amount");
        this.filledPoints = json.optString("filled-points");
        this.orderId = json.optLong("order-id");

    }
    
    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFilledFees() {
        return this.filledFees;
    }

    public void setFilledFees(String filledFees) {
        this.filledFees = filledFees;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMatchId() {
        return this.matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilledAmount() {
        return this.filledAmount;
    }

    public void setFilledAmount(String filledAmount) {
        this.filledAmount = filledAmount;
    }

    public String getFilledPoints() {
        return this.filledPoints;
    }

    public void setFilledPoints(String filledPoints) {
        this.filledPoints = filledPoints;
    }

    public long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }


    
}
