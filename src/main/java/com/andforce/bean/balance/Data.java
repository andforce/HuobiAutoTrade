package com.andforce.bean.balance;

import org.json.*;
import java.util.ArrayList;

public class Data {
	
    private long id;
    private String state;
    private ArrayList<List> list;
    private String type;
    
    
	public Data () {
		
	}	
        
    public Data (JSONObject json) {
    
        this.id = json.optLong("id");
        this.state = json.optString("state");

        this.list = new ArrayList<List>();
        JSONArray arrayList = json.optJSONArray("list");
        if (null != arrayList) {
            int listLength = arrayList.length();
            for (int i = 0; i < listLength; i++) {
                JSONObject item = arrayList.optJSONObject(i);
                if (null != item) {
                    this.list.add(new List(item));
                }
            }
        }
        else {
            JSONObject item = json.optJSONObject("list");
            if (null != item) {
                this.list.add(new List(item));
            }
        }

        this.type = json.optString("type");

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

    public ArrayList<List> getList() {
        return this.list;
    }

    public void setList(ArrayList<List> list) {
        this.list = list;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


    
}
