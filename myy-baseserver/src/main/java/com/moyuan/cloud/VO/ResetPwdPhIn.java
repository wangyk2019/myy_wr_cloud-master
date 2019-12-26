package com.moyuan.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ResetPwdPhIn",description = "电话号码和验证码合成对象")
public class ResetPwdPhIn {
	@ApiModelProperty(name = "userid",value = "id",dataType = "Long",required = true,example = "1")
	private long userid;
	@ApiModelProperty(name = "orgId",value = "orgId",dataType = "Long",required = true,example = "1")
	private long orgId;
	@ApiModelProperty(name = "oldph",value = "电话",dataType = "String",required = true,example = "123")
	private String oldph;
	@ApiModelProperty(name = "newph",value = "电话",dataType = "String",required = true,example = "123")
	private String newph;
	@ApiModelProperty(name = "newpw",value = "密码",dataType = "String",required = true,example = "111")
	private String newpw;
	@ApiModelProperty(name = "code",value = "验证码",dataType = "String",required = true,example = "111")
	private String code;

}
