package com.moyuan.signin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "myy_signinplan")
@ApiModel(value = "myy_signinplan", description = "巡检打卡")
@Data
public class MyySigninPlan extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 200L;

    @ApiModelProperty(value = "用户ID", example = "1212")
    private String date;

    @ApiModelProperty(value = "区域ID", example = "1")
    private Long areaid;

    @ApiModelProperty(value = "a用户ID", example = "34343")
    private Long userida;

    @ApiModelProperty(value = "b用户ID", example = "34342")
    private Long useridb;

    @ApiModelProperty(value = "星期几", example = "星期一")
    private int week;

    @ApiModelProperty(value = "第几周", example = "第五周")
    private int weeknum;

}
