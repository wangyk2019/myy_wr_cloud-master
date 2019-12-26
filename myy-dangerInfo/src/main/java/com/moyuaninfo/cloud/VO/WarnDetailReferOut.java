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
@ApiModel(value = "WarnDetailReferOut",description = "")
public class WarnDetailReferOut {
    private static final String DATE_FORMAT = "yyyy.MM.dd HH:mm:ss";

    @ApiModelProperty(name = "dType",value = "消息类型：1可疑火点、2视野移位，3视野遮挡，4异物滞留，5，人行移动",dataType = "String",required = true,example = "1")
    private String dtype;

    @ApiModelProperty(name = "dTime",value = "消息类型时间",dataType = "Date",required = true,example = "2019.06.18 12:00:00")
//    @JsonFormat(pattern = DATE_FORMAT)
    private String dtime;

    @ApiModelProperty(name = "vedioAddr",value = "险情地址",dataType = "String",required = true,example = "")
    private String vedioAddr;

    @ApiModelProperty(name = "vedioImg",value = "险情地址",dataType = "String",required = true,example = "")
    private String vedioImg;

    @ApiModelProperty(name = "dAddr",value = "地址",dataType = "String",required = true,example = "")
    private String daddr;

    @ApiModelProperty(name = "dCam",value = "摄像头名称",dataType = "String",required = true,example = "A")
    private String dcam;

    @ApiModelProperty(name = "name",value = "负责人",dataType = "String",required = false,example = "天老师")
    private String name;

    @ApiModelProperty(name = "mpMobile",value = "电话",dataType = "String",required = false,example = "15168331797")
    private String mpMobile;

}