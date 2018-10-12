package com.wdkj.utils.http.client;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpCaller {

    private static final Logger log = LoggerFactory.getLogger(HttpCaller.class);

    /**
     * 延迟初始化，延迟初始化需要考虑同步问题
     */
    private static HttpCaller httpCaller;

    private static OkHttpClient CLIENT_INSTANCE;

    private HttpCaller() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);
        builder.readTimeout(3 * 60, TimeUnit.SECONDS);
        CLIENT_INSTANCE = builder.build();
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static synchronized HttpCaller getInstance() {
        if (httpCaller == null) {
            httpCaller = new HttpCaller();
        }
        return httpCaller;

    }

    /**
     * 获取
     *
     * @param params
     * @return
     */
    public String getAsString(String url, Map<String, String> params) {

        try {
            Request request = new Request.Builder()
                    .url(url)//请求接口。如果需要传参拼接到接口后面。
                    .build();//创建Request 对象
            Response response = null;
            response = CLIENT_INSTANCE.newCall(request).execute();//得到Response 对象
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                if (log.isDebugEnabled()) log.debug(url + " failed, code is " + response.code());
                throw new RuntimeException("请求失败，code:" + response.code());
            }
        } catch (Exception e) {
            log.error("http calling exception :", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取
     *
     * @param url
     * @return
     */
    public String getAsString(String url) {

        return this.getAsString(url, null);
    }

    /**
     * 获取
     *
     * @param params
     * @return
     */
    public String postAsString(Map<String, String> params) {

        return "";
    }

    /**
     * 获取
     *
     * @param params
     * @return
     */
    public String postAsString(String url, Map<String, String> params, String fileName, byte[] data) {
        try {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);

            //参数
            if (params != null) {
                for (String key : params.keySet()) {
                    builder.addFormDataPart(key, params.get(key));
                }
            }

            builder.addFormDataPart("file", fileName, RequestBody.create(MediaType.parse("application/octet-stream"), data));

            Request request = new Request.Builder().method("post", builder.build())
                    .url(url)//请求接口。如果需要传参拼接到接口后面。
                    .build();//创建Request 对象
            Response response = CLIENT_INSTANCE.newCall(request).execute();//得到Response 对象

            return response.body().string();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
