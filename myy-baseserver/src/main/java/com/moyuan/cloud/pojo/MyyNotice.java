package com.moyuan.cloud.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "myy_notice")
public class MyyNotice extends BaseEntity implements Serializable {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Column(name = "name")
    @ApiModelProperty(value = "标题名称",example = "关于**")
    private String name;

    @Column(name = "info")
    @ApiModelProperty(value = "详细信息",example = "关于**")
    private String info;

    @Column(name = "type")
    @ApiModelProperty(value = "类型",example = "1")
    private String type;

    @Column(name = "readinfo")
    @ApiModelProperty(value = "阅读情况",example = "1")
    private String readinfo;

    @Column(name = "users")
    @ApiModelProperty(value = "告知人",example = "")
    private String users;

    @Column(name = "resource")
    @ApiModelProperty(value = "来源",example = "")
    private String resource;

    @Column(name = "beigntime")
    @JsonFormat(pattern = DATE_FORMAT)
    @ApiModelProperty(value = "开始时间",example = "2019-06-28 08:08:00")
    private Date beigntime;

    @Column(name = "endtime")
    @JsonFormat(pattern = DATE_FORMAT)
    @ApiModelProperty(value = "结束时间",example = "2019-06-28 08:08:00")
    private Date endtime;

}
