package com.moyuan.cloud.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "myy_user")
@ApiModel(value = "MyyUser", description = "用户表")
@Data
public class MyyUser extends BaseEntity implements Serializable {
  private static final long serialVersionUID = 400L;

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
  private long districtid;

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

  @Override
  public String toString() {
    return "MyyUser{" +
            "username='" + username + '\'' +
            ", id_number='" + id_number + '\'' +
            ", phonenumber='" + phonenumber + '\'' +
            ", password='" + password + '\'' +
            ", wxid='" + wxid + '\'' +
            ", valid='" + valid + '\'' +
            ", district_id=" + districtid +
            ", area_id=" + area_id +
            ", position_id=" + position_id +
            ", role=" + role +
            ", validity=" + validity +
            ", avatar_imgurl='" + avatar_imgurl + '\'' +
            ", id=" + id +
            ", creattime=" + creattime +
            ", updatetime=" + updatetime +
            ", comment='" + comment + '\'' +
            ", state='" + state + '\'' +
            '}';
  }
}
