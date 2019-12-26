package com.moyuaninfo.suggest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SuggestSolveDto
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/23 20:11
 * @Version 1.0
 **/
@ApiModel(value = "SuggestSolveDto", description = "处理咨询建议dto")
@Data
public class SuggestSolveDto implements Serializable {

    private static final long serialVersionUID = 1576831069608200099L;

    @ApiModelProperty(value = "当前用户id")
    private Long userId;

    @ApiModelProperty(value = "咨询建议id")
    private Long suggestId;

    @ApiModelProperty(value = "回复内容")
    private String result;

    @ApiModelProperty(value = "处理人姓名")
    private String updateUserName;

    @ApiModelProperty(value = "处理人联系方式")
    private String updateUserPhonenumber;

    @ApiModelProperty(value = "处理图片路径")
    private String solvePhotoPath;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;


}
