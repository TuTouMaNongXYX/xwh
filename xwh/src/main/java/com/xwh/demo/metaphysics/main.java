package com.xwh.demo.metaphysics;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 谢宇轩 命理玄学知识图谱-观音灵签 奇奇怪怪的玄学
 * @Description: TODO
 * @DateTime: 2022/6/22 10:51
 **/
public class main {
    //API产品路径
    static String host = "http://gylq.market.alicloudapi.com";
    static String path = "/ai_metaphysics/guan_yin_lin_qian/elite";
    static String method = "GET";
    //阿里云APPCODE
    static String appcode = "";

    public static void fortuneTelling(String timeOfBirth, String familyName, String lastName, String sex) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        //UUID采用当前程序运行时间，用于防止重放攻击，开发者可根据自己需求，自定义字符串
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String UUID = df.format(new Date());
        headers.put("X-Ca-Nonce", UUID);
        Map<String, String> querys = new HashMap<String, String>();
        //参数配置
        //生日，如：20180808080808，即2018年08月08日08时08分08秒，若无法确定确切小时分钟与秒，请用00表示，如：20180808000000
        querys.put("BIRTH", timeOfBirth);
        //姓氏，如：张
        querys.put("FIRST_NAME", familyName);
        //性别，如：男
        querys.put("GENDER", sex);
        //名称，如：无忌
        querys.put("LAST_NAME", lastName);
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



