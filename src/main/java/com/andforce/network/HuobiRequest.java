package com.andforce.network;

import com.andforce.HuobiConfig;
import com.andforce.utils.HMACUtil;
import com.andforce.utils.TimeUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by andforce on 2018/1/23.
 */
public class HuobiRequest {
    private String mHost;
    private String mPath;
    private String mMethod;
    private String mUrl;
    private Map<String, String> mParameters = new TreeMap<>();

    private JSONObject mPostJsonBody = new JSONObject();

    private HuobiRequest(String method, String host, String path, Map<String, String> parameters, JSONObject postJsonBody){
        this.mMethod = method;
        this.mHost = host;
        this.mPath = path;
        this.mUrl = "https://" + host + path;
        this.mParameters = parameters;
        this.mPostJsonBody = postJsonBody;

    }

    public String url() {
        return mUrl;
    }

    public String host() {
        return mHost;
    }

    public String path() {
        return mPath;
    }

    public String method() {
        return mMethod;
    }

    public String postBody(){
        return mPostJsonBody.toString();
    }

//    public Map<String, String> parameters() {
//        sign(method() ,host(), path(), mParameters);
//        return mParameters;
//    }

    private String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("UTF-8 encoding not supported!");
        }
    }

    public String parametersStr(){
        return sign(method() ,host(), path(), mParameters);
    }

    private String sign(String method, String host, String path, Map<String, String> params) {

        StringBuilder sb = new StringBuilder();
        sb.append(method.toUpperCase()).append('\n')                // GET
                .append(host.toLowerCase()).append('\n')            // Host
                .append(path).append('\n');                         // Path
        params.remove("Signature");
        params.put("AccessKeyId", HuobiConfig.APPKEY);
        params.put("SignatureVersion", "2");
        params.put("SignatureMethod", "HmacSHA256");
        params.put("Timestamp", TimeUtils.getUTCTimeStr("%04d-%02d-%02dT%02d:%02d:%02d"));
        // build sign:
        SortedMap<String, String> map = new TreeMap<>(params);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append('=').append(urlEncode(value)).append('&');
        }
        // remove last '&':
        sb.deleteCharAt(sb.length() - 1);
        // sign:
        Mac hmacSha256 = null;
        try {
            hmacSha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secKey =
                    new SecretKeySpec(HuobiConfig.APP_SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmacSha256.init(secKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm: " + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid key: " + e.getMessage());
        }
        String payload = sb.toString();
        byte[] hash = hmacSha256.doFinal(payload.getBytes(StandardCharsets.UTF_8));
        String actualSign = Base64.getEncoder().encodeToString(hash);
        params.put("Signature", actualSign);

        return HMACUtil.formatUrlMap(params, true, false);
    }

    public static class Builder{
        private String mHost;
        private String mPath;
        private String mMethod;
        private Map<String, String> mParameters = new TreeMap<>();

        private JSONObject mPostJsonBody = new JSONObject();

        public Builder method(String method){
            this.mMethod = method;
            return this;
        }

        public Builder host(String host){
            this.mHost = host;
            return this;
        }

        public Builder path(String path){
            this.mPath = path;
            return this;
        }

        public Builder parameters(Map<String, String> parameter){
            this.mParameters = parameter;
            return this;
        }

        public Builder parameter(String key, String value){
            this.mParameters.put(key, value);
            return this;
        }

        public Builder postJsonBody(String key, String value){
            this.mPostJsonBody.put(key, value);
            return this;
        }

        public Builder postJsonBody(String key, JSONObject value){
            this.mPostJsonBody.put(key, value);
            return this;
        }

        public Builder postJsonBody(String key, JSONArray value){
            this.mPostJsonBody.put(key, value);
            return this;
        }

        public HuobiRequest build(){
            return new HuobiRequest(mMethod, mHost, mPath, mParameters, mPostJsonBody);
        }
    }
}
