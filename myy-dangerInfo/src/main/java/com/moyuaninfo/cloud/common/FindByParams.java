package com.moyuaninfo.cloud.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "FindByParams",description = "查询参数")
public class FindByParams {
    @ApiModelProperty(name = "orgId",value = "组织id",dataType = "int",example = "1")
    private long orgId;

    @ApiModelProperty(name = "warnen",value = "类型英文",dataType = "String",example = "shape")
    private String warnen;

    @ApiModelProperty(name = "warnname",value = "类型中文",dataType = "String",example = "人形移动")
    private String warnname;

    @ApiModelProperty(value = "页码，从1开始", example = "1")
    private int pagenum=1;

    @ApiModelProperty(value = "每页数据量", example = "10")
    private int pagesize=10;

}
