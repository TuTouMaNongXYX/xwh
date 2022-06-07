package com.xwh.demo.cloud.tencent;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
// 导入对应产品模块的client
import com.tencentcloudapi.cvm.v20170312.CvmClient;
// 导入要请求接口对应的request response类
import com.tencentcloudapi.cvm.v20170312.models.DescribeInstancesRequest;
import com.tencentcloudapi.cvm.v20170312.models.DescribeInstancesResponse;
import com.tencentcloudapi.cvm.v20170312.models.Filter;
//导入可选配置类
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.profile.Language;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: 谢宇轩 腾讯云短信 使用时需要注入不然无法从配置文件读取值
 * @Description: TODO
 * @DateTime: 2022/6/7 10:29
 **/
@Component
public class shortMessage {

    @Value("${tencent.secretId}")
    String secretId;
    @Value("${tencent.secretKey}")
    String secretKey;
    @Value("${tencent.SmsSdkAppId}")
    String SmsSdkAppId;
    @Value("${tencent.SignName}")
    String SignName;
    @Value("${tencent.TemplateId}")
    String TemplateId;


    /**
     * @return
     * @description 发送短信
     * @author 谢宇轩
     * @date 2022/6/7 10:37
     * @params 电话数组，验证码数组
     */
    public  void sendMessages(String[] phoneNumber, String[] templateParam) {
        try {
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential(secretId, secretKey);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendSmsRequest req = new SendSmsRequest();
            //手机号数组
            String[] phoneNumberSet1 = phoneNumber;
            req.setPhoneNumberSet(phoneNumberSet1);

            //短信应用id   “SmsSdkAppId”   填入上文获取的对应参数   这里示例随便填的
            req.setSmsSdkAppId(SmsSdkAppId);
            //签名内容   “SignName”   填入上文获取的对应参数   这里示例随便填的
            req.setSignName(SignName);
            //正文模板id   “TemplateId”   填入上文获取的对应参数   这里示例随便填的
            req.setTemplateId(TemplateId);

            //验证码数组
            String[] templateParamSet1 = templateParam;
            req.setTemplateParamSet(templateParamSet1);

            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSmsSdkAppId() {
        return SmsSdkAppId;
    }

    public void setSmsSdkAppId(String smsSdkAppId) {
        SmsSdkAppId = smsSdkAppId;
    }

    public String getSignName() {
        return SignName;
    }

    public void setSignName(String signName) {
        SignName = signName;
    }

    public String getTemplateId() {
        return TemplateId;
    }

    public void setTemplateId(String templateId) {
        TemplateId = templateId;
    }
}


