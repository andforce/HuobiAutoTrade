package fakedata;

import com.andforce.assets.Account;
import com.andforce.assets.Asset;
import com.andforce.assets.MyHuobi;
import com.andforce.bean.HuobiResult;
import com.andforce.network.HuobiRequest;
import com.andforce.utils.NumberUtils;
import com.andforce.utils.TimeUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FakeUtils {

    public static float USDT = 0.0f;
    public static double BTC = 0.05151551f;
    public static double XRP = 584.4278f;

    public void write(String content){
        try {
            FileWriter writer = new FileWriter("fakedata.txt", true);
            writer.write(content + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MyHuobi makeFakeData(){

        MyHuobi myHuobi = MyHuobi.getInstance();
        myHuobi.setAccount(new Account(1682181));

        ArrayList<Asset> assets = new ArrayList<>();
        assets.add(new Asset("btc","trade", 0.05151551));
        assets.add(new Asset("xrp","trade", 584.4278));
        myHuobi.setAssets(assets);

        return myHuobi;
    }

    public void buy(String symbol, float buyAmount, float usdt){

        if (symbol.toLowerCase().equals("xrpusdt")){
            if (buyAmount < 1.f){
                write(TimeUtils.getTimeStr() + " 购买XRP失败， 数量：" + buyAmount);
                return;
            }
        } else {
            if (buyAmount < 0.001f){
                write(TimeUtils.getTimeStr() + " 购买BTC失败， 数量：" + buyAmount);
                return;
            }
        }
        // 消耗USDT
        float realPrice = formatFloat(usdt);
        float realBuyAmount = formatFloat(buyAmount);

        float totalUSDT = realPrice * realBuyAmount;
        float fee = buyAmount * 0.002f;

        USDT -= totalUSDT;

        String buy;
        if (symbol.toLowerCase().equals("xrpusdt")){
            XRP += buyAmount;
            buy = TimeUtils.getTimeStr() + " \tbuy:" + symbol + " account:" + realBuyAmount + " price:"  + realPrice + " fee:[" + fee + "XRP] 实际所得XRP:" + (buyAmount - fee) + " 账户USDT总量:" + USDT + " xrp:" + XRP + " btc:" + BTC;
        } else {
            BTC += buyAmount;
            buy = TimeUtils.getTimeStr() + " \tbuy:" + symbol + " account:" + realBuyAmount + " price:"  + realPrice + " fee:[" + fee + "BTC] 实际所得USDT:" + (buyAmount - fee) + " 账户USDT总量:" + USDT + " xrp:" + XRP + " btc:" + BTC;
        }
        System.out.println(buy);
        write(buy);

    }

    private float formatFloat(float price) {
        return NumberUtils.shortFloat(price, 2);
    }

    public void sell(String symbol, float sellCount, float price){

        // 获取USDT 手续费千分之二
        float realPrice = formatFloat(price);
        float realBuyAmount = formatFloat(sellCount);

        float total = realPrice * realBuyAmount;
        float fee = total * 0.002f;

        USDT += total;
        if (symbol.toLowerCase().equals("xrpusdt")){
            XRP -= sellCount;
        } else {
            BTC -= sellCount;
        }
        String sell = TimeUtils.getTimeStr() + " \tsell:" + symbol + " account:" + realBuyAmount + " price:"  + realPrice + " fee:" + fee + " 实际所得USDT:" + (total - fee) + " 账户USDT总量:" + USDT + " xrp:" + XRP + " btc:" + BTC;
        System.out.println(sell);
        write(sell);
    }

}
