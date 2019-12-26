package com.moyuaninfo.suggest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "MyyUser", description = "用户信息")
@Data
public class MyyUser implements Serializable {

    private static final long serialVersionUID = -908681415200061905L;

    @ApiModelProperty(value = "用户名字",example = "用户名字")
    private String username;

    @ApiModelProperty(value = "身份ID",example = "身份ID")
    private String id_number;

    @ApiModelProperty(value = "手机号码",example = "手机号码")
    private String phonenumber;

    @ApiModelProperty(value = "登录密码",example = "登录密码")
    private String password;

    @ApiModelProperty(value = "微信ID",example = "微信ID")
    private String wxid;

    @ApiModelProperty(value = "激活状态，(0:未激活，1:激活)",example = "0")
    private String valid;

    @ApiModelProperty(value = "组织ID",example = "1")
    private long district_id;

    @ApiModelProperty(value = "所属区域",example = "1")
    private long area_id;

    @ApiModelProperty(value = "职务信息",example = "1")
    private long position_id;

    @ApiModelProperty(value = "用户角色(admin：管理员，user：普通用户，people：群众)", example = "admin,user")
    private String role;

    @ApiModelProperty(value = "有效期",example = "2019-10-29")
    private java.sql.Date validity;

    @ApiModelProperty(value = "头像图片",example = "头像图片")
    private String avatar_imgurl;

    @ApiModelProperty(value = "河道负责人",example = "0否，1是")
    private String arearole;

    @ApiModelProperty(value = "直播权限",example = "0否，1是")
    private String livepower;


}
