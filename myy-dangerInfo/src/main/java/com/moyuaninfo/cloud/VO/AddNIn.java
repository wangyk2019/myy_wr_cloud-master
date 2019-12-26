package com.moyuaninfo.cloud.VO;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@ApiModel(value = "AddNIn", description = "监控时间天配置表")
public class AddNIn implements Serializable {

  @ApiModelProperty(value = "监控天id",example = "1")
  private long dayid;

  @ApiModelProperty(value = "天",example = "[1,2]")
  private ArrayList<Integer> days;

}
