package com.xwh.demo.logistics;

import cn.dev33.satoken.util.SaFoxUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwh.demo.Exception.MyException;
import com.xwh.demo.entity.Restful;
import com.xwh.demo.entity.vo.kd;
import com.xwh.demo.entity.vo.result;
import com.xwh.demo.utils.Io.IoUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * @Author: 谢宇轩 阿里云-快递查询
 * @Description: TODO
 * @DateTime: 2022/6/7 15:14
 **/
@Component
public class alibaba {

    @Value("${logistics.host}")
    private String host;

    @Value("{logistics.path}")
    private String path;

    @Value("{logistics.appcode}")
    private String appcode;


    /**
     * @return
     * @description 快递查询
     * @author 谢宇轩
     * @date 2022/6/7 15:15
     * @params 快递单号  快递商编码  电话
     */
    public Restful expressInquiry(String coding, String operator, String phone) throws MyException {
        //顺丰快递 单号需特殊处理
        if (operator.equals("SFEXPRESS")) {
            if (SaFoxUtil.isEmpty(phone)) {
                throw new MyException("顺丰快递缺失电话");
            }
            String p = phone.substring(9);
            coding = coding + ":" + p;
        }
        //最终请求地址 拼接请求链接
        String urlSend = host + path + "?no=" + coding + "&type=" + operator;
        String json = null;
        try {
            URL url = new URL(urlSend);
            HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
            httpURLCon.setRequestProperty("Authorization", "APPCODE " + appcode);// 格式Authorization:APPCODE
            // (中间是英文空格)
            int httpCode = httpURLCon.getResponseCode();
            if (httpCode == 200) {
                json = IoUtil.read(httpURLCon.getInputStream());
                System.out.println("正常请求计费(其他均不计费)");
                System.out.println("获取返回的json:");
                System.out.print(json);
            } else {
                Map<String, List<String>> map = httpURLCon.getHeaderFields();
                String error = map.get("X-Ca-Error-Message").get(0);
                if (httpCode == 400 && error.equals("Invalid AppCode `not exists`")) {
                    System.out.println("AppCode错误 ");
                    return Restful.error().message("AppCode错误 ");
                } else if (httpCode == 400 && error.equals("Invalid Url")) {
                    System.out.println("请求的 Method、Path 或者环境错误");
                    return Restful.error().message("请求的 Method、Path 或者环境错误 ");
                } else if (httpCode == 400 && error.equals("Invalid Param Location")) {
                    System.out.println("参数错误");
                    return Restful.error().message("参数错误 ");
                } else if (httpCode == 403 && error.equals("Unauthorized")) {
                    System.out.println("服务未被授权（或URL和Path不正确）");
                    return Restful.error().message("服务未被授权（或URL和Path不正确） ");
                } else if (httpCode == 403 && error.equals("Quota Exhausted")) {
                    System.out.println("套餐包次数用完 ");
                    return Restful.error().message("套餐包次数用完 ");
                } else if (httpCode == 403 && error.equals("Api Market Subscription quota exhausted")) {
                    System.out.println("套餐包次数用完，请续购套餐");
                    return Restful.error().message("套餐包次数用完，请续购套餐 ");
                } else {
                    System.out.println("参数名错误 或 其他错误");
                    System.out.println(error);
                    return Restful.error().message("参数名错误 或 其他错误 " + error);
                }
            }

        } catch (MalformedURLException e) {
            System.out.println("URL格式错误");
        } catch (UnknownHostException e) {
            System.out.println("URL地址错误");
        } catch (Exception e) {
            // 打开注释查看详细报错异常信息
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(jsonObject);
        kd kd = JSON.toJavaObject(jsonObject, kd.class);
        result result = kd.getResult();
        result.setList(kd.ExpressStatusAlgorithm(result.getList()));
        kd.setResult(result);
        return Restful.ok(kd);
    }


}
