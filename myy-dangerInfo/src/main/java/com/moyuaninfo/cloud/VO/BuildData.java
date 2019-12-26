package com.moyuaninfo.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wyk
 * @description:
 * @date 2019-03-30 18:41
 */
@Setter
@Getter
@ApiModel(value = "BuildData",description = "")
public class BuildData {

    @ApiModelProperty(name = "name",value = "楼",dataType = "String",required = true,example = "1")
    private String name;

}