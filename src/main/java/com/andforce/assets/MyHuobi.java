package com.andforce.assets;

import com.andforce.bean.balance.Balance;
import com.andforce.bean.order.Data;
import com.andforce.bean.order.Order;
import com.andforce.network.HuobiRequest;
import com.andforce.network.okhttp.HuobiOkHttp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andforce on 2018/1/24.
 */
public class MyHuobi {

    private HuobiOkHttp mHuobiOkHttp = new HuobiOkHttp();

    private static MyHuobi mInstance = new MyHuobi();

    public static MyHuobi getInstance(){
        return mInstance;
    }

    private List<Asset> mAssets;

    private List<OrderInfo> mOrderInfos;

    private Account mAccount;

    private MyHuobi() {
        super();
    }

    public List<Asset> getAssets() {
        return mAssets;
    }

    public void setAssets(List<Asset> assets) {
        mAssets = assets;
    }

    public List<OrderInfo> getOrderInfos() {
        return mOrderInfos;
    }

    public void setOrderInfos(List<OrderInfo> orderInfos) {
        mOrderInfos = orderInfos;
    }

    public Account getAccount() {
        return mAccount;
    }

    public void setAccount(Account account) {
        mAccount = account;
    }

    public void refresh(){

        mAssets = null;

        HuobiRequest accountRequest = new HuobiRequest.Builder().method("GET")
                .host("api.huobi.pro").path("/v1/account/accounts").build();
        com.andforce.bean.account.Account account = mHuobiOkHttp.GET(com.andforce.bean.account.Account.class, accountRequest);
        //print(account.getStatus());

        //MyHuobi myHuobi = MyHuobi.getInstance();

        // Account
        com.andforce.assets.Account account1 = new com.andforce.assets.Account(account.getData().get(0).getId());
        this.setAccount(account1);
        // Asset
        HuobiRequest balanceRequest = new HuobiRequest.Builder().method("GET")
                .host("api.huobi.pro").path("/v1/account/accounts/" + account.getData().get(0).getId() + "/balance").build();
        Balance balance = mHuobiOkHttp.GET(Balance.class, balanceRequest);
        ArrayList<Asset> assets = new ArrayList<>();
        for (com.andforce.bean.balance.List b : balance.getData().getList()) {
            double _balance = Double.valueOf(b.getBalance());
            if (_balance != 0){
                Asset asset = new Asset(b.getCurrency(), b.getType(), Double.valueOf(b.getBalance()));
                assets.add(asset);
            }
        }
        this.setAssets(assets);


        ArrayList<OrderInfo> allOrders = new ArrayList<>();

        // 查看委托中的比特币订单，也就是未完成的订单
        HuobiRequest btcOrderRequest = new HuobiRequest.Builder().method("GET").host("api.huobi.pro").path("/v1/order/orders")
                .parameter("symbol", "btcusdt")
                .parameter("states", "submitted,partial-filled")
                .build();

        Order btcOrder = mHuobiOkHttp.GET(Order.class, btcOrderRequest);

        for (Data data: btcOrder.getData()) {
            OrderInfo orderInfo = new OrderInfo(data.getId(),data.getSymbol(),data.getType(), data.getFieldAmount(),
                    data.getFieldCashAmount(), data.getState());

            allOrders.add(orderInfo);
        }

        HuobiRequest xrpOrderRequest = new HuobiRequest.Builder().method("GET").host("api.huobi.pro").path("/v1/order/orders")
                .parameter("symbol", "xrpusdt")
                .parameter("states", "submitted,partial-filled")
                .build();

        // 产看委托中的xrp订单
        Order xrpOrder = mHuobiOkHttp.GET(Order.class, xrpOrderRequest);

        for (Data data: xrpOrder.getData()) {
            OrderInfo orderInfo = new OrderInfo(data.getId(),data.getSymbol(),data.getType(), data.getFieldAmount(),
                    data.getFieldCashAmount(), data.getState());

            allOrders.add(orderInfo);
        }

        this.setOrderInfos(allOrders);
    }
}
