package com.moyuaninfo.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@ApiModel(value = "FindAllCamWarnByParams", description = "查询参数")
public class FindAllCamWarnByParams implements Serializable {

  @ApiModelProperty(value = "组织ID",example = "1")
  private long orgid;

  @ApiModelProperty(value = "区域1ID",example = "1")
  private long oneid;

  @ApiModelProperty(value = "区域2ID",example = "1")
  private long twoid;

  @ApiModelProperty(value = "区域3ID",example = "1")
  private long threeid;

  @ApiModelProperty(value = "摄像头ID",example = "1")
  private long cameraid;

  @ApiModelProperty(value = "用户ID",example = "1")
  private long userid;
}
