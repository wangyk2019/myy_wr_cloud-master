package com.moyuaninfo.liveplay.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "CameraVO",description = "摄像头简明对象")
public class CameraVO {

	@ApiModelProperty(name = "districtid",value = "组织ID",dataType = "Long",required = false,example = "101101")
	private Long districtid;
	@ApiModelProperty(name = "id",value = "摄像头ID",dataType = "Long",required = false,example = "101101")
	private Long id;
	@ApiModelProperty(name = "areaid",value = "区域ID",dataType = "Long",required = false,example = "101101")
	private Long areaid;
	@ApiModelProperty(name = "location",value = "地址",dataType = "String",required = false,example = "杭州市下沙海达南路")
	private String location;
	@ApiModelProperty(name = "streamname",value = "串流名称",dataType = "String",required = false,example = "camera01")
	private String streamname;
	@ApiModelProperty(name = "username",value = "摄像头登录名字",dataType = "String",required = false,example = "admin")
	private String username;
	@ApiModelProperty(name = "password",value = "摄像头登录密码",dataType = "String",required = false,example = "admin123")
	private String password;
	@ApiModelProperty(name = "rtspport",value = "摄像头rtsp端口",dataType = "String",required = false,example = "8000")
	private String rtspport;
	@ApiModelProperty(name = "camIp",value = "摄像头IP",dataType = "String",required = false,example = "10.110.11.11")
	private String camIp;
	@ApiModelProperty(name = "channel",value = "摄像头视频通道",dataType = "String",required = false,example = "channel1")
	private String channel;
	@ApiModelProperty(name = "link",value = "摄像头视频通道",dataType = "String",required = false,example = "rtsp://test1:my123456@192.168.0.222/Streaming/Channels/1/sub/10")
	private String link;

	@ApiModelProperty(name = "name",value = "摄像头名称",dataType = "String",required = false,example = "A")
	private String name;

	@ApiModelProperty(name = "userid",value = "用户ID",dataType = "long",required = false,example = "1011")
	private String userid;
}
