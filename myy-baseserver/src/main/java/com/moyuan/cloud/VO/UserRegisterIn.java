package com.moyuan.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

/**
 * @author big boy
 * @description:
 * @date 2019-03-30 18:41
 */
@Setter
@Getter
@ApiModel(value = "UserRegisterIn",description = "")
public class UserRegisterIn {
    @ApiModelProperty(name = "cellphone",value = "手机号码",dataType = "String",required = true,example = "15988888888")
    private String cellphone;
    @ApiModelProperty(name = "username",value = "用户名",dataType = "String",required = true,example = "小明")
    private String username;

    @Pattern(regexp = "/^(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/",message = "密码由6到10的字母和数字组成")
    @ApiModelProperty(name = "password",value = "登录密码",dataType = "String",required = true,example = "123456")
    private String password;
//    @ApiModelProperty(name = "code",value = "证件号码",dataType = "String",required = true,example = "31646485699645777")
//    private String code;

    @ApiModelProperty(name = "code",value = "验证码",dataType = "String",required = true,example = "3164")
    private String code;

    @ApiModelProperty(name = "type",value = "身份",dataType = "Long",required = true,example = "1")
    private long type;

    @ApiModelProperty(name = "valid",value = "状态",dataType = "String",required = false,example = "1")
    private String valid;

    @ApiModelProperty(name = "orgId",value = "工作单位",dataType = "int",required = true,example = "1")
    private int orgId;


}