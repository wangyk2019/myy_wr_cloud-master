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
@ApiModel(value = "NoticeMsg",description = "公告信息")
public class NoticeMsg {
//    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    @ApiModelProperty(name = "msgId",value = "信息id",dataType = "int",required = true,example = "1")
    private long msgId;
    @ApiModelProperty(name = "msgType",value = "消息类型：0公告、1险情",dataType = "String",required = true,example = "1")
    private String msgType;
    @ApiModelProperty(name = "msgContent",value = "消息内容",dataType = "String",required = true,example = "关于系统维护公告信息通知")
    private String msgContent;

//    @ApiModelProperty(name = "time",value = "发出时间",dataType = "Date",example = "2019-06-12 12:00:00")
//    @JsonFormat(pattern = DATE_FORMAT)
//    private Date time;
}