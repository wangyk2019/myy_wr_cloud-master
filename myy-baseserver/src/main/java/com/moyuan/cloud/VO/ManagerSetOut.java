package com.moyuan.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author big boy
 * @description:
 * @date 2019-03-30 18:41
 */
@Setter
@Getter
@ApiModel(value = "ManagerSetOut",description = "")
public class ManagerSetOut {

    @ApiModelProperty(name = "areabottomId",value = "区域3级Id",dataType = "long",required = true,example = "1")
    private long areabottomId;

    @ApiModelProperty(name = "addr",value = "地址",dataType = "String",required = true,example = "周易")
    private String addr;

    @ApiModelProperty(name = "userMsg",value = "用户",dataType = "String",example = "1")
    private List<UserMsg> userMsg;


}