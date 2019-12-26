package com.moyuan.signin.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@ApiModel(value = "FindAllUserByParams", description = "人员查询参数")
public class FindAllUserByParams implements Serializable {

    @ApiModelProperty(value = "id",example = "1")
    private long id;

    @ApiModelProperty(value = "手机号码",example = "手机号码")
    private String phonenumber;

    @ApiModelProperty(value = "用户名字",example = "用户名字")
    private String username;

    @ApiModelProperty(value = "组织ID",example = "1")
    private long district_id;

    @ApiModelProperty(value = "区域ID",example = "1")
    private long areaid;

    @ApiModelProperty(value = "所属区域",example = "[1]")
    private ArrayList<Long> area_id;

    @ApiModelProperty(value = "职务信息",example = "1")
    private long position_id;

    public FindAllUserByParams(String username, long areaid) {
        this.username = username;
        this.areaid = areaid;
    }
}