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
@ApiModel(value = "MyyMonitordayIn", description = "监控时间天配置表")
public class MyyMonitordayIn implements Serializable {

  @ApiModelProperty(value = "摄像头险情id",example = "1")
  private long camwarnid;

  @ApiModelProperty(value = "天",example = "[1,2]")
  private ArrayList<Integer> days;



}
