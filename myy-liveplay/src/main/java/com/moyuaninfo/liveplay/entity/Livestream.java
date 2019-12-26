package com.moyuaninfo.liveplay.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "myy_livestream")
@ApiModel(value = "livestream", description = "直播推流")
public class Livestream extends BaseEntity implements Serializable {

    @Column(name = "cameraid", columnDefinition = "int(20) comment'摄像头ID'")
    @ApiModelProperty(name = "cameraid", value = "摄像头ID", dataType = "Long", required = true, example = "10002")
    private Long cameraid;
    @ApiModelProperty(name = "ip", value = "摄像头IP", dataType = "String", required = false, example = "10.11.111.11")
    @Column(name = "ip", columnDefinition = "varchar(255) comment'摄像头IP'")
    private String ip;
    @ApiModelProperty(name = "cameraname", value = "摄像头名称", dataType = "String", required = false, example = "camera01")
    @Column(name = "cameraname", columnDefinition = "varchar(255) comment'摄像头名字'")
    private String cameraname;
    @ApiModelProperty(name = "streamname", value = "串流名称", dataType = "String", required = false, example = "camera01")
    @Column(name = "streamname", columnDefinition = "varchar(255) comment'串流名字'")
    private String streamname;
    @ApiModelProperty(name = "status", value = "串流状态", dataType = "String", required = false, example = "0：失效；1：起效中")
    @Column(name = "status", columnDefinition = "varchar(5) comment'状态（0：失效；1：起效中）'")
    private String status;
    @ApiModelProperty(name = "pusherid", value = "推流器ID", dataType = "String", required = false, example = "ranF3_4Wg")
    @Column(name = "pusherid", columnDefinition = "varchar(50) comment'推流器ID（ranF3_4Wg）'")
    private String pusherid;
    @ApiModelProperty(name = "pusherurl", value = "推流器拉流url", dataType = "String", required = false, example = "rtsp://192.168.73.1/camera08")
    @Column(name = "pusherurl", columnDefinition = "varchar(255) comment'推流器拉流url（rtsp://192.168.73.1/camera08）'")
    private String pusherurl;

    public Livestream(Long cameraid, String ip, String cameraname, String streamname, String status) {
        this.cameraid = cameraid;
        this.ip = ip;
        this.cameraname = cameraname;
        this.streamname = streamname;
        this.status = status;
    }

    public Livestream(Long cameraid, String ip, String cameraname, String streamname, String status, String pusherid, String pusherurl) {
        this.cameraid = cameraid;
        this.ip = ip;
        this.cameraname = cameraname;
        this.streamname = streamname;
        this.status = status;
        this.pusherid = pusherid;
        this.pusherurl = pusherurl;
    }

    public Livestream() {
    }

    public Long getCameraid() {
        return cameraid;
    }

    public void setCameraid(Long cameraid) {
        this.cameraid = cameraid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCameraname() {
        return cameraname;
    }

    public void setCameraname(String cameraname) {
        this.cameraname = cameraname;
    }

    public String getStreamname() {
        return streamname;
    }

    public void setStreamname(String streamname) {
        this.streamname = streamname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPusherid() {
        return pusherid;
    }

    public void setPusherid(String pusherid) {
        this.pusherid = pusherid;
    }

    public String getPusherurl() {
        return pusherurl;
    }

    public void setPusherurl(String pusherurl) {
        this.pusherurl = pusherurl;
    }

    @Override
    public String toString() {
        return "Livestream{" +
                "cameraid=" + cameraid +
                ", ip='" + ip + '\'' +
                ", cameraname='" + cameraname + '\'' +
                ", streamname='" + streamname + '\'' +
                ", status='" + status + '\'' +
                ", pusherid='" + pusherid + '\'' +
                ", pusherurl='" + pusherurl + '\'' +
                ", id=" + id +
                '}';
    }
}
