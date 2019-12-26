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
@ApiModel(value = "DangersOut",description = "险情统计信息")
public class DangersOut {
    @ApiModelProperty(name = "uncheck",value = "未查看数",dataType = "int",example = "12")
    private int uncheck;

//    @ApiModelProperty(name = "warnTypes",value = "各险情数",dataType = "String",required = true,example ="10.0%")
//    private List<WarnMsg> warnMsgs;

    @ApiModelProperty(name = "warnTypes",value = "各险情数",dataType = "String",example ="")
    private Map<String, Object> warnMsgs;

}