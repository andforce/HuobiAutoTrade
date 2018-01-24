package com.andforce.network.okhttp;

import com.andforce.bean.HuobiResult;
import com.andforce.network.HuobiRequest;
import com.andforce.utils.NumberUtils;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by andforce on 2018/1/24.
 */
public class BuyOrSellApi {

    private HuobiOkHttp mHuobiOkHttp = new HuobiOkHttp();
    private Gson mGson = new Gson();

    /**
     *
     * @param accountId
     * @param symbol        xrpusdt
     * @param buyAmount     比特币的最小数量是0.001个  瑞波的最小数量是1个,小数点后面要保持两位
     * @param price         单价
     * @return
     */
    public HuobiResult buy(String accountId, String symbol, float buyAmount, float price){

        float realPrice = formatFloat(price);
        float realBuyAmount = formatFloat(buyAmount);

        System.out.println("buy:" + symbol + " account:" + realBuyAmount + " price:"  + realPrice);

        HuobiRequest orderPlaceRequest = new HuobiRequest.Builder().method("POST").host("api.huobi.pro").path("/v1/order/orders/place")
                .postJsonBody("account-id", accountId)
                .postJsonBody("symbol", symbol.toLowerCase())
                .postJsonBody("amount", String.valueOf(realBuyAmount))
                .postJsonBody("price", String.valueOf(realPrice))
                .postJsonBody("type", "buy-limit")
                .postJsonBody("source", "api")
                .build();


        String result = mHuobiOkHttp.POST(orderPlaceRequest);

        if (result.startsWith("{\"status\":\"error\"")) {
            System.out.println("buy Error:" + result);
        } else {
            HuobiResult huobiResult = mGson.fromJson(result, HuobiResult.class);
            return huobiResult;
        }

        return null;
    }

    private float formatFloat(float price) {
        return NumberUtils.shortFloat(price, 2);
    }

    public HuobiResult sell(String accountId, String symbol, float buyAmount, float price){

        float realPrice = formatFloat(price);
        float realBuyAmount = formatFloat(buyAmount);

        System.out.println("sell:" + symbol + " account:" + realBuyAmount + " price:"  + realPrice);

        HuobiRequest orderPlaceRequest = new HuobiRequest.Builder().method("POST").host("api.huobi.pro").path("/v1/order/orders/place")
                .postJsonBody("account-id", accountId)
                .postJsonBody("symbol", symbol.toLowerCase())
                .postJsonBody("amount", String.valueOf(realBuyAmount))
                .postJsonBody("price", String.valueOf(realPrice))
                .postJsonBody("type", "sell-limit")
                .postJsonBody("source", "api")
                .build();

        String result = mHuobiOkHttp.POST(orderPlaceRequest);

        if (result.startsWith("{\"status\":\"error\"")) {
            System.out.println("sell Error:" + result);
        } else {
            HuobiResult huobiResult = mGson.fromJson(result, HuobiResult.class);
            return huobiResult;
        }

        return null;
    }
}
