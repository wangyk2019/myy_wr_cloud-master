package com.moyuan.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ResetPhIn",description = "电话号码和验证码合成对象")
public class ResetPhIn {
	@ApiModelProperty(name = "userid",value = "id",dataType = "Long",required = true,example = "1")
	private long userid;
	@ApiModelProperty(name = "oldph",value = "手机号码",dataType = "String",required = true,example = "15988888888")
	private String oldph;
	@ApiModelProperty(name = "newph",value = "手机号码",dataType = "String",required = true,example = "15988888881")
	private String newph;
	@ApiModelProperty(name = "code",value = "短信验证码",dataType = "String",required = true,example = "1234")
	private String code;
}
