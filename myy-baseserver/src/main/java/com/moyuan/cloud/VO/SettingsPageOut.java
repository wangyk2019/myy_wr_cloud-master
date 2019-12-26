package com.moyuan.cloud.VO;

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
@ApiModel(value = "SettingsPageOut",description = "")
public class SettingsPageOut {
    @ApiModelProperty(name = "name",value = "姓名")
    private String name;

    @ApiModelProperty(name = "floorNum",value = "实验楼栋数")
    private Long floorNum;

    @ApiModelProperty(name = "camNum",value = "摄像头数")
    private Long camNum;

    @ApiModelProperty(name = "adminNum",value = "管理员数")
    private Long adminNum;

    @ApiModelProperty(name = "warnPhNum",value = "预警电话数")
    private Long warnPhNum;

    @ApiModelProperty(name = "warnMsgNum",value = "预警信息数")
    private Long warnMsgNum;

}