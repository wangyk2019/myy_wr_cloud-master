package com.moyuaninfo.cloud.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "DetectconfigVO",description = "服务配置简明对象")
public class DetectconfigVO {

	@ApiModelProperty(name = "camera",value = "摄像头ID",dataType = "Long",required = false,example = "101101")
	private Long camera;
	@ApiModelProperty(name = "ip",value = "摄像头IP",dataType = "String",required = false,example = "10.110.11.11")
	private String ip;

}
