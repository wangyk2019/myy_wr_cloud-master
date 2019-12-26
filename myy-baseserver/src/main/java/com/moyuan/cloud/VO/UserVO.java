package com.moyuan.cloud.VO;

import com.moyuan.cloud.pojo.MyyUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "UserVO",description = "用户信息")
public class UserVO implements Serializable{

	@ApiModelProperty(name = "user",value = "用户对象信息",dataType = "User",required = false,example = "User")
	private MyyUser user;
//	@ApiModelProperty(name = "leader",value = "身份对象信息",dataType = "Leader",required = false,example = "leader")
//	private Leader leader;
//	@ApiModelProperty(name = "teacher",value = "身份对象信息",dataType = "Teacher",required = false,example = "teacher")
//	private Teacher teacher;
//	@ApiModelProperty(name = "student",value = "身份对象信息",dataType = "Student",required = false,example = "student")
//	private Student student;
	
	@ApiModelProperty(name = "colleagerole",value = "用户身份",dataType = "String",required = false,example = "teacher")
	private String role;
}
