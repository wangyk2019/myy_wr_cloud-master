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
@ApiModel(value = "WarninfoOut",description = "")
public class WarninfoOut {

    @ApiModelProperty(value = "险情类型id",example = "1")
    private long warntypeid;

    @ApiModelProperty(value = "排序",example = "1")
    private int sort;

    @ApiModelProperty(value = "激活状态",example = "1")
    private int active;

    @ApiModelProperty(value = "险情名称",example = "人形移动")
    private String warnname;

    @ApiModelProperty(value = "险情图片",example = "1")
    private String warncorn;

    @ApiModelProperty(value = "险情英文名称",example = "move")
    private String warnen;

}