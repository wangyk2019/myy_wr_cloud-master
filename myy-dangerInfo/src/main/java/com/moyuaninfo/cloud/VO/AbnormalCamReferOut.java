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
@ApiModel(value = "AbnormalCamReferOut",description = "")
public class AbnormalCamReferOut {
    @ApiModelProperty(name = "abnormlCamInfoList",value = "故障摄像头列表")
    private List<AbnormlCamInfo> abnormlCamInfoList;
}