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
@ApiModel(value = "WarnReferByTypeOut",description = "")
public class WarnReferByTypeOut {
    @ApiModelProperty(name = "dangerList",value = "险情首页",example = "0")
    private List<WarnType> dangerList;

    @ApiModelProperty(name = "newdangerList",value = "最新险情",example = "0")
    private List<NewWarn> newdangerList;

    @ApiModelProperty(name = "dangerList",value = "险情更多",example = "0")
    private List<WarnType> moredanger;
}