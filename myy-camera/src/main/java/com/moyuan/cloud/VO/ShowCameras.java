package com.moyuan.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author wyk
 * @description:
 * @date 2019-03-30 18:41
 * orgId:
 * buildName: 楼名
 * floorName: 楼层
 * roomName: 房间名
 */
@Setter
@Getter
@ApiModel(value = "ShowCameras",description = "")
public class ShowCameras implements Serializable {
    @ApiModelProperty(name = "camid",value = "摄像头id",dataType = "long",required = true,example = "1")
    private long camid;

    @ApiModelProperty(name = "areaonename",value = "区域一",dataType = "String",required = false,example = "sd")
    private String areaonename;

    @ApiModelProperty(name = "areatwoname",value = "区域二",dataType = "String",required = false,example = "sd")
    private String areatwoname;

    @ApiModelProperty(name = "camname",value = "摄像头",dataType = "String",required = false,example = "sd")
    private String camname;

    @ApiModelProperty(name = "dangernum",value = "险情量",dataType = "long",example = "101")
    private long dangernum;

    @ApiModelProperty(name = "longitude",value = "经度坐标",dataType = "String",example = "101.1111")
    private String longitude;

    @ApiModelProperty(name = "latitude",value = "纬度坐标",dataType = "String",example = "202.2222")
    private String latitude;


}