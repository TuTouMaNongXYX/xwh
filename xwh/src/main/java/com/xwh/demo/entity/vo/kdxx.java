package com.xwh.demo.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: 谢宇轩 快递具体信息
 * @Description: TODO
 * @DateTime: 2022/5/9 17:10
 **/
@Data
@ApiModel(value="快递物流具体信息", description="快递物流")
public class kdxx {

    //时间点
    @ApiModelProperty(value = "时间点")
    String time;
    //事件详情
    @ApiModelProperty(value = "事件详情")
    String status;

    //状态
    @ApiModelProperty(value = "状态")
    String statusName;

    //事件详情
    @ApiModelProperty(value = "事件详情")
    String acceptStation;

    //时间点
    @ApiModelProperty(value = "时间点")
    String createTime;

}
