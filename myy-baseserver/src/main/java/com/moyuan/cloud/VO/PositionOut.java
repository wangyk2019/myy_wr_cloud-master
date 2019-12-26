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
@ApiModel(value = "PositionOut",description = "")
public class PositionOut {

    @ApiModelProperty(name = "rivername",value = "河长名字",dataType = "String",example = "周易")
    private String rivername;

    @ApiModelProperty(name = "riverphone",value = "河长电话",dataType = "String",example = "15168331797")
    private String riverphone;

    @ApiModelProperty(name = "riverposition",value = "河长职务",dataType = "String",example = "**")
    private String riverposition;

    @ApiModelProperty(name = "riverresponsibility",value = "河长职责",dataType = "String",example = "**")
    private String riverresponsibility;

    @ApiModelProperty(name = "rivername",value = "警长名字",dataType = "String",example = "周易")
    private String pelicername;

    @ApiModelProperty(name = "pelicerphone",value = "警长电话",dataType = "String",example = "15168331797")
    private String pelicerphone;

    @ApiModelProperty(name = "riverresponsibility",value = "警长职责",dataType = "String",example = "**")
    private String pelicerresponsibility;

//    @ApiModelProperty(name = "supervisephone",value = "监督电话",dataType = "String",example = "15168331797")
//    private String supervisephone;
}