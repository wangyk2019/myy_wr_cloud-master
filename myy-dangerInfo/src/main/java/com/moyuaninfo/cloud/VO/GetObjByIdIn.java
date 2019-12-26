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
@ApiModel(value = "GetObjByIdIn",description = "")
public class GetObjByIdIn {
    @ApiModelProperty(name = "id",value = "id",dataType = "int",required = true,example = "1")
    private int id;

    @ApiModelProperty(name = "eventype",value = "类型",dataType = "String",required = true,example = "0")
    private String eventype;

}