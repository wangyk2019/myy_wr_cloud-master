package com.moyuaninfo.cloud.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Myy_Warnpushcfg")
@Data
@ApiModel(value = "MyyWarnpushcfg", description = "人员推送信息配置表")
public class MyyWarnpushcfg extends BaseEntity implements Serializable {

  @ApiModelProperty(value = "区域三级id",example = "1")
  @Column(name = "areabottomid")
  private long areabottomid;

  @ApiModelProperty(value = "摄像机id",example = "1")
  @Column(name = "cameraid")
  private long cameraid;

  @ApiModelProperty(value = "用户id",example = "1")
  @Column(name = "userid")
  private long userid;

  @ApiModelProperty(value = "推送类型",example = "phone,msg,app")
  @Column(name = "pushtype")
  private String pushtype;

  @ApiModelProperty(value = "险情类型id",example = "1")
  @Column(name = "warntypeid")
  private long warntypeid;

}
