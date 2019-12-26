package com.moyuan.cloud.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@ApiModel(value = "FindCameraByParams", description = "摄像头查询参数")
public class FindCameraByParams implements Serializable {

//  @ApiModelProperty(value = "摄像头类型型号",example = "123")
////  private String cameratype;

  @ApiModelProperty(value = "",example = "[1]")
  private ArrayList<Long> areabottom;

  @ApiModelProperty(value = "摄像机状态",example = "0")
  private String status;

  @ApiModelProperty(value = "访问摄像头的Ip",example = "192.168.1.1")
  private String ip;

  @ApiModelProperty(value = "链接",example = "")
  private String link;


}
