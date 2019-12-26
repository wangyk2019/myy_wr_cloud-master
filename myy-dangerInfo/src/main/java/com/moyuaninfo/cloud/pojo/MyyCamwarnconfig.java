package com.moyuaninfo.cloud.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Myy_Camwarnconfig")
@Data
@ApiModel(value = "MyyCamwarnconfig", description = "摄像头险情配置表")
public class MyyCamwarnconfig extends BaseEntity implements Serializable {

  @ApiModelProperty(value = "摄像头id",example = "1",required = true)
  @Column(name = "cameraid")
  private long cameraid;

  @ApiModelProperty(value = "险情类型",example = "1",required = true)
  @Column(name = "warntypeid")
  private long warntypeid;

  @ApiModelProperty(value = "险情开关0:关，1：开",example = "0",required = true)
  @Column(name = "onoff")
  private String onoff;


}
