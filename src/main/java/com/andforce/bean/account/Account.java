package com.andforce.bean.account;

import org.json.*;
import java.util.ArrayList;

public class Account {
	
    private String status;
    private ArrayList<Data> data;
    
    
	public Account () {
		
	}	
        
    public Account (JSONObject json) {
    
        this.status = json.optString("status");

        this.data = new ArrayList<Data>();
        JSONArray arrayData = json.optJSONArray("data");
        if (null != arrayData) {
            int dataLength = arrayData.length();
            for (int i = 0; i < dataLength; i++) {
                JSONObject item = arrayData.optJSONObject(i);
                if (null != item) {
                    this.data.add(new Data(item));
                }
            }
        }
        else {
            JSONObject item = json.optJSONObject("data");
            if (null != item) {
                this.data.add(new Data(item));
            }
        }


    }
    
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Data> getData() {
        return this.data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }


    
}
