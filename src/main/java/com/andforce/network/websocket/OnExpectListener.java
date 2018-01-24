package com.andforce.network.websocket;

/**
 * Created by andforce on 2018/1/24.
 */
public interface OnExpectListener {

    public void onExpect(String buyOrSell ,String symbol, String nowPrice, float rate);
}
