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
@ApiModel(value = "MapCameras",description = "")
public class MapCameras implements Serializable {
    @ApiModelProperty(name = "camid",value = "摄像头id",dataType = "long",required = true,example = "1")
    private long camid;

    @ApiModelProperty(value = "经度坐标",example = "经度坐标")
    private String longitude;

    @ApiModelProperty(value = "纬度坐标",example = "纬度坐标")
    private String latitude;


}