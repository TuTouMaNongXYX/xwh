package com.xwh.demo.cloud.alibaba.identityCardVerification;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: 谢宇轩  身份证验证
 * @Description: TODO
 * @DateTime: 2022/6/22 10:27
 **/
public class verification {

    //请求地址
    static String url = "https://eid.shumaidata.com/eid/check";
    //阿里云
    static String appCode = "";

    /**
     * @return
     * @description 身份证验证
     * @author 谢宇轩
     * @date 2022/6/22 10:27
     * @params
     */
    public static String is(String sfz, String name) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("idcard", sfz);
        params.put("name", name);
        String result = postForm(appCode, url, params);
        return result;
    }


    /**
     * 用到的HTTP工具包：okhttp 3.13.1
     * <dependency>
     * <groupId>com.squareup.okhttp3</groupId>
     * <artifactId>okhttp</artifactId>
     * <version>3.13.1</version>
     * </dependency>
     */
    public static String postForm(String appCode, String url, Map<String, String> params) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        FormBody.Builder formbuilder = new FormBody.Builder();
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            formbuilder.add(key, params.get(key));
        }
        FormBody body = formbuilder.build();
        Request request = new Request.Builder().url(url).addHeader("Authorization", "APPCODE " + appCode).post(body).build();
        Response response = client.newCall(request).execute();
        System.out.println("返回状态码" + response.code() + ",message:" + response.message());
        String result = response.body().string();
        return result;
    }

}
