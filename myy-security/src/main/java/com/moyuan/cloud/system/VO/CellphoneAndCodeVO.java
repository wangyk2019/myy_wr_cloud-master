package com.moyuan.cloud.system.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "CellphoneAndCodeVO", description = "电话号码和验证码合成对象")
public class CellphoneAndCodeVO {
    @ApiModelProperty(name = "cellphone", value = "手机号码", dataType = "String", required = true, example = "15988888888")
    private String cellphone;
    @ApiModelProperty(name = "code", value = "短信验证码", dataType = "String", required = true, example = "1234")
    private String code;

    @ApiModelProperty(name = "ip", value = "ip地址", dataType = "String", required = false, example = "1.2.1.3")
    private String ip;
    @ApiModelProperty(name = "platform", value = "登录平台", dataType = "String", required = false, example = "Android，weixin")
    private String platform;
}
