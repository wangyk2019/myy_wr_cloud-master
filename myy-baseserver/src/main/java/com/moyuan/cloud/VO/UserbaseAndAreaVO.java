package com.moyuan.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UserbaseAndLabVO",description = "简要的用户信息")
public class UserbaseAndAreaVO {
	@ApiModelProperty(name = "cellphone",value = "手机号码",dataType = "String",required = true,example = "15988888888")
	private String cellphone;
	@ApiModelProperty(name = "username",value = "用户名",dataType = "String",required = false,example = "小明")
	private String username;

	@ApiModelProperty(name = "areabottomid",value = "区域ID",dataType = "Long",required = false,example = "10")
	private long areabottomid;
	@ApiModelProperty(name = "areabottomName",value = "区域room",dataType = "Long",required = false,example = "112室")
	private String areabottomName;

	@ApiModelProperty(name = "orgId",value = "组织ID",dataType = "long",required = false,example = "10")
	private long orgId;

	@ApiModelProperty(name = "admin",value = "是否管理员",dataType = "boolean",required = true,example = "false")
	private boolean admin;

}
