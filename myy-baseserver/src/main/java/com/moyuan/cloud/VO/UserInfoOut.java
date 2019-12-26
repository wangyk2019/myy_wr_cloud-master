package com.moyuan.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author big boy
 * @description:
 * @date 2019-03-30 18:41
 */
@Setter
@Getter
@ApiModel(value = "UserInfoOut",description = "")
public class UserInfoOut {

    @ApiModelProperty(name = "username",value = "用户名",dataType = "String",required = true,example = "周易")
    private String username;

    @ApiModelProperty(name = "cellphone",value = "手机号码",dataType = "String",required = true,example = "15168331797")
    private String cellphone;

    @ApiModelProperty(name = "password",value = "密码",dataType = "String",required = true,example = "123456")
    private String password;

    @ApiModelProperty(name = "colleagerole",value = "角色",dataType = "String",required = true,example = "教师")
    private String role;

    @ApiModelProperty(name = "ispassword",value = "是否有密码",dataType = "Boolean",required = true,example = "true")
    private boolean ispassword;

    @ApiModelProperty(name = "orgName",value = "组织id后者学校Id",dataType = "String",required = true,example = "组织")
    private String orgName;

    @ApiModelProperty(name = "department",value = "用户部门",dataType = "String",required = true,example = "1111")
    private String department;
    @ApiModelProperty(name = "certificates",value = "用户证件",dataType = "String",required = true,example = "1111")
    private String certificates;
    @ApiModelProperty(name = "certificatesNO",value = "用户证件号",dataType = "String",required = true,example = "1111")
    private String certificatesNO;
}