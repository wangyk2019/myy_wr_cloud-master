package com.moyuan.cloud.VO;

import com.moyuan.cloud.pojo.MyyUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "UserMsg",description = "用户信息")
public class UserMsg implements Serializable{

	@ApiModelProperty(name = "userid",value = "用户id",dataType = "String",example = "User")
	private long userid;

	@ApiModelProperty(name = "name",value = "用户名称",dataType = "String",example = "User")
	private String username;

	@ApiModelProperty(name = "phonenumber",value = "用户手机",dataType = "String",example = "13122111111")
	private String phonenumber;

	@ApiModelProperty(name = "admin",value = "是否管理员",dataType = "String",example = "1")
	private String admin;
}
