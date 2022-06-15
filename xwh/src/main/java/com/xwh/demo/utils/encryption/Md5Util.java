package com.xwh.demo.utils.encryption;

import org.springframework.util.Base64Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: 谢宇轩 MD5加密
 * @Description: TODO
 * @DateTime: 2022/6/15 17:04
 **/
public class Md5Util {

    public  static String md5(String password) {
        MessageDigest messageDigest = null;
        String newPassword = "";
        try {
            // 创建一个MessageDigest对象，并且设置好加密算法
            messageDigest = MessageDigest.getInstance("md5");
            // 对指定的字节进行更新加密
            messageDigest.update(password.getBytes());
            // 进行最终计算且返回byte[]
            byte[] bytes = messageDigest.digest();
            newPassword = Base64Utils.encodeToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // md5是一种加密算法 sha-1也是
        return newPassword;
    }
}
