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
@ApiModel(value = "WarnMsg",description = "险情信息")
public class WarnMsg {
    @ApiModelProperty(name = "id",value = "信息id",dataType = "int",required = true,example = "1")
    private long id;
    @ApiModelProperty(name = "eventType",value = "消息类型：1可疑火点、2视野移位，3视野遮挡，4异物滞留，5，人行移动",dataType = "String",required = true,example = "人行移动")
    private String eventType;
    @ApiModelProperty(name = "img",value = "图片信息",dataType = "String",required = true,example = "ssasssssssdZ")
    private String img;
//    @ApiModelProperty(name = "video",value = "视频信息",dataType = "String",required = true,example = "ssasssssssdZ")
//    private String video;
    @ApiModelProperty(name = "addr",value = "位置",dataType = "String",required = true,example = "1号楼实验室101室")
    private String addr;
    @ApiModelProperty(name = "ctime",value = "时间",dataType = "String",required = true,example = "2019-08-10 12:00:00")
    private String ctime;

    @ApiModelProperty(name = "camName",value = "摄像头",dataType = "String",required = true,example = "1号楼实验室101室")
    private String camName;

    @ApiModelProperty(name = "state",value = "查看状态（1：已看，0：未看）",dataType = "String",example = "0")
    private String state;
}