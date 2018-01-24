package com.andforce.bean.account;

import org.json.*;


public class Data {
	
    private long id;
    private String state;
    private String type;
    private String subtype;
    
    
	public Data () {
		
	}	
        
    public Data (JSONObject json) {
    
        this.id = json.optLong("id");
        this.state = json.optString("state");
        this.type = json.optString("type");
        this.subtype = json.optString("subtype");

    }
    
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return this.subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }


    
}
