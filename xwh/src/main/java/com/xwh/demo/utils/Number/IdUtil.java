package com.xwh.demo.utils.Number;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 谢宇轩 id生成工具类
 * @Description: TODO
 * @DateTime: 2022/6/7 10:16
 **/
public class IdUtil {

    /**
     * @return
     * @description 根据时间生成id
     * @author 谢宇轩
     * @date 2022/4/24 11:18
     * @params
     */
    public static synchronized Long getUUIDTOLong() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String msg = "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        msg = sdf.format(date).substring(2);
        return Long.parseLong(msg);
    }

}
