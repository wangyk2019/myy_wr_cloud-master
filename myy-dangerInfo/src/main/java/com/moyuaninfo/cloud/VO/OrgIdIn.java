package com.moyuaninfo.cloud.VO;

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
@ApiModel(value = "OrgIdIn",description = "")
public class OrgIdIn {
    @ApiModelProperty(name = "orgId",value = "组织id",dataType = "long",required = true,example = "1")
    private long orgId;

    @ApiModelProperty(name = "eventType",value = "类型",dataType = "String",required = false,example = "可疑火点")
    private String eventType;

    @ApiModelProperty(name = "page",value = "页",dataType = "int",required = false,example = "1")
    private int page;

}