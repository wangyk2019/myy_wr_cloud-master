package com.moyuan.cloud.VO;

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
@ApiModel(value = "WarnPushcfgSetOut",description = "推送设置信息")
public class WarnPushcfgSetOut {
    @ApiModelProperty(name = "areauserid",value = "areauserid",dataType = "long",example = "1")
    private long areauserid;
    @ApiModelProperty(name = "warnpushid",value = "warnpushid",dataType = "long",example = "1")
    private long warnpushid;
    @ApiModelProperty(name = "addr",value = "位置",dataType = "String",example = "1号楼实验室101室")
    private String addr;
    @ApiModelProperty(name = "username",value = "人名",dataType = "String",example = "s")
    private String username;

    @ApiModelProperty(name = "phonewarn",value = "电话推送",dataType = "List",example = "s")
    private List<String> phonewarn;
    @ApiModelProperty(name = "msgwarn",value = "短信推送",dataType = "List",example = "s")
    private List<String> msgwarn;
}