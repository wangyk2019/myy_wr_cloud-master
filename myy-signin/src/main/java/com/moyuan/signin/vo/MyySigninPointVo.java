package com.moyuan.signin.vo;

import com.moyuan.signin.pojo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@ApiModel(value = "myy_signinpoint", description = "巡检打卡点")
@Data
public class MyySigninPointVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 200L;

    @ApiModelProperty(value = "名称", example = "打卡点A")
    private String name;

    @ApiModelProperty(value = "上级区域ID", example = "1")
    private long areaid;

    @ApiModelProperty(value = "经度坐标", example = "经度坐标")
    private String longitude;

    @ApiModelProperty(value = "纬度坐标", example = "纬度坐标")
    private String latitude;


}
