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
@ApiModel(value = "UserInfoIn",description = "")
public class UserInfoIn {
    @ApiModelProperty(name = "cellphone",value = "手机号码",dataType = "String",required = true,example = "15988888888")
    private String cellphone;

    @ApiModelProperty(name = "orgId",value = "高校id",dataType = "String",required = true,example = "1")
    private int orgId;
}