package com.moyuan.cloud.system.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "myy_loginlog")
@ApiModel(value = "MyyLoginLog", description = "登录日志表")
@Data
public class MyyLoginLog extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "用户ID", example = "123213")
    private Long userid;

    @ApiModelProperty(value = "登录方式", example = "p")
    private Long logintype;

    @ApiModelProperty(value = "登录终端", example = "android,weixin")
    private String platform;

    @ApiModelProperty(value = "登录IP", example = "1.2.3.5")
    private String ip;

    public MyyLoginLog(long userid, long logintype, String platform, String ip) {
        this.userid = userid;
        this.logintype = logintype;
        this.platform = platform;
        this.ip = ip;
    }

    public MyyLoginLog() {
    }
}
