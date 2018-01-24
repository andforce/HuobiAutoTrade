import com.andforce.assets.MyHuobi;
import com.andforce.bean.HuobiResult;
import com.andforce.bean.historybuy.HistoryBuy;
import com.andforce.bean.order.Order;
import com.andforce.network.HuobiRequest;
import com.andforce.network.okhttp.BuyOrSellApi;
import com.andforce.network.okhttp.HuobiOkHttp;
import com.andforce.network.websocket.HuobiWebSocket;
import com.andforce.network.websocket.OnExpectListener;

import java.net.URISyntaxException;


public class Main {




    public static void main(String[] args) throws URISyntaxException {


        HuobiOkHttp okHttp = new HuobiOkHttp();


        MyHuobi myHuobi = MyHuobi.getInstance();

        myHuobi.refresh();


        if (true){

            HuobiWebSocket huobiWebSocket = HuobiWebSocket.getInstance();

            huobiWebSocket.startMonitorPrice(new OnExpectListener() {

                @Override
                public void onExpect(String buyOrSell, String symbol, float nowPrice, float rate) {
                    huobiWebSocket.pause(symbol);

                    BuyOrSellApi api = new BuyOrSellApi();

                    api.buy(String.valueOf(myHuobi.getAccount().getAccountId()),symbol, 1f, 1.3333f);

                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    System.out.println(buyOrSell + "\t币种:" + symbol + "\t现价:" + nowPrice +"\t跌涨:" + rate);
                    System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                }
            },new String[]{/*"xrpusdt", */"btcusdt"});
            return;
        }



        if (false) {

            // 已经成交的委托
            HuobiRequest matchresultsRequest = new HuobiRequest.Builder()
                    .method("GET").host("api.huobi.pro").path("/v1/order/matchresults").build();

            HistoryBuy historyBuy = okHttp.GET(HistoryBuy.class, matchresultsRequest);

            print(historyBuy.getData().size());

            // 根据状态差委托
            HuobiRequest orderRequest = new HuobiRequest.Builder().method("GET").host("api.huobi.pro").path("/v1/order/orders")
                    .parameter("symbol", "btcusdt")
                    .parameter("states", "submitted,partial-filled")
                    .build();

            Order order = okHttp.GET(Order.class, orderRequest);


            //884520305

            String accountId = String.valueOf(myHuobi.getAccount().getAccountId());
            print("accountId:" + accountId);

            HuobiRequest orderPlaceRequest = new HuobiRequest.Builder().method("POST").host("api.huobi.pro").path("/v1/order/orders/place")
                    .postJsonBody("account-id", accountId)
                    .postJsonBody("symbol", "btcusdt")
                    .postJsonBody("amount", "0.000001")
                    .postJsonBody("price", "18888.00")
                    .postJsonBody("type", "sell-limit")
                    .postJsonBody("source", "api")
                    .build();

            HuobiResult huobiResult = okHttp.POST(HuobiResult.class, orderPlaceRequest);

            print(huobiResult.getStatus());
        }

    }


    private static void print(Object object){
        System.out.println(object);
    }
}
