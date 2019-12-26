package com.moyuan.signin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MyySigninPageVo", description = "巡检记录分页简明对象")
public class MyySigninPageVo {

    @ApiModelProperty(value = "搜索条件", example = "张三")
    private String param;

    @ApiModelProperty(value = "区域ID", example = "32342")
    private long areaid;

    @ApiModelProperty(value = "时长", example = "week")
    private String timelength;

    @ApiModelProperty(value = "页码，从1开始", example = "1")
    private int pagenum;

    @ApiModelProperty(value = "每页数据量", example = "10")
    private int pagesize;
}
