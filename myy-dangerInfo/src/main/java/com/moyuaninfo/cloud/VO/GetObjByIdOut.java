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
@ApiModel(value = "GetObjByIdOut",description = "")
public class GetObjByIdOut {
    @ApiModelProperty(name = "title",value = "标题",dataType = "String",required = true,example = "****")
    private String title;

    @ApiModelProperty(name = "context",value = "内容",dataType = "String",required = true,example = "*****")
    private String context;
}