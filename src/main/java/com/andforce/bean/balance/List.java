package com.andforce.bean.balance;

import org.json.*;


public class List {
	
    private String currency;
    private String type;
    private String balance;
    
    
	public List () {
		
	}	
        
    public List (JSONObject json) {
    
        this.currency = json.optString("currency");
        this.type = json.optString("type");
        this.balance = json.optString("balance");

    }
    
    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }


    
}
