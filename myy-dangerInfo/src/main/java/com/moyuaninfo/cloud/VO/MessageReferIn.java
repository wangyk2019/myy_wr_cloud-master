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
@ApiModel(value = "MessageReferIn",description = "")
public class MessageReferIn {
    @ApiModelProperty(name = "orgId",value = "组织id",dataType = "int",required = true,example = "1")
    private int orgId;

    @ApiModelProperty(name = "eventtpye",value = "人行移动",dataType = "String",example = "1")
    private String eventtpye;

    @ApiModelProperty(name = "areaone",value = "区域一级id",dataType = "long",example = "1")
    private long areaone;
    @ApiModelProperty(name = "areatwo",value = "区域2级id",dataType = "long",example = "2")
    private long areatwo;
    @ApiModelProperty(name = "areabottom",value = "区域3级id",dataType = "long",example = "3")
    private long areabottom;
    @ApiModelProperty(name = "cameraid",value = "摄像机id",dataType = "long",example = "4")
    private long cameraid;

    @ApiModelProperty(name = "begintime",value = "开始时间",dataType = "String",example = "2019-10-11")
    private String begintime;
    @ApiModelProperty(name = "endtime",value = "结束时间",dataType = "String",example = "2019-10-12")
    private String endtime;

    @ApiModelProperty(value = "页码，从1开始", example = "1")
    private int pagenum=1;
    @ApiModelProperty(value = "每页数据量", example = "10")
    private int pagesize=10;

}