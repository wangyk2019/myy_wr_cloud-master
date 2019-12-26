package com.moyuan.cloud.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "Myy_orgwarntype")
public class MyyOrgwarntype implements Serializable {

  @Column(name = "orgid")
  @ApiModelProperty(value = "组织id",example = "1")
  private long orgid;

  @Column(name = "warntypeid")
  @ApiModelProperty(value = "险情类型id",example = "1")
  private long warntypeid;

  @Column(name = "sort")
  @ApiModelProperty(value = "排序",example = "1")
  private int sort;

  @Column(name = "active")
  @ApiModelProperty(value = "激活状态（0：未激活，1：激活）",example = "1")
  private int active;

  @Column(name = "belongs")
  @ApiModelProperty(value = "所属区块（1：首页展示，2：更多功能）",example = "1")
  private int belongs;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "orgwarntypeid")
  @ApiModelProperty(value = "组织险情类型id",example = "1")
  private long orgwarntypeid;

}
