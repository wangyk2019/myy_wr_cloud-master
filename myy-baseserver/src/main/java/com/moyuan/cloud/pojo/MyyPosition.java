package com.moyuan.cloud.pojo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "myy_position")
public class MyyPosition extends BaseEntity implements Serializable {

  @Column(name = "name")
  @ApiModelProperty(value = "姓名",example = "李")
  private String name;

  @Column(name = "contact")
  @ApiModelProperty(value = "联系方式",example = "05715465464")
  private String contact;

  @Column(name = "position")
  @ApiModelProperty(value = "职务",example = "**")
  private String position;

  @Column(name = "responsibility")
  @ApiModelProperty(value = "职责",example = "关于**")
  private String responsibility;

  @Column(name = "districtid")
  @ApiModelProperty(value = "组织id",example = "关于**")
  private long districtid;

  @Column(name = "areaid")
  @ApiModelProperty(value = "区域id",example = "关于**")
  private long areaid;

  @Column(name = "institutions")
  @ApiModelProperty(value = "单位机构",example = "**")
  private String institutions;

  @Column(name = "department")
  @ApiModelProperty(value = "部门",example = "**")
  private String department;

  @Column(name = "certificate")
  @ApiModelProperty(value = "证件类型",example = "关于**")
  private String certificate;

  @Column(name = "certNumber")
  @ApiModelProperty(value = "证件号码",example = "关于**")
  private String certNumber;

  @Column(name = "positiontype")
  @ApiModelProperty(value = "职务类型",example = "关于**")
  private String positiontype;


}
