package com.moyuaninfo.cloud.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Myy_Monitorday")
@Data
@ApiModel(value = "MyyMonitorday", description = "监控时间天配置表")
public class MyyMonitorday extends BaseEntity implements Serializable {

  @ApiModelProperty(value = "摄像头险情id",example = "1")
  private long camwarnid;

  @ApiModelProperty(value = "天",example = "1")
  private String day;



}
