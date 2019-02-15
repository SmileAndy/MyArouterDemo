package meiyu.core.http;

import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import meiyu.core.Constant;
import meiyu.core.application.MyApplication;
import meiyu.core.sp.Prefs;
import meiyu.core.sp.SpEnum;
import meiyu.core.utils.SignUtils;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by q on 2017/12/18.
 */

public class SignInterceptor implements Interceptor {
    private String TAG = "SignInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        Response response=chain.proceed(chain.request());
        Map<String, String> keyValueMap = new HashMap<>();
        Map<String, Integer> keyCountMap = new HashMap<>();
        Request.Builder headersBuilder = request.newBuilder();
        try {
            headersBuilder.addHeader("deviceId", getDeviceId());
            headersBuilder.addHeader("producer", getProducer());
            headersBuilder.addHeader("channelId", getChannelId());
            headersBuilder.addHeader("ip", getIp());
            long timestamp = System.currentTimeMillis();
            headersBuilder.addHeader("timestamp", String.valueOf(timestamp));
            headersBuilder.addHeader("platform", "android");
            String token = Prefs.getString(SpEnum.TOKEN.name()
                    , Constant.TOKEN_DEFAULT);
            headersBuilder.addHeader("token", token);
//            headersBuilder.addHeader("User-Agent",getUserAgent());

            request = headersBuilder.build();

            String method = request.method();

            //循环获取get参数
            HttpUrl httpUrlurl = request.url();
            Set<String> parameterNames = httpUrlurl.queryParameterNames();
            for (String key : parameterNames) {  //循环参数列表
                keyValueMap.put(key, httpUrlurl.queryParameter(key));

            }

            //循环获取header
            Set<String> headersKeySet = request.headers().names();
            for (String setKey : headersKeySet) {
//                if ("ApiVersion".equals(setKey)) {
//                    continue;
//                }
                keyValueMap.put(setKey, request.headers().get(setKey));
            }

            //循环获取post的参数
            if ("POST".equals(method)) {
                StringBuilder sb = new StringBuilder();
                if (request.body() instanceof FormBody) {
                    FormBody body = (FormBody) request.body();
                    for (int i = 0; i < body.size(); i++) {
                        String name = body.name(i);
                        String value = body.value(i);
                        if (keyCountMap.containsKey(name)) {
                            keyCountMap.put(name, keyCountMap.get(name) + 1);
                        } else {
                            keyCountMap.put(name, 1);
                            keyValueMap.put(name, value);
                        }
                        sb.append(name + "=" + value + ",");
//                        if( ! ( httpUrlurl.url().getPath().contains("addCart") &&body.name(i).equals("spec") && sb.toString().contains("spec")) ){

//                        }
                    }
                    if(sb.length()>0) {
                        sb.delete(sb.length() - 1, sb.length());
                    }
                    Log.i(TAG, "filter before:" + sb.toString());
                }
            }
            filterMap(keyValueMap, keyCountMap);
            StringBuilder keyValueSb = new StringBuilder();
            for (String key : keyValueMap.keySet()) {
                keyValueSb.append(key + "=" + keyValueMap.get(key) + "&");
            }
            Log.i(TAG, keyValueSb.toString());
            headersBuilder.addHeader("sign", SignUtils.getSignString(keyValueMap));


        } catch (Exception e) {
            e.printStackTrace();
        }

//        okhttp3.MediaType mediaType = response.body().contentType();
//        String content = response.body().string();
        request = headersBuilder.build();
        return chain.proceed(request);
//        return response.newBuilder()
//                .body(okhttp3.ResponseBody.create(mediaType, content))
//                .headers(headers)
//                .build();
    }

    /**
     * 过滤map，如果存在一个key多次放到map中的情况，将该key从map中去掉
     *
     * @param keyValueMap
     * @param keyCountMap
     */
    public void filterMap(Map<String, String> keyValueMap, Map<String, Integer> keyCountMap) {
        for (String key : keyCountMap.keySet()) {
            if (keyCountMap.get(key) > 1) {
                keyValueMap.remove(key);
            }
        }
    }


    public String getDeviceId() {
        String deviceId = "";
        deviceId = SignUtils.comDeviceId();
        return deviceId;
    }

    public String getProducer() {
        String producer = "";
        producer = android.os.Build.MANUFACTURER;
        return producer;
    }

    public String getChannelId() {
        String channelId = "";
        channelId = Constant.CHANNEL_ID;
        return channelId;
    }

    public String getIp() {
        String ip = "";
        ip = SignUtils.getIPAddress();
        return ip;
    }

    private String getUserAgent(){
        String userAgent="";
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.JELLY_BEAN_MR1){
            try{
                userAgent= WebSettings.getDefaultUserAgent(MyApplication.getContext());
            }catch (Exception e){
                userAgent= System.getProperty("http.agent");
            }
        }else{
            userAgent= System.getProperty("http.agent");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

}
