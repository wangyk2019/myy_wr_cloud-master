package com.moyuan.cloud.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Myy_Warnpushcfg")
@Data
@ApiModel(value = "MyyWarnpushcfg", description = "人员推送信息配置表")
public class MyyWarnpushcfg extends BaseEntity implements Serializable {

//  @ApiModelProperty(value = "河道id",example = "1")
//  private long areaid;

  @ApiModelProperty(value = "区域三级用户表id",example = "1")
  private long areauserid;

  @ApiModelProperty(value = "摄像机id",example = "1")
  private long cameraid;

//  @ApiModelProperty(value = "用户id",example = "1")
//  private long userid;

  @ApiModelProperty(value = "推送类型",example = "phone,msg,app")
  private String pushtype;

  @ApiModelProperty(value = "险情类型",example = "水质突变")
  private String warntype;

//  @ApiModelProperty(value = "超出警戒水位",example = "msg,app")
//  private String waterline_high;
//
//  @ApiModelProperty(value = "水质颜色突变",example = "msg,app")
//  private String watercolor_change;
//
//  @ApiModelProperty(value = "钓鱼行为",example = "msg,app")
//  private String fishing_behavior;
//
//  @ApiModelProperty(value = "捕捞/游泳/落水等异常行为",example = "msg,app")
//  private String man_overboard;
//
//  @ApiModelProperty(value = "滞留禁行区域",example = "msg,app")
//  private String area_restricted;
//
//  @ApiModelProperty(value = "摄像机遮挡/损毁",example = "msg,app")
//  private String view_abnormal;


}
