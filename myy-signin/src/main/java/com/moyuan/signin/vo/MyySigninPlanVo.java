package com.moyuan.signin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "MyySigninPlanVo", description = "巡检计划简明对象")
public class MyySigninPlanVo {
    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "用户ID", example = "1212")
    private String date;

    @ApiModelProperty(value = "区域ID", example = "1")
    private Long areaid;

    @ApiModelProperty(value = "a用户ID", example = "34343")
    private Long userida;

    @ApiModelProperty(value = "b用户ID", example = "34343")
    private Long useridb;

    @ApiModelProperty(value = "星期几", example = "星期一")
    private String week;

    @ApiModelProperty(value = "第几周", example = "5")
    private int weeknum;

    @ApiModelProperty(value = "a用户名", example = "张三")
    private String usernamea;

    @ApiModelProperty(value = "b用户名", example = "李四")
    private String usernameb;

    @ApiModelProperty(value = "a用户手机号", example = "13333333333")
    private String phonea;

    @ApiModelProperty(value = "b用户手机号", example = "13333333333")
    private String phoneb;

}
