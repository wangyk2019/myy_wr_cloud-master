package com.moyuaninfo.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "MyyCamwarnconfigIn", description = "摄像头险情配置表")
public class MyyCamwarnconfigIn implements Serializable {

  @ApiModelProperty(value = "摄像头id",example = "1",required = true)
  private long cameraid;

  @ApiModelProperty(value = "险情类型",example = "[watercolorChange]",required = true)
  private List<String> warntypes;


}
