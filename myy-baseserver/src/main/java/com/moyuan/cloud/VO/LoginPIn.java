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
@ApiModel(value = "LoginPIn",description = "用户密码登录")
public class LoginPIn {
    @ApiModelProperty(name = "cellphone",value = "手机号",dataType = "String",required = true,example = "151683317979")
    private String cellphone;
    @ApiModelProperty(name = "password",value = "密码",dataType = "String",required = true,example = "123456")
    private String password;
}