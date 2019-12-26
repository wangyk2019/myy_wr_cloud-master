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
@ApiModel(value = "WarnDetailReferIn",description = "")
public class WarnDetailReferIn {
    @ApiModelProperty(name = "id",value = "dangerId",dataType = "int",required = true,example = "1")
    private int dangerId;

}