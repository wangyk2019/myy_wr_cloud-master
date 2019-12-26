package com.moyuan.signin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MyySigninVo", description = "巡检点简明对象")
public class MyySigninVo {

    @ApiModelProperty(value = "名称", example = "打卡点A")
    private String name;

    @ApiModelProperty(value = "上级区域ID", example = "1")
    private long areaid;

    @ApiModelProperty(value = "经度坐标", example = "经度坐标")
    private String longitude;

    @ApiModelProperty(value = "纬度坐标", example = "纬度坐标")
    private String latitude;

    @ApiModelProperty(value = "是否打卡", example = "1")
    private String state;
}
