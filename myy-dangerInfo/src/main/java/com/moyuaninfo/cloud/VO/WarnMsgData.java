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
@ApiModel(value = "WarnMsgData",description = "险情信息")
public class WarnMsgData {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @ApiModelProperty(name = "id",value = "信息id",dataType = "int",required = true,example = "1")
    private int id;
    @ApiModelProperty(name = "title",value = "人行移动",dataType = "String",required = true,example = "1")
    private String title;
    @ApiModelProperty(name = "text",value = "消息内容",dataType = "String",required = true,example = "发现可以火点请尽快处理排查")
    private String text;
    @ApiModelProperty(name = "time",value = "消息类型时间",dataType = "Date",required = true,example = "2019.06.18 12:00:00")
//    @JsonFormat(pattern = DATE_FORMAT)
    private String time;

    @ApiModelProperty(name = "msgType",value = "",dataType = "String",required = true,example = "公告0险情1")
    private String msgType;
}