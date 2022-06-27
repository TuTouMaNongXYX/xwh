package com.xwh.demo.utils.Img;

import com.xwh.demo.Exception.MyException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: 谢宇轩  图片工具类
 * @Description: TODO
 * @DateTime: 2022/6/27 9:04
 **/
public class ImgUtil {


    /**
     * @return 图片压缩
     * @description
     * @author 谢宇轩
     * @date 2022/6/27 9:05
     * @params
     */
    public static InputStream getImageCom(MultipartFile file) throws IOException, MyException {
        //获取文件输入流
        InputStream inputStream = file.getInputStream();
        try {
            // 把图片读入到内存中
            BufferedImage bufImg = ImageIO.read(inputStream);
            // 压缩代码,存储图片文件byte数组
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            //防止图片变红,这一步非常重要
            BufferedImage bufferedImage = new BufferedImage(bufImg.getWidth(), bufImg.getHeight(), BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics().drawImage(bufImg, 0, 0, Color.WHITE, null);
            //先转成jpg格式来压缩,然后在通过OSS来修改成源文件本来的后缀格式
            ImageIO.write(bufferedImage, "jpg", bos);
            return new ByteArrayInputStream(bos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
        throw new MyException("图片转化异常");
    }


}
