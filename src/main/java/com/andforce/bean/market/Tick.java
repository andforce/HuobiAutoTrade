package com.andforce.bean.market;

import org.json.*;


public class Tick {
	
    private String amount;
    private long id;
    private float high;
    private String vol;
    private float open;
    private float count;
    private float low;
    private float close;
    
    
	public Tick () {
		
	}	
        
    public Tick (JSONObject json) {
    
        this.amount = json.optString("amount");
        this.id = json.optLong("id");
        this.high = json.optFloat("high");
        this.vol = json.optString("vol");
        this.open = json.optFloat("open");
        this.count = json.optFloat("count");
        this.low = json.optFloat("low");
        this.close = json.optFloat("close");

    }
    
    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getHigh() {
        return this.high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public String getVol() {
        return this.vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public float getOpen() {
        return this.open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getCount() {
        return this.count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public float getLow() {
        return this.low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getClose() {
        return this.close;
    }

    public void setClose(float close) {
        this.close = close;
    }


    
}
