package com.moyuaninfo.suggest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SuggestListDto
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/23 20:11
 * @Version 1.0
 **/
@ApiModel(value = "SuggestListDto", description = "咨询建议列表dto")
@Data
public class SuggestListDto implements Serializable {

    private static final long serialVersionUID = -3333066581031374354L;

    @ApiModelProperty(value = "组织id")
    private Integer districtId;

    @ApiModelProperty(value = "区域河道id")
    private Integer areaId;

    @ApiModelProperty(value = "处理状态",example = "0：今日，1：未处理，2：已处理")
    private Integer status;

    @ApiModelProperty(value = "起始页")
    private Integer page;

    @ApiModelProperty(value = "每页显示的条数")
    private Integer rows;

    @ApiModelProperty(value = "问题类型",example = "1：问题咨询，2：巡查问题")
    private Integer suggestType;

    @ApiModelProperty(value = "开始时间")
    private String beginTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "组织状态")
    private Boolean districtStatus;

    @ApiModelProperty(value = "搜索关键词")
    private String searchName;

    @ApiModelProperty(value = "当前登录用户id")
    private Long userId;

}
