package com.moyuan.cloud.VO;

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
@ApiModel(value = "AreabottomidAndUserid",description = "")
public class AreabottomidAndUserid {
    @ApiModelProperty(name = "roomId",value = "实验室id",dataType = "Long",example = "1")
    private long areabottomId;

    @ApiModelProperty(name = "userId",value = "用户id",dataType = "Long",example = "1")
    private long userId;
}