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
@ApiModel(value = "MessageListNoticeOut",description = "")
public class MessageListNoticeOut {
    @ApiModelProperty(name = "warnMsgDataList",value = "险情消息列表",required = true)
    private List<WarnMsgData> dangerList;
//    @ApiModelProperty(name = "sysMsgDataList",value = "公告信息列表",required = true)
//    private List<SysMsgData> sysList;

}