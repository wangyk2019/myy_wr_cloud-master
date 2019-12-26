package com.moyuan.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wyk
 * @description:
 * @date 2019-03-30 18:41
 */
@Setter
@Getter
@ApiModel(value = "FindAllAreaIn",description = "")
public class FindAllAreaIn {
    @ApiModelProperty(name = "orgId",value = "组织id",dataType = "long",example = "1")
    private long orgId;

    @ApiModelProperty(name = "oneId",value = "1id",dataType = "long",example = "1")
    private long oneId;

    @ApiModelProperty(name = "twoId",value = "2id",dataType = "long",example = "1")
    private long twoId;

    @ApiModelProperty(name = "threeId",value = "3id",dataType = "long",example = "1")
    private long threeId;

    @ApiModelProperty(name = "camId",value = "摄像头id",dataType = "long",example = "1")
    private long camId;

    @ApiModelProperty(name = "userId",value = "用户id",dataType = "long",example = "1")
    private long userId;

    @ApiModelProperty(name = "oneName",value = "1",dataType = "String",example = "1")
    private String oneName;

    @ApiModelProperty(name = "twoName",value = "2",dataType = "String",example = "1")
    private String twoName;

    @ApiModelProperty(name = "threeName",value = "3",dataType = "String",example = "1")
    private String threeName;

    @ApiModelProperty(name = "camName",value = "摄像头",dataType = "String",example = "1")
    private String camName;

//    //'1,2,3,4,5,6,7,8' : '实验室，摄像头，消息，人员设置，直播权限，监控时间,消息中心，险情列表'
//    @ApiModelProperty(name = "istype",value = "标记",dataType = "String",example = "0")
//    private String istype;

    @ApiModelProperty(name = "starttime",value = "开始时间",dataType = "String",example = "2019-08-01")
    private String starttime;
    @ApiModelProperty(name = "endtime",value = "结束时间",dataType = "String",example = "2019-08-01")
    private String endtime;
    @ApiModelProperty(name = "warntype",value = "详情类型",dataType = "String",example = "可疑火点")
    private String warntype;

}