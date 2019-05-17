package com.ybb.mall.web.rest.util;


import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User : 黄志成
 * Date : 2018/12/3
 * Desc : OkHttp工具类
 */

public class OkHttpUtil {
    private static MediaType MEDIA_TYPE_TEXT = MediaType.parse("Text/plain");
    private static OkHttpClient client = null;

    public static OkHttpClient getInstance() {
        if (client == null) {
            synchronized (OkHttpUtil.class) {
                if (client == null) {
                    client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder()
                                    .addHeader("Content-Type", "application/json")
                                    .build();
                            return chain.proceed(request);
                        }

                    }).hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String s, SSLSession sslSession) {
                            return true;
                        }
                    }).cookieJar(new CookieJar() {
                        private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

                        @Override
                        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                            cookieStore.put(url.host(), cookies);
                        }

                        @Override
                        public List<Cookie> loadForRequest(HttpUrl url) {
                            List<Cookie> cookies = cookieStore.get(url.host());
                            return cookies != null ? cookies : new ArrayList<Cookie>();
                        }
                    }).build();
                }
            }

        }
        return client;
    }


    public static JSONObject getRequest(String url , String token) {
        Request okRequest = null;
        if(token != null){
            okRequest = new Request.Builder().url(url).addHeader("Authorization",token).get().build();
        }else{
            okRequest = new Request.Builder().url(url).get().build();
        }

        try {
            Response okResponse  = OkHttpUtil.getInstance().newCall(okRequest).execute();
            if (!okResponse.isSuccessful()) {
                throw new IOException("服务器端错误: " + okResponse);
            } else {
                return (JSONObject) JSONObject.parse(okResponse.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static JSONObject postRequest(String url, RequestBody requestBody , String token) {
        Request okRequest = null;
        if(token != null){
            okRequest = new Request.Builder().url(url).addHeader("Authorization",token).post(requestBody).build();
        }else{
            okRequest = new Request.Builder().url(url).post(requestBody).build();
        }

        try {
            Response okResponse   = OkHttpUtil.getInstance().newCall(okRequest).execute();
            if (!okResponse.isSuccessful()) {
                throw new IOException("服务器端错误: " + okResponse);
            } else {
                return (JSONObject) JSONObject.parse(okResponse.body().string());
            }
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject putRequest(String url, RequestBody requestBody , String token){
        Request okRequest = null;
        if(token != null){
            okRequest = new Request.Builder().url(url).addHeader("Authorization",token).put(requestBody).build();
        }else{
            okRequest = new Request.Builder().url(url).put(requestBody).build();
        }

        try {
            Response okResponse   = OkHttpUtil.getInstance().newCall(okRequest).execute();
            if (!okResponse.isSuccessful()) {
                throw new IOException("服务器端错误: " + okResponse);
            } else {
                return (JSONObject) JSONObject.parse(okResponse.body().string());
            }
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }


    public static JSONObject patchRequest(String url , RequestBody requestBody , String token) {
        Request okRequest = null;
        if(token != null){
            okRequest = new Request.Builder().url(url).addHeader("Authorization",token).patch(requestBody).build();
        }else{
            okRequest = new Request.Builder().url(url).patch(requestBody).build();
        }

        try {
            Response okResponse  = OkHttpUtil.getInstance().newCall(okRequest).execute();
            if (!okResponse.isSuccessful()) {
                throw new IOException("服务器端错误: " + okResponse);
            } else {
                return (JSONObject) JSONObject.parse(okResponse.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static JSONObject deleteRequest(String url , String token) {
        Request okRequest = null;
        if(token != null){
            okRequest = new Request.Builder().url(url).addHeader("Authorization",token).delete().build();
        }else{
            okRequest = new Request.Builder().url(url).delete().build();
        }

        try {
            Response okResponse  = OkHttpUtil.getInstance().newCall(okRequest).execute();
            if (!okResponse.isSuccessful()) {
                throw new IOException("服务器端错误: " + okResponse);
            } else {
                return (JSONObject) JSONObject.parse(okResponse.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String xmlPostRequest(String url, RequestBody requestBody ) {
        Request okRequest = null;
        okRequest = new Request.Builder().url(url).post(requestBody).build();
        try {
            Response okResponse   = OkHttpUtil.getInstance().newCall(okRequest).execute();
            if (!okResponse.isSuccessful()) {
                throw new IOException("服务器端错误: " + okResponse);
            } else {
                return okResponse.body().string();
            }
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
