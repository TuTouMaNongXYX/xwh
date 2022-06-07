package com.xwh.demo.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author: 谢宇轩 邮件工具类
 * @Description: TODO
 * @DateTime: 2022/6/7 10:13
 **/
public class MailUtil {
    @Resource
    private JavaMailSender sender;

    /**
     * @return
     * @description 发送邮件（简单）
     * @author 谢宇轩
     * @date 2022/6/2 16:01
     * @params
     */
    public void simpleMail(String zt, String msg, String to) {
        //发送一封简单邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText(msg);
        mailMessage.setSubject(zt);
        mailMessage.setFrom("3390380439@qq.com");//发件人邮箱
        mailMessage.setTo(to);//收件人邮箱
        sender.send(mailMessage);
    }


    /**
     * @return
     * @description 复杂邮件 msg可以存放html等
     * @author 谢宇轩
     * @date 2022/6/2 16:18
     * @params
     */
    public void complexMail(String zt, String msg, String to, File file, String filename) throws MessagingException {
        //发送一封复杂的邮件
        MimeMessage mimeMessage=sender.createMimeMessage();
        //组装
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
        helper.setText(msg,true);
        helper.setSubject(zt);
        //添加附件
        helper.addAttachment(filename,file);//上传本地文件
        helper.setFrom("3390380439@qq.com");
        helper.setTo(to);
        sender.send(mimeMessage);
    }

    //定时任务
//    @Scheduled(cron = "0/2 * * * * ?")
}
