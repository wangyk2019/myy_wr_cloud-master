package com.moyuan.signin.vo;

import com.moyuan.signin.pojo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "MyySigninPageOutVo", description = "巡检打卡")
@Data
public class MyySigninPageOutVo {

    @ApiModelProperty(value = "用户名", example = "李四")
    private String username;

    @ApiModelProperty(value = "用户ID", example = "1212")
    private long userid;

    @ApiModelProperty(value = "计划ID", example = "34343")
    private long planid;

    @ApiModelProperty(value = "打卡点ID", example = "1")
    private long pointid;

    @ApiModelProperty(value = "经度坐标", example = "经度坐标")
    private String longitude;

    @ApiModelProperty(value = "纬度坐标", example = "纬度坐标")
    private String latitude;

    @ApiModelProperty(value = "打卡结果", example = "0：失败；1：成功；")
    private String result;

    @ApiModelProperty(value = "打卡时间", example = "20191212100000")
    private Date signintime;


}
