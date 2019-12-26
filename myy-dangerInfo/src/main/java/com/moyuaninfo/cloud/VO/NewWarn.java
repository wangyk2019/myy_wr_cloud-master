package com.moyuaninfo.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author big boy
 * @description:
 * @date 2019-03-30 18:41
 */
@Setter
@Getter
@ApiModel(value = "NewWarn",description = "险情统计信息")
public class NewWarn {
    @ApiModelProperty(name = "id",value = "险情id",dataType = "Long",required = true,example = "12")
    private Long id;
    @ApiModelProperty(name = "warnName",value = "险情名称",dataType = "String",required = true,example = "人行移动")
    private String warnName;
    @ApiModelProperty(name = "pic",value = "险情图片",dataType = "String",required = true,example = "12")
    private String pic;
    @ApiModelProperty(name = "cameraName",value = "摄像头名称",dataType = "String",required = true,example = "11")
    private String cameraName;
    @ApiModelProperty(name = "areaName",value = "位置",dataType = "String",required = true,example = "11")
    private String areaName;
    @ApiModelProperty(name = "time",value = "时间",dataType = "String",required = true,example = "人行移动")
    private String time;
}