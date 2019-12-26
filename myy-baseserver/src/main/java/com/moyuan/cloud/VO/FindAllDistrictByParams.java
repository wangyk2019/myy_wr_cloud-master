package com.moyuan.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@ApiModel(value = "FindAllDistrictByParams", description = "组织查询参数")
public class FindAllDistrictByParams implements Serializable {

//  @ApiModelProperty(value = "id",example = "1")
//  private long id;

  @ApiModelProperty(value = "组织名字",example = "组织名字")
  private String name;

}
