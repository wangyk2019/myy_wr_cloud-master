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
@ApiModel(value = "WarnPushcfgSetOut",description = "推送设置信息")
public class WarnPushcfgSetOut {
    @ApiModelProperty(name = "id",value = "id",dataType = "long",required = true,example = "1")
    private long id;
    @ApiModelProperty(name = "addr",value = "位置",dataType = "String",required = true,example = "1号楼实验室101室")
    private String addr;
    @ApiModelProperty(name = "username",value = "人名",dataType = "String",required = true,example = "s")
    private String username;

    @ApiModelProperty(name = "phonewarn",value = "电话推送",dataType = "List",required = true,example = "s")
    private List<String> phonewarn;
    @ApiModelProperty(name = "msgwarn",value = "短信推送",dataType = "List",required = true,example = "s")
    private List<String> msgwarn;
}