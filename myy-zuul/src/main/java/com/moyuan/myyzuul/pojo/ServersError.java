package com.moyuan.myyzuul.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "myy_serverslogs")
public class ServersError {

//    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "id",example = "1")
    private int id;

    @Column(name = "server")
    @ApiModelProperty(value = "服务",example = "*")
    private String server;

    @Column(name = "errorinfo")
    private String errorinfo;

    @Column(name = "method")
    private String method;

    @Column(name = "param")
    private String param;

    @Column(name = "responsebody")
    private String responsebody;

    @Column(name = "requestbody")
    private String requestbody;

    @Column(name = "state")
    private String state;

    @Column(name = "creattime")
    @CreationTimestamp
    private Date creattime;

}
