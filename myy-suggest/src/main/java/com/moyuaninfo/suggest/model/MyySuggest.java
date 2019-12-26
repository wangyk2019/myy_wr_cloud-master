package com.moyuaninfo.suggest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@ApiModel(value = "MyySuggest", description = "咨询建议")
@Data
public class MyySuggest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "处理状态",example = "0：未处理，1：已处理")
    private String status;

    @ApiModelProperty(value = "问题类型",example = "1:问题咨询，2:巡查问题")
    private Integer suggestType;

    @ApiModelProperty(value = "问题地点",example = "金沙湖")
    private String site;

    @ApiModelProperty(value = "所属区域河道id")
    private Integer areaId;

    @ApiModelProperty(value = "组织ID")
    private Integer districtId;

    @ApiModelProperty(value = "申报人")
    private String createUserName;

    @ApiModelProperty(value = "创建人")
    private Long createUser;

    @ApiModelProperty(value = "处理人姓名")
    private String updateUserName;

    @ApiModelProperty(value = "处理人联系方式")
    private String updateUserPhonenumber;

    @ApiModelProperty(value = "处理图片路径")
    private String solvePhotoPath;

    @ApiModelProperty(value = "更新人")
    private Long updateUser;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @ApiModelProperty(value = "备注")
    private String comment;

    @ApiModelProperty(value = "0：无效，1：有效")
    private String state;

    @ApiModelProperty(value = "咨询建议内容")
    private String content;

    @ApiModelProperty(value = "咨询建议拍照图片路径，多张中间用\";\"隔开")
    private String photoPath;

    @ApiModelProperty(value = "处理结果")
    private String result;


}