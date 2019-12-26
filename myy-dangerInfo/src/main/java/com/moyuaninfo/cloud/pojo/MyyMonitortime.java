package com.moyuaninfo.cloud.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "Myy_Monitortime")
@Data
@ApiModel(value = "MyyMonitortime", description = "监控时间天配置表")
public class MyyMonitortime extends BaseEntity implements Serializable {

  @ApiModelProperty(value = "监控天id",example = "1")
  private long dayid;

  @ApiModelProperty(value = "时间段",example = "1，2")
  private String time;

//  @ApiModelProperty(value = "开始时间",example = "1")
//  private String begintime;
//
//  @ApiModelProperty(value = "结束时间",example = "2")
//  private String endtime;

}
