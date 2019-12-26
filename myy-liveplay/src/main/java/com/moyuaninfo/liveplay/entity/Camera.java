package com.moyuaninfo.liveplay.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "myy_camera")
@ApiModel(value = "Camera", description = "摄像头对象")
@Data
public class Camera extends BaseEntity implements Serializable {

    @Column(name = "cameratype", columnDefinition = "varchar(100) comment'摄像头类型型号'")
    @ApiModelProperty(name = "cameratype", value = "摄像头类型型号", dataType = "String", required = false, example = "XFSX-0211")
    private String cameratype;
    @Column(name = "deviceid", columnDefinition = "varchar(100) comment'摄像头设备码'")
    @ApiModelProperty(name = "deviceid", value = "摄像头设备码", dataType = "String", required = false, example = "XFSXDKLD0211")
    private String deviceid;
    @Column(name = "areaid", columnDefinition = "int(20) comment'所属区域'")
    @ApiModelProperty(name = "areaid", value = "所属区域ID", dataType = "Long", required = false, example = "120212")
    private Long areaid;
    @Column(name = "link", columnDefinition = "varchar(200) comment'摄像头链接'")
    @ApiModelProperty(name = "link", value = "摄像头链接", dataType = "String", required = false, example = "rtsp://test1:my123456@192.168.0.222/h264/Channels/1")
    private String link;
    @Column(name = "linktype", columnDefinition = "varchar(20) comment'摄像头链接类型'")
    @ApiModelProperty(name = "linktype", value = "链接类型", dataType = "String", required = false, example = "rtsp")
    private String linktype;
    @Column(name = "location", columnDefinition = "varchar(255) comment'摄像头地址'")
    @ApiModelProperty(name = "location", value = "摄像头地址", dataType = "String", required = false, example = "海达南路")
    private String location;
    @Column(name = "manufacturer", columnDefinition = "varchar(255) comment'摄像头制造商'")
    @ApiModelProperty(name = "manufacturer", value = "摄像头制造商", dataType = "String", required = false, example = "海康")
    private String manufacturer;
    @Column(name = "name", columnDefinition = "varchar(255) comment'摄像头名字'")
    @ApiModelProperty(name = "name", value = "摄像头名字", dataType = "String", required = false, example = "camera01")
    private String name;
    @Column(name = "status", columnDefinition = "varchar(5) comment'摄像头状态'")
    @ApiModelProperty(name = "status", value = "摄像头状态", dataType = "String", required = false, example = "0：故障；1：正常")
    private String status;
    @Column(name = "linkstatus", columnDefinition = "varchar(5) comment'摄像头链接状态'")
    @ApiModelProperty(name = "linkstatus", value = "摄像头链接状态", dataType = "String", required = false, example = "0：等待链接；1：链接中；2：闲置")
    private String linkstatus;
    @Column(name = "streamname", columnDefinition = "varchar(100) comment'串流名'")
    @ApiModelProperty(name = "streamname", value = "串流名", dataType = "String", required = false, example = "camera01")
    private String streamname;
    @Column(name = "username", columnDefinition = "varchar(100) comment'登陆名'")
    @ApiModelProperty(name = "username", value = "登陆名", dataType = "String", required = false, example = "admin")
    private String username;
    @Column(name = "password", columnDefinition = "varchar(100) comment'登陆密码'")
    @ApiModelProperty(name = "password", value = "登陆密码", dataType = "String", required = false, example = "admin123")
    private String password;
    @Column(name = "rtspport", columnDefinition = "varchar(10) comment'链接端口'")
    @ApiModelProperty(name = "rtspport", value = "链接端口", dataType = "String", required = false, example = "554")
    private String rtspport;
    @Column(name = "ip", columnDefinition = "varchar(100) comment'链接IP'")
    @ApiModelProperty(name = "ip", value = "链接IP", dataType = "String", required = false, example = "10.10.10.10")
    private String ip;
    @Column(name = "channel", columnDefinition = "varchar(100) comment'链接通道'")
    @ApiModelProperty(name = "channel", value = "链接通道", dataType = "String", required = false, example = "channel1")
    private String channel;
    @Column(name = "longitude", columnDefinition = "varchar(50) comment'经度坐标'")
    @ApiModelProperty(name = "longitude", value = "经度坐标", dataType = "String", required = false, example = "112.121")
    private String longitude;
    @Column(name = "latitude", columnDefinition = "varchar(50) comment'纬度坐标'")
    @ApiModelProperty(name = "latitude", value = "纬度坐标", dataType = "String", required = false, example = "22.365")
    private String latitude;

//    @Column(name = "creattime")
//    @ApiModelProperty(name = "creattime", value = "", dataType = "Date", required = false, example = "")
//    private Timestamp creattime;
//    @Column(name = "updatetime")
//    @ApiModelProperty(name = "updatetime", value = "", dataType = "Date", required = false, example = "")
//    private Timestamp updatetime;


    public Camera() {
    }

    public Camera(String cameratype, String deviceid, Long areaid, String link, String linktype, String location, String manufacturer,
                  String name, String status, String linkstatus, String streamname, String username, String password, String rtspport,
                  String ip, String channel, String longitude, String latitude) {
        this.cameratype = cameratype;
        this.deviceid = deviceid;
        this.areaid = areaid;
        this.link = link;
        this.linktype = linktype;
        this.location = location;
        this.manufacturer = manufacturer;
        this.name = name;
        this.status = status;
        this.linkstatus = linkstatus;
        this.streamname = streamname;
        this.username = username;
        this.password = password;
        this.rtspport = rtspport;
        this.ip = ip;
        this.channel = channel;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "cameratype='" + cameratype + '\'' +
                ", deviceid='" + deviceid + '\'' +
                ", areaid=" + areaid +
                ", link='" + link + '\'' +
                ", linktype='" + linktype + '\'' +
                ", location='" + location + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", linkstatus='" + linkstatus + '\'' +
                ", streamname='" + streamname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rtspport='" + rtspport + '\'' +
                ", ip='" + ip + '\'' +
                ", channel='" + channel + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
