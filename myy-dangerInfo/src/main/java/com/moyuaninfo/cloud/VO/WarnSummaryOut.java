package com.moyuaninfo.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author big boy
 * @description:
 * @date 2019-03-30 18:41
 */
@Setter
@Getter
@ApiModel(value = "WarnSummaryOut",description = "信息总览返回信息")
public class WarnSummaryOut {
    @ApiModelProperty(name = "noticeMsgLIst",value = "公告信息列表",required = true)
    private List<NoticeMsg> noticeMsgLIst;
    @ApiModelProperty(name = "onlineCam",value = "在线摄像头",dataType = "Long",required = true,example = "899")
    private Long onlineCam;
    @ApiModelProperty(name = "abnormalCam",value = "故障摄像头",dataType = "Long",required = true,example = "199")
    private Long abnormalCam;
    @ApiModelProperty(name = "warnList",value = "险情预览",required = true,example = "0")
    private List<WarnMsg> warnList;
}