package com.moyuan.cloud.VO;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ExcelTarget("20")
@ApiModel(value = "MyyAreaIn", description = "区域")
@Data
public class MyyAreaIn implements Serializable {

  @Excel(name = "河道名称", orderNum = "0", width=30)
  @NotBlank(message = "不能为空")
  private String name;

  @Excel(name = "河道编码", orderNum = "1", width=30)
  private String area_code;

  @Excel(name = "所在地点", orderNum = "2", width=30)
  private String belongs;

  @Excel(name = "河道长度", orderNum = "3", width=30)
  private String length;

  @Excel(name = "河道宽度", orderNum = "4", width=30)
  private String width;

  @Excel(name = "监督电话", orderNum = "5", width=30)
  private String supervise_call;

  @Excel(name = "河道常水位", orderNum = "6", width=30)
  private String avg_waterlever;

  @Excel(name = "警戒水位", orderNum = "7", width=30)
  private String warning_line;

  @Excel(name = "河道级别", orderNum = "8", width=30)
  private String level;

  @Excel(name = "起点位置", orderNum = "9", width=30)
  private String origin;

  @Excel(name = "终点位置", orderNum = "10", width=30)
  private String destination;

  @Excel(name = "排放口数量", orderNum = "11", width=30)
  private long sewage_outlets;

  @Excel(name = "河道介绍", orderNum = "12", width=30)
  private String introduction;


}
