package com.andforce.bean.balance;

import org.json.*;


public class Balance {
	
    private String status;
    private Data data;
    
    
	public Balance () {
		
	}	
        
    public Balance (JSONObject json) {
    
        this.status = json.optString("status");
        this.data = new Data(json.optJSONObject("data"));

    }
    
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    
}
