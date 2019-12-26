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
@ApiModel(value = "WarnHistoryListOut",description = "")
public class WarnInfoListOut {
    @ApiModelProperty(name = "warnList",value = "险情预览",required = true,example = "0")
    private List<WarnMsg> warnList;

}