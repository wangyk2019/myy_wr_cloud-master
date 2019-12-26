package com.moyuan.cloud.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "Myy_Warntype")
public class MyyWarntype implements Serializable {

  private static final long serialVersionUID = 3000L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "warntypeid")
  @ApiModelProperty(value = "险情类型id",example = "1")
  private long warntypeid;

  @Column(name = "warnname")
  @ApiModelProperty(value = "险情名称",example = "人形移动")
  private String warnname;

  @Column(name = "warncorn")
  @ApiModelProperty(value = "激活险情图片",example = "1")
  private String warncorn;

  @Column(name = "warnuncorn")
  @ApiModelProperty(value = "未激活险情图片",example = "1")
  private String warnuncorn;

  @Column(name = "warnen")
  @ApiModelProperty(value = "险情英文名称",example = "move")
  private String warnen;


}
