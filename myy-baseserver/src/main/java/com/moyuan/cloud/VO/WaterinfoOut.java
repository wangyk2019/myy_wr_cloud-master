package com.moyuan.cloud.VO;

import com.moyuan.cloud.pojo.MyyAreaone;
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
@ApiModel(value = "WaterinfoOut",description = "")
public class WaterinfoOut {

    @ApiModelProperty(name = "myyAreaone",value = "河道信息",dataType = "MyyAreaone",example = "")
    private MyyAreaone myyAreaone;

    @ApiModelProperty(name = "myyPosition",value = "河长信息",dataType = "MyyPosition",example = "")
    private PositionOut myyPosition;

}