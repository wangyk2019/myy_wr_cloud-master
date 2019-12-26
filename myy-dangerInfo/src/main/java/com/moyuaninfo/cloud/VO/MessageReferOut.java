package com.moyuaninfo.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @author big boy
 * @description:
 * @date 2019-03-30 18:41
 */
@Setter
@Getter
@ApiModel(value = "MessageReferOut",description = "")
public class MessageReferOut {
    @ApiModelProperty(name = "warnList",value = "险情预览",required = true,example = "0")
    private Map<String, Object> msginfo;

    @ApiModelProperty(name = "uncheck",value = "未查询数量",example = "0")
    private long uncheck;

}