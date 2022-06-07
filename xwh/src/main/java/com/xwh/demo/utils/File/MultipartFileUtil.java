package com.xwh.demo.utils.File;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author: 谢宇轩 MultipartFile工具类
 * @Description: TODO
 * @DateTime: 2022/6/6 16:25
 **/
public class MultipartFileUtil {

    public static File MultipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若须要防止生成的临时文件重复,能够在文件名后添加随机码

        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
