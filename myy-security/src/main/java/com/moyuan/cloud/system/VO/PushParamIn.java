package com.moyuan.cloud.system.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@ApiModel(value = "PushParamIn", description = "参数")
@Data
public class PushParamIn implements Serializable {

    @ApiModelProperty(value = "手机号", example = "138")
    private String phone;

    @ApiModelProperty(value = "内容", example = "[]")
    private ArrayList<String> content;

    @ApiModelProperty(value = "app内容", example = "aa")
    private String appcontent;

    @ApiModelProperty(value = "app标题", example = "aa")
    private String apptitle;

    @ApiModelProperty(value = "河道id", example = "1")
    private long areaid;

    @ApiModelProperty(value = "用户id", example = "1")
    private long userid;

    @ApiModelProperty(value = "组织id", example = "1")
    private long districtid;


}
