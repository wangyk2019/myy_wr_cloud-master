package com.moyuaninfo.cloud.VO;

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
@ApiModel(value = "MessageListOut",description = "")
public class MessageListOut {
    @ApiModelProperty(name = "warnList",value = "险情预览",required = true,example = "0")
    private List<WarnMsg> msginfo;

    @ApiModelProperty(name = "builddata",value = "险情预览",required = true,example = "0")
    private List<BuildData> builddata;

}