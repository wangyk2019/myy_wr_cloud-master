package com.moyuaninfo.liveplay.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "myy_area")
@ApiModel(value = "area", description = "区域对象")
@Data
public class Area extends BaseEntity implements Serializable {
    @Column(name = "name", columnDefinition = "varchar(50)  COMMENT '名称'")
    @ApiModelProperty(name = "name", value = "名称", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String name;//
    @Column(name = "area_code", columnDefinition = "varchar(50)  COMMENT '区域编码'")
    @ApiModelProperty(name = "area_code", value = "区域编码", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String area_code;//
    @Column(name = "belongs", columnDefinition = "varchar(100)  COMMENT '所属区域'")
    @ApiModelProperty(name = "belongs", value = "所属区域", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String belongs;//
    @Column(name = "level", columnDefinition = "int(2) COMMENT '级别'")
    @ApiModelProperty(name = "level", value = "级别", dataType = "int", required = false, example = "XFSXDKLD0211")
    private int level;//
    @Column(name = "length", columnDefinition = "varchar(20)  COMMENT '长度'")
    @ApiModelProperty(name = "length", value = "长度", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String length;//
    @Column(name = "width", columnDefinition = "varchar(20)  COMMENT '宽度'")
    @ApiModelProperty(name = "width", value = "宽度", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String width;//
    @Column(name = "origin", columnDefinition = "varchar(100)  COMMENT '起点位置'")
    @ApiModelProperty(name = "origin", value = "起点位置", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String origin;//
    @Column(name = "destination", columnDefinition = "varchar(100)  COMMENT '终点位置'")
    @ApiModelProperty(name = "destination", value = "终点位置", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String destination;//
    @Column(name = "avg_waterlever", columnDefinition = "varchar(10)  COMMENT '常水位'")
    @ApiModelProperty(name = "avg_waterlever", value = "常水位", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String avg_waterlever;//
    @Column(name = "warning_line", columnDefinition = "varchar(10)  COMMENT '警戒水位'")
    @ApiModelProperty(name = "warning_line", value = "警戒水位", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String warning_line;//
    @Column(name = "sewage_outlets", columnDefinition = "int(11) COMMENT '污水排放口数'")
    @ApiModelProperty(name = "sewage_outlets", value = "污水排放口数", dataType = "int", required = false, example = "XFSXDKLD0211")
    private int sewage_outlets;//
    @Column(name = "introduction", columnDefinition = "varchar(255)  COMMENT '介绍'")
    @ApiModelProperty(name = "introduction", value = "介绍", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String introduction;//
    @Column(name = "supervise_call", columnDefinition = "varchar(20)  COMMENT '监督电话'")
    @ApiModelProperty(name = "supervise_call", value = "监督电话", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String supervise_call;//
    @Column(name = "longitude_b", columnDefinition = "varchar(50)  COMMENT '经度坐标起点'")
    @ApiModelProperty(name = "longitude_b", value = "经度坐标起点", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String longitude_b;//
    @Column(name = "longitude_e", columnDefinition = "varchar(50)  COMMENT '经度坐标终点'")
    @ApiModelProperty(name = "longitude_e", value = "经度坐标终点", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String longitude_e;//
    @Column(name = "latitude_b", columnDefinition = "varchar(50)  COMMENT '纬度坐标起点'")
    @ApiModelProperty(name = "latitude_b", value = "纬度坐标起点", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String latitude_b;//
    @Column(name = "latitude_e", columnDefinition = "varchar(50)  COMMENT '纬度坐标终点'")
    @ApiModelProperty(name = "latitude_e", value = "纬度坐标终点", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String latitude_e;//
    @Column(name = "districtid", columnDefinition = "int(20) COMMENT '所属区ID'")
    @ApiModelProperty(name = "districtid", value = "所属区ID", dataType = "long", required = false, example = "XFSXDKLD0211")
    private long districtid;//

    public Area(String name, String area_code, String belongs, int level, String length, String width,
                String origin, String destination, String avg_waterlever, String warning_line, int sewage_outlets,
                String introduction, String supervise_call, String longitude_b, String longitude_e,
                String latitude_b, String latitude_e, long districtid) {
        this.name = name;
        this.area_code = area_code;
        this.belongs = belongs;
        this.level = level;
        this.length = length;
        this.width = width;
        this.origin = origin;
        this.destination = destination;
        this.avg_waterlever = avg_waterlever;
        this.warning_line = warning_line;
        this.sewage_outlets = sewage_outlets;
        this.introduction = introduction;
        this.supervise_call = supervise_call;
        this.longitude_b = longitude_b;
        this.longitude_e = longitude_e;
        this.latitude_b = latitude_b;
        this.latitude_e = latitude_e;
        this.districtid = districtid;
    }

    public Area() {

    }

    @Override
    public String toString() {
        return "Area{" +
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
                ", districtid=" + districtid +
                '}';
    }
}
