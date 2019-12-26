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
@ApiModel(value = "MessageHistoryReferIn",description = "")
public class MessageHistoryReferIn {
    @ApiModelProperty(name = "orgId",value = "组织id",dataType = "int",required = true,example = "1")
    private int orgId;
    @ApiModelProperty(name = "startTime",value = "开始时间",dataType = "String",required = true,example = "2019-06-18")
    private String startTime;
    @ApiModelProperty(name = "endTime",value = "结束时间",dataType = "String",required = true,example = "2019-06-20")
    private String endTime;

    @ApiModelProperty(name = "camid",value = "摄像头id",dataType = "String",required = true,example = "")
    private long camid;

    @ApiModelProperty(name = "camid",value = "状态",dataType = "String",required = true,example = "")
    private long state;

    @ApiModelProperty(name = "eventType",value = "类型",dataType = "String",required = true,example = "可疑火点")
    private String eventType;
    @ApiModelProperty(name = "page",value = "页",dataType = "int",required = true,example = "11")
    private int page;

}