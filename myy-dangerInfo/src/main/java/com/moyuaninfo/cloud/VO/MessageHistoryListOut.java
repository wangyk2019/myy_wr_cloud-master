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
@ApiModel(value = "MessageHistoryListOut",description = "")
public class MessageHistoryListOut {
    @ApiModelProperty(name = "warnMsgList",value = "险情消息列表",required = true)
    private List<WarnMsg> historyData;

    @ApiModelProperty(name = "builddata",value = "",required = true)
    private List<BuildData> builddata;

}