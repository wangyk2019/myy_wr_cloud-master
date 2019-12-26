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
@ApiModel(value = "MonthWarns",description = "险情统计信息")
public class MonthWarns {
    @ApiModelProperty(name = "month",value = "月",dataType = "int",required = true,example = "12")
    private int month;

    @ApiModelProperty(name = "warnTypes",value = "各险情数",dataType = "String",required = true,example ="10.0%")
    private List<WarnType> warnTypes;

    @ApiModelProperty(name = "monthGrowthrate",value = "增长比",dataType = "String",required = true,example ="10.0%")
    private String monthGrowthrate;
}