package com.andforce.network.websocket;

import com.alibaba.fastjson.JSONObject;
import com.andforce.TradeConfig;
import com.andforce.bean.market.MarketKLine;
import com.andforce.bean.market.Tick;
import com.andforce.utils.CommonUtils;
import com.google.gson.Gson;
import org.java_websocket.client.DefaultSSLWebSocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

public class HuobiWebSocket extends WebSocketClient {

    private OnExpectListener mOnExpectListener;

    private static final String url = "wss://api.huobi.pro/ws";

    private static HuobiWebSocket mInstance = null;

    private Gson mGson = new Gson();

    private boolean mPause = false;



    public static HuobiWebSocket getInstance(){
        if (mInstance == null) {
            try {
                mInstance = new HuobiWebSocket(new URI(url), getWebSocketHeaders(), 1000);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }
        return mInstance;
    }


    private HuobiWebSocket(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    private HuobiWebSocket(URI serverURI) {
        super(serverURI);
    }

    private HuobiWebSocket(URI serverUri, Map<String, String> headers, int connecttimeout) {
        super(serverUri, new Draft_17(), headers, connecttimeout);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("开流--opened connection");
    }

    @Override
    public void onMessage(ByteBuffer socketBuffer) {
        try {
            String marketStr = CommonUtils.byteBufferToString(socketBuffer);
            String market = CommonUtils.uncompress(marketStr);
            if (market.contains("ping")) {
                //System.out.println(market.replace("ping", "pong"));
                // Client 心跳
                this.send(market.replace("ping", "pong"));
            } else {
                if (market.contains("\"status\":\"ok\"")){
                    //System.out.println(" market:" + market);
                } else {

                    //System.out.println(" market:" + market);

                    if (mPause){
                        return;
                    }

                    MarketKLine kLine = mGson.fromJson(market, MarketKLine.class);
                    Tick tick = kLine.getTick();

                    float rate = (tick.getClose() - tick.getOpen()) * 100 / tick.getOpen();
                    float close = tick.getClose();
                    float open = tick.getOpen();
                    String coin = kLine.getCh().split("\\.")[1].toUpperCase();

                    String closeStr = String.format("%08.2f", close);

                    if (rate >= TradeConfig.SELL){
                        if (mOnExpectListener != null){
                            mOnExpectListener.onExpect("sell", coin, closeStr, rate);
                        }
                    } else if (rate <= TradeConfig.BUY){
                        if (mOnExpectListener != null){
                            mOnExpectListener.onExpect("buy",coin, closeStr, rate);
                        }
                    } else {
                        System.out.println(coin + " \tOpen:\t" + String.format("%08.2f", open) + "\t\tClose:\t" + closeStr + "\t\tRate:\t" + String.format("%.2f%%", rate));
                    }

                }
            }
//			System.out.println(MAP_CUR_RESULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(String message) {
        System.out.println("接收--received: " + message);
    }

    public void onFragment(Framedata fragment) {
        System.out.println("片段--received fragment: " + new String(fragment.getPayloadData().array()));
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("关流--Connection closed by " + (remote ? "remote peer" : "us"));
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("WebSocket 连接异常: " + ex);
    }

    public static Map<String, String> getWebSocketHeaders() throws IOException {
        Map<String, String> headers = new HashMap<String, String>();
        return headers;
    }

    private static void trustAllHosts(HuobiWebSocket appClient) {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        }};

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            appClient.setWebSocketFactory(new DefaultSSLWebSocketClientFactory(sc));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause(){
        mPause = true;
    }

    public void restart(){
        mPause = false;
    }

    public void stop(){
        this.close();
    }

    public void startMonitorPrice(OnExpectListener listener, String[] monitors) {

        mOnExpectListener = listener;

        trustAllHosts(this);
        try {
            this.connectBlocking();
            long monitorId = 0x0001;
            for (String monitor : monitors) {
                com.andforce.network.websocket.HuobiWebSocket.SubModel subModel1 = new com.andforce.network.websocket.HuobiWebSocket.SubModel();
                String kline = String.format("market.%s.kline.1day", monitor.toLowerCase());
                subModel1.setSub(kline);
                subModel1.setId(monitorId++);
                this.send(JSONObject.toJSONString(subModel1));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 订阅K线数据 sub 根据自己需要订阅数据



        // 订阅数据深度
//		com.andforce.network.websocket.HuobiWebSocket.SubModel subModel1 = new com.andforce.network.websocket.HuobiWebSocket.SubModel();
//		subModel1.setSub("market.btccny.depth.percent10");
//		subModel1.setId(10001L);
//		this.send(JSONObject.toJSONString(subModel1));
        // 取消订阅省略

        // 请求数据 sub 根据自己需要请求数据
//		ReqModel reqModel = new ReqModel();
//		reqModel.setReq("market.btccny.depth.percent10");
//		reqModel.setId(10002L);
//		this.send(JSONObject.toJSONString(reqModel));

        // 请求数据
//		ReqModel reqModel1 = new ReqModel();
//		reqModel1.setReq("market.btccny.detail");
//		reqModel1.setId(10003L);
//		this.send(JSONObject.toJSONString(reqModel1));
//		System.out.println("send : " + JSONObject.toJSONString(reqModel));
    }

    public static class SubModel {
        private String sub;
        private Long id;

        public String getSub() {
            return sub;
        }

        public void setSub(String sub) {
            this.sub = sub;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
