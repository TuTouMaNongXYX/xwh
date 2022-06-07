package com.xwh.demo.utils.Io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author: 谢宇轩 io流工具类
 * @Description: TODO
 * @DateTime: 2022/6/7 10:16
 **/
public class IoUtil {

    /*
     * 读取返回结果
     */
    public static String read(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

}
