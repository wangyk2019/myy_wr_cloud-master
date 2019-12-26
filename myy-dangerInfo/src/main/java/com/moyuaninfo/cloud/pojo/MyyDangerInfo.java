package com.moyuaninfo.cloud.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "myy_dangerinfo")
@Data
@ApiModel(value = "MyyDangerInfo", description = "摄像头")
public class MyyDangerInfo extends BaseEntity implements Serializable {

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Column(name = "areaid")
	@ApiModelProperty(value = "区域id",example = "123")
	private long areaid;

	@Column(name = "areabottomid")
	@ApiModelProperty(value = "岸id",example = "123")
	private long areabottomid;

	@Column(name = "cameraid")
	@ApiModelProperty(value = "摄像头id",example = "123")
	private long cameraid;

	@ApiModelProperty(value = "检测结果",example = "1")
	@Column(name = "result")
	private String result;

	@ApiModelProperty(value = "名称",example = "可疑火点")
	@Column(name = "name")
	private String name;

	@ApiModelProperty(value = "发生时间",example = "2019-06-15 18:20:30")
	@Column(name = "time")
	private String time;

	@ApiModelProperty(value = "是否上传深度状态",example = "0：否，1：是")
	@Column(name = "status")
	private String status;

	@ApiModelProperty(value = "是否查看状态",example = "0：否，1：是")
	@Column(name = "ischeck")
	private int ischeck;

	@ApiModelProperty(value = "图片",example = "/pic")
	@Column(name = "picpath")
	private String picpath;

	@ApiModelProperty(value = "视频",example = "//file")
	@Column(name = "filepath")
	private String filepath;

	@ApiModelProperty(name = "districtid",value = "组织id",dataType = "long",required = false,example = "10.110.11.11")
	@Column(name = "districtid",columnDefinition = "varchar(100) comment '组织id'")
	private long districtid;

	@ApiModelProperty(name = "warntype",value = "预警类型",dataType = "String",required = false,example = "firedetect")
	@Column(name = "warntype",columnDefinition = "varchar(50) comment '预警类型'")
	private String warntype;

	@ApiModelProperty(name = "sourcepath",value = "预警的源文件路径",dataType = "String",required = false,example = "/fire/001.jpg")
	@Column(name = "sourcepath",columnDefinition = "varchar(255) comment '预警的源文件路径'")
	private String sourcepath;

	@ApiModelProperty(value = "摄像头ip",example = "1")
	@Column(name = "ip")
	private String ip;

}
