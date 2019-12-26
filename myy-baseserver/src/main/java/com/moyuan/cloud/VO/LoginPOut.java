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
@ApiModel(value = "LoginPOut",description = "")
public class LoginPOut {

    @ApiModelProperty(name = "username",value = "用户名",dataType = "String",required = true,example = "周易")
    private String username;

    @ApiModelProperty(name = "cellphone",value = "手机号码",dataType = "String",required = true,example = "15168331797")
    private String cellphone;

    @ApiModelProperty(name = "colleagerole",value = "角色",dataType = "String",required = true,example = "教师")
    private String role;

    @ApiModelProperty(name = "admin",value = "角色",dataType = "String",required = true,example = "0")
    private String admin;

    @ApiModelProperty(name = "colleageId",value = "组织id后者学校Id",dataType = "int",required = true,example = "1")
    private long orgId;

    @ApiModelProperty(name = "orgName",value = "组织名称",dataType = "String",example = "1")
    private String orgName;

    @ApiModelProperty(name = "userId",value = "用户Id",dataType = "long",required = true,example = "1")
    private long userId;
}