package com.xwh.demo.entity.vo;

import com.xwh.demo.Exception.MyException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 谢宇轩 快递
 * @Description: TODO
 * @DateTime: 2022/5/9 17:08
 **/
@Data
@ApiModel(value = "快递物流信息", description = "快递物流")
public class kd {

    //提示信息
    @ApiModelProperty(value = "提示信息")
    String msg;
    //结果集
    @ApiModelProperty(value = "结果集")
    result result;
    //状态码
    @ApiModelProperty(value = "状态码")
    String status;
    /*
     201快递单号为空
     202快递公司为空
     203快递公司不存在
     204快递公司识别失败
     205 没有信息；单号错误 (一个单号对应多个快递公司，请求须指定快递公司)
     207 错误单号；一个单号对应多个快递公司，请求须指定快递公司
    */


    /**
     * @return
     * @description 快递状态算法
     * @author 谢宇轩
     * @date 2022/5/10 8:38
     * @params
     */
    public List<kdxx> ExpressStatusAlgorithm(List<kdxx> list) {
        for (int i = 0; i < list.size(); i++) {
            kdxx kdxx = list.get(i);
            kdxx.setCreateTime(kdxx.getTime());
            kdxx.setAcceptStation(kdxx.getStatus());
            //状态概率
            //发货
            int Shipped = appearNumber(kdxx.getStatus(), "揽收") + appearNumber(kdxx.getStatus(), "已揽收");
            if (Shipped > 0 || i == list.size() - 1) {
                kdxx.setStatusName("已发货");
                continue;
            }
            //运输
            int Transport = appearNumber(kdxx.getStatus(), "到达") + appearNumber(kdxx.getStatus(), "离开") + appearNumber(kdxx.getStatus(), "收入") + appearNumber(kdxx.getStatus(), "发出")+appearNumber(kdxx.getStatus(), "揽投")+ appearNumber(kdxx.getStatus(), "投递")
                    + appearNumber(kdxx.getStatus(), "到达")+ appearNumber(kdxx.getStatus(), "离开")+ appearNumber(kdxx.getStatus(), "打包")+ appearNumber(kdxx.getStatus(), "发往")+ appearNumber(kdxx.getStatus(), "发车")
                    + appearNumber(kdxx.getStatus(), "分拣")+ appearNumber(kdxx.getStatus(), "营业点")+ appearNumber(kdxx.getStatus(), "中心");
            //配送
            int Delivery = appearNumber(kdxx.getStatus(), "派送") + appearNumber(kdxx.getStatus(),
                    "快递") + appearNumber(kdxx.getStatus(), "派件");
            //代收
            int Collection = appearNumber(kdxx.getStatus(), "代收") + appearNumber(kdxx.getStatus(), "暂存");
            //签收
            int signForReceipt = appearNumber(kdxx.getStatus(), "已签收") + appearNumber(kdxx.getStatus(), "签收")
                    + appearNumber(kdxx.getStatus(), "取走");
            //退回
            int goBack  = appearNumber(kdxx.getStatus(), "退回");

            if (goBack >0){
                kdxx.setStatusName("退回拦截");
                list.set(i, kdxx);
                continue;
            }

            if (signForReceipt  > Delivery) {
                kdxx.setStatusName("已签收");
                list.set(i, kdxx);
                continue;
            }
            if (Transport > Delivery && Transport > Collection) {
                kdxx.setStatusName("运输中");
                list.set(i, kdxx);
                continue;
            } else if (Delivery > Transport && Delivery > Collection) {
                kdxx.setStatusName("派送中");
                list.set(i, kdxx);
                continue;
            } else if (Collection > Transport && Collection > Delivery) {
                kdxx.setStatusName("代收点");
                list.set(i, kdxx);
                continue;
            } else {
                new MyException("快递算法异常....");
            }
        }
        return list;
    }

    /**
     * 获取指定字符串出现的次数
     *
     * @param srcText  源字符串
     * @param findText 要查找的字符串
     * @return
     */
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }

}














