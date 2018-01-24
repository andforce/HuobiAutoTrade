import com.andforce.assets.Asset;
import com.andforce.assets.MyHuobi;
import com.andforce.assets.OrderInfo;
import com.andforce.bean.HuobiResult;
import com.andforce.bean.account.Account;
import com.andforce.bean.balance.Balance;
import com.andforce.bean.balance.List;
import com.andforce.bean.historybuy.HistoryBuy;
import com.andforce.bean.order.Data;
import com.andforce.bean.order.Order;
import com.andforce.network.HuobiRequest;
import com.andforce.network.okhttp.HuobiOkHttp;
import com.andforce.network.websocket.HuobiWebSocket;

import java.net.URISyntaxException;
import java.util.ArrayList;


public class Main {



    public static void main(String[] args) throws URISyntaxException {


        HuobiOkHttp okHttp = new HuobiOkHttp();


        MyHuobi myHuobi = MyHuobi.getInstance();

        myHuobi.refresh();


        print("------------------");


        if (false){
            HuobiWebSocket.executeWebSocket();
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
