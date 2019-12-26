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
@ApiModel(value = "ForgotPIn",description = "")
public class ForgotPIn {
    @ApiModelProperty(name = "cellphone",value = "手机号码",dataType = "String",required = true,example = "15988888888")
    private String cellphone;
    @ApiModelProperty(name = "password",value = "登录密码",dataType = "String",required = true,example = "123456")
    private String password;
    @ApiModelProperty(name = "smscode",value = "短信验证码",dataType = "String",required = true,example = "1234")
    private String smscode;

}