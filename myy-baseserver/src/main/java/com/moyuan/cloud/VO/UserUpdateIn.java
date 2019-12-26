package com.moyuan.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@ApiModel(value = "UserUpdateIn",description = "用户对象")
@Getter
@Setter
public class UserUpdateIn {
	@ApiModelProperty(name = "orgId",value = "userId",dataType = "Long",required = true,example = "1")
	private long orgId;

	@ApiModelProperty(name = "userId",value = "userId",dataType = "Long",required = true,example = "1")
	private long userId;
	@ApiModelProperty(name = "username",value = "用户名字",dataType = "String",required = false,example = "李三")
	private String username;
	@ApiModelProperty(name = "cellphone",value = "手机号码",dataType = "String",required = false,example = "159888888888")
	private String cellphone;

	@ApiModelProperty(name = "roomId",value = "实验室ID",dataType = "Long",required = false,example = "10")
	private Long roomId;

	@ApiModelProperty(name = "admin",value = "是否管理员",dataType = "boolean",required = true,example = "false")
	private boolean admin;

}
