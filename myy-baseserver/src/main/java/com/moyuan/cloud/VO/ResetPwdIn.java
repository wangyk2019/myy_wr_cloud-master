package com.moyuan.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ResetPhIn",description = "电话号码和验证码合成对象")
public class ResetPwdIn {
	@ApiModelProperty(name = "userid",value = "id",dataType = "String",required = true,example = "1")
	private long userid;
	@ApiModelProperty(name = "orgId",value = "orgId",dataType = "String",required = true,example = "1")
	private long orgId;
	@ApiModelProperty(name = "oldpw",value = "密码",dataType = "String",required = true,example = "123")
	private String oldpw;
	@ApiModelProperty(name = "newpw",value = "密码",dataType = "String",required = true,example = "111")
	private String newpw;

	@ApiModelProperty(name = "smscode",value = "验证码",dataType = "String",required = true,example = "111")
	private String smscode;

}
