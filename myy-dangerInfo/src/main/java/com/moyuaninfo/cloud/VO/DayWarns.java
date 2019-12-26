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
public class DayWarns {
    @ApiModelProperty(name = "day",value = "天",dataType = "int",required = true,example = "12")
    private int day;

    @ApiModelProperty(name = "warnTypes",value = "各险情数",dataType = "String",required = true,example ="10.0%")
    private List<WarnType> warnTypes;
}