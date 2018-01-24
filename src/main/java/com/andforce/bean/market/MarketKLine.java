package com.andforce.bean.market;

import org.json.*;


public class MarketKLine {
	
    private Tick tick;
    private String ch;
    private long ts;
    
    
	public MarketKLine () {
		
	}	
        
    public MarketKLine (JSONObject json) {
    
        this.tick = new Tick(json.optJSONObject("tick"));
        this.ch = json.optString("ch");
        this.ts = json.optLong("ts");

    }
    
    public Tick getTick() {
        return this.tick;
    }

    public void setTick(Tick tick) {
        this.tick = tick;
    }

    public String getCh() {
        return this.ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public long getTs() {
        return this.ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }


    
}
