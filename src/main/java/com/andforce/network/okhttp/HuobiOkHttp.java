package com.andforce.network.okhttp;

import com.andforce.bean.account.Account;
import com.andforce.bean.balance.Balance;
import com.andforce.bean.historybuy.HistoryBuy;
import com.andforce.network.HuobiRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by andforce on 2018/1/23.
 */
public class HuobiOkHttp {

    public String GET(HuobiRequest huobiRequest){

        OkHttpClient client = new OkHttpClient();
        //创建一个Request
        Request request = new Request.Builder()
                .get()
                .url(huobiRequest.url() + "?" + huobiRequest.parametersStr())
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36")
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .build();

        //通过client发起请求
        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (responseBody != null){
                String body = responseBody.string();
                return body;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String POST(HuobiRequest huobiRequest){

        OkHttpClient client = new OkHttpClient();

        //创建一个Request
        Request request = new Request.Builder()
                .post(RequestBody.create(MediaType.parse("application/json"), huobiRequest.postBody()))
                .url(huobiRequest.url() + "?" + huobiRequest.parametersStr())
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/json")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36").build();


        //通过client发起请求
        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (responseBody != null){
                String body = responseBody.string();
                return body;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public Account account(HuobiRequest resuest){
//        String json = GET(resuest);
//        Account account = new Gson().fromJson(json, Account.class);
//        return account;
//    }
//
//    public Balance balance(HuobiRequest resuest){
//        String json = GET(resuest);
//        Balance account = new Gson().fromJson(json, Balance.class);
//        return account;
//    }
//
//    public HistoryBuy historyBuy(HuobiRequest resuest){
//        String json = GET(resuest);
//        HistoryBuy account = new Gson().fromJson(json, HistoryBuy.class);
//        return account;
//    }


    public <T> T GET(Class<T> classOfT,HuobiRequest resuest){
        String json = GET(resuest);
        return  new Gson().fromJson(json, classOfT);
    }

    public <T> T POST(Class<T> classOfT,HuobiRequest resuest){
        String json = POST(resuest);
        return  new Gson().fromJson(json, classOfT);
    }

}
