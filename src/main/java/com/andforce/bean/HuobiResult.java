package com.andforce.bean;

import org.json.*;


public class HuobiResult {
	
    private String status;
    private String data;
    
    
	public HuobiResult () {
		
	}	
        
    public HuobiResult (JSONObject json) {
    
        this.status = json.optString("status");
        this.data = json.optString("data");

    }
    
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }


    
}
