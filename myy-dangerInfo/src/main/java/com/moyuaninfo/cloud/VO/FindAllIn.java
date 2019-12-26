package com.moyuaninfo.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author wyk
 * @description:
 * @date 2019-03-30 18:41
 */
@Setter
@Getter
@ApiModel(value = "FindAllIn",description = "")
public class FindAllIn {
    @ApiModelProperty(name = "orgId",value = "组织id",dataType = "int",example = "1")
    private long orgId;

    @ApiModelProperty(name = "roomId",value = "实验室id",dataType = "long",example = "1")
    private long roomId;

    @ApiModelProperty(name = "camId",value = "摄像头id",dataType = "int",example = "1")
    private long camId;

    @ApiModelProperty(name = "buildname",value = "楼",dataType = "String",example = "1")
    private String buildname;

    @ApiModelProperty(name = "floorname",value = "层",dataType = "String",example = "1")
    private String floorname;

    @ApiModelProperty(name = "roomname",value = "房间",dataType = "String",example = "1")
    private String roomname;

    @ApiModelProperty(name = "camName",value = "摄像头",dataType = "String",example = "1")
    private String camName;

    //'1,2,3,4,5,6,7,8' : '实验室，摄像头，消息，人员设置，直播权限，监控时间,消息中心，险情列表'
    @ApiModelProperty(name = "istype",value = "标记",dataType = "String",example = "0")
    private String istype;

    @ApiModelProperty(name = "starttime",value = "开始时间",dataType = "String",example = "2019-08-01")
    private String starttime;
    @ApiModelProperty(name = "endtime",value = "结束时间",dataType = "String",example = "2019-08-01")
    private String endtime;
    @ApiModelProperty(name = "type",value = "详情类型",dataType = "String",example = "可疑火点")
    private String type;


    @ApiModelProperty(name = "addr",value = "险情上下位置（1：首页展示，2：更多功能）",dataType = "String",example = "1")
    private int belongs;
    @ApiModelProperty(name = "warntypeid",value = "xianqingid",dataType = "long",example = "1")
    private long warntypeid;
    @ApiModelProperty(name = "warnen",value = "warnen",dataType = "String",example = "1")
    private String warnen;
    @ApiModelProperty(name = "warntypeids",value = "xianqingid",dataType = "long",example = "1")
    private List<Long> warntypeids;

    public FindAllIn(){
        super();
    }

    public FindAllIn(String warnen){
        this.warnen = warnen;
    }

    public FindAllIn(long warntypeid){
        this.warntypeid = warntypeid;
    }
}