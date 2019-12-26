package com.moyuan.cloud.VO;

import com.moyuan.cloud.pojo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@ApiModel(value = "AddMyyArea", description = "添加区域")
@Data
public class AddMyyArea implements Serializable {

  @ApiModelProperty(value = "id",example = "1")
  private long id;

  @ApiModelProperty(value = "名称",example = "名称A")
  private String name;

  @ApiModelProperty(value = "区域编码",example = "摄像头A")
  private String area_code;

  @ApiModelProperty(value = "所属区域",example = "所属区域A")
  private String belongs;

  @ApiModelProperty(value = "级别",example = "3")
  private long level;

  @ApiModelProperty(value = "长度",example = "长度")
  private String length;

  @ApiModelProperty(value = "宽度",example = "宽度")
  private String width;

  @ApiModelProperty(value = "起点位置",example = "起点位置")
  private String origin;

  @ApiModelProperty(value = "终点位置",example = "终点位置")
  private String destination;

  @ApiModelProperty(value = "常水位",example = "常水位")
  private String avg_waterlever;

  @ApiModelProperty(value = "警戒水位",example = "警戒水位")
  private String warning_line;

  @ApiModelProperty(value = "污水排放口数",example = "33")
  private long sewage_outlets;

  @ApiModelProperty(value = "介绍",example = "介绍")
  private String introduction;

  @ApiModelProperty(value = "监督电话",example = "监督电话")
  private String supervise_call;

  @ApiModelProperty(value = "经度坐标起点",example = "经度坐标起点")
  private String longitude_b;

  @ApiModelProperty(value = "经度坐标终点",example = "经度坐标终点")
  private String longitude_e;

  @ApiModelProperty(value = "纬度坐标起点",example = "纬度坐标起点")
  private String latitude_b;

  @ApiModelProperty(value = "纬度坐标终点",example = "纬度坐标终点")
  private String latitude_e;

  @ApiModelProperty(value = "所属区ID",example = "所属区ID")
  private long district_id;

  @Override
  public String toString() {
    return "MyyArea{" +
            "name='" + name + '\'' +
            ", area_code='" + area_code + '\'' +
            ", belongs='" + belongs + '\'' +
            ", level=" + level +
            ", length='" + length + '\'' +
            ", width='" + width + '\'' +
            ", origin='" + origin + '\'' +
            ", destination='" + destination + '\'' +
            ", avg_waterlever='" + avg_waterlever + '\'' +
            ", warning_line='" + warning_line + '\'' +
            ", sewage_outlets=" + sewage_outlets +
            ", introduction='" + introduction + '\'' +
            ", supervise_call='" + supervise_call + '\'' +
            ", longitude_b='" + longitude_b + '\'' +
            ", longitude_e='" + longitude_e + '\'' +
            ", latitude_b='" + latitude_b + '\'' +
            ", latitude_e='" + latitude_e + '\'' +
            ", district_id=" + district_id +
            ", id=" + id +
            '}';
  }
}
