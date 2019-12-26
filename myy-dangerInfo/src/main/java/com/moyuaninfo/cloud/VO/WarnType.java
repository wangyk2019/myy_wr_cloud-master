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
@ApiModel(value = "WarnType",description = "险情统计信息")
public class WarnType {

    @ApiModelProperty(name = "num",value = "数量",dataType = "Long",example = "12")
    private long num;

    @ApiModelProperty(name = "warnname",value = "人行移动",dataType = "String",example = "人行移动")
    private String warnname;

    @ApiModelProperty(name = "warntypeid",value = "险情类型id",dataType = "Long",example = "1")
    private long warntypeid;

    @ApiModelProperty(name = "warnen",value = "险情英文名称",dataType = "String",example = "1")
    private String warnen;

    @ApiModelProperty(name = "sort",value = "排序",dataType = "int",example = "1")
    private int sort;

    @ApiModelProperty(name = "active",value = "是否激活",dataType = "int",example = "1")
    private int active;

    @ApiModelProperty(name = "warncorn",value = "险情类型图片",dataType = "String",example = "1")
    private String warncorn;

    @ApiModelProperty(name = "time",value = "图片信息",dataType = "String",example ="2019-06-25 12:00:00")
    private String time;
}