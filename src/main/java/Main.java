import com.andforce.assets.MyHuobi;
import com.andforce.bean.historybuy.HistoryBuy;
import com.andforce.network.HuobiRequest;
import com.andforce.network.okhttp.BuyOrSellApi;
import com.andforce.network.okhttp.HuobiOkHttp;
import com.andforce.network.websocket.HuobiWebSocket;
import com.andforce.network.websocket.OnExpectListener;
import com.andforce.utils.TimeUtils;
import fakedata.FakeUtils;

import java.net.URISyntaxException;


public class Main {




    public static void main(String[] args) throws URISyntaxException {




        //new FakeUtils().write("");

        HuobiOkHttp okHttp = new HuobiOkHttp();


        MyHuobi myHuobi = MyHuobi.getInstance();

        myHuobi.refresh();

        double usdt = myHuobi.getTotalUSDT();

        System.out.println("USDT: " + usdt);
        if (false){


            FakeUtils fakeUtils = new FakeUtils();
            MyHuobi fakeMyHuobi = fakeUtils.makeFakeData();

            HuobiWebSocket huobiWebSocket = HuobiWebSocket.getInstance();

            huobiWebSocket.startMonitorPrice(new OnExpectListener() {

                @Override
                public void onExpect(String buyOrSell, String symbol, float nowPrice, float rate) {
                    huobiWebSocket.pause(symbol);


                    if (buyOrSell.equals("buy")){
                        float account = FakeUtils.USDT / nowPrice;
                        fakeUtils.buy(symbol, account, nowPrice);
                    } else {
                        if (symbol.equals("xrpusdt")){
                            if (FakeUtils.XRP >= 1.f){
                                fakeUtils.sell(symbol, (float) FakeUtils.XRP, nowPrice);
                            }
                        } else {
                            if (FakeUtils.BTC >= 0.001f){
                                fakeUtils.sell(symbol, (float) FakeUtils.BTC, nowPrice);
                            }
                        }

                    }

//                    BuyOrSellApi api = new BuyOrSellApi();
//
//                    api.buy(String.valueOf(myHuobi.getAccount().getAccountId()),symbol, 1f, 1.3333f);
//
//                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//                    System.out.println(buyOrSell + "\t币种:" + symbol + "\t现价:" + nowPrice +"\t跌涨:" + rate);
//                    System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                }
            },new String[]{/*"xrpusdt", */"btcusdt"});



            // 已经成交的委托
            HuobiRequest matchresultsRequest = new HuobiRequest.Builder()
                    .method("GET").host("api.huobi.pro").path("/v1/order/matchresults").build();

            HistoryBuy historyBuy = okHttp.GET(HistoryBuy.class, matchresultsRequest);

            print(historyBuy.getData().size());

        }

    }


    private static void print(Object object){
        System.out.println(object);
    }
}
