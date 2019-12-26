package com.moyuaninfo.cloud.VO;


import com.moyuaninfo.cloud.pojo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@ApiModel(value = "MyyMonitortimeIn", description = "监控时间天配置表")
public class MyyMonitortimeIn implements Serializable {

  @ApiModelProperty(value = "监控天id",example = "1")
  private long dayid;

  @ApiModelProperty(value = "时间段",example = "[[1,2],[3,5]]")
  private ArrayList<ArrayList<String>> times;

//  @ApiModelProperty(value = "开始时间",example = "1")
//  private String begintime;
//
//  @ApiModelProperty(value = "结束时间",example = "2")
//  private String endtime;

}
