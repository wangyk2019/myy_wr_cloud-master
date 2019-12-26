package com.moyuan.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@ApiModel(value = "FindAllAreaByParams", description = "区域查询参数")
public class FindAllAreaByParams implements Serializable {

  @ApiModelProperty(value = "区域名字",example = "用户名字")
  private String name;

  @NotNull
  @Min(value = 1,message = "组织ID最小不能小于1")
  @ApiModelProperty(value = "组织ID",example = "11",required = true)
  private long districtid;

}
