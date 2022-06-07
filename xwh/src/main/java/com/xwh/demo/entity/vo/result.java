package com.xwh.demo.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: 谢宇轩
 * @Description: TODO
 * @DateTime: 2022/5/9 17:09
 **/
@Data
@ApiModel(value="快递物流详情", description="快递物流")
public class result {

    //快递公司名字
    @ApiModelProperty(value = "快递公司名字")
    String expName;

    //发货到收货耗时(截止最新轨迹)
    @ApiModelProperty(value = "发货到收货耗时")
    String takeTime;

    //快递公司官网
    @ApiModelProperty(value = "快递公司官网")
    String expSite;

    //快递公司电话
    @ApiModelProperty(value = "快递公司电话")
    String expPhone;

    //最新轨迹的时间
    @ApiModelProperty(value = "最新轨迹的时间")
    String updateTime;

    //快递公司名字
    @ApiModelProperty(value = "快递公司名字")
    String type;

    //事件轨迹集
    @ApiModelProperty(value = "事件轨迹集")
    List<kdxx> list;

    //是否本人签收 0否1是
    @ApiModelProperty(value = "是否本人签收")
    String issign;

    //运单编号
    @ApiModelProperty(value = "运单编号")
    String number;

    //投递状态 0快递收件(揽件)1在途中 2正在派件 3已签收 4派送失败 5.疑难件 6.退件签收
    @ApiModelProperty(value = "投递状态")
    String deliverystatus;

    //快递员
    @ApiModelProperty(value = "快递员")
    String courier;

    @ApiModelProperty(value = "快递商图片路径")
    String logo;

    //	快递员电话
    @ApiModelProperty(value = "快递员电话")
    String courierPhone;



}
