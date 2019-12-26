package com.moyuaninfo.liveplay.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "myy_livelog")
@ApiModel(value = "livelog", description = "直播推流日志")
public class Livelog extends BaseEntity implements Serializable {

    @Column(name = "userid", columnDefinition = "int(20) comment'用户ID'")
    @ApiModelProperty(name = "userid", value = "用户ID", dataType = "Long", required = true, example = "10002")
    private Long userid;
    @Column(name = "playstreamid", columnDefinition = "int(20) comment'推流ID'")
    @ApiModelProperty(name = "playstreamid", value = "推流ID", dataType = "Long", required = true, example = "10002")
    private Long playstreamid;
    @Column(name = "playstreamurl", columnDefinition = "varchar(200) comment'推流url'")
    @ApiModelProperty(name = "playstreamurl", value = "推流url", dataType = "string", required = true, example = "http://play.live.com/live/cam")
    private String playstreamurl;

    public Livelog() {
    }

    public Livelog(Long userid, Long playstreamid, String playstreamurl) {
        this.userid = userid;
        this.playstreamid = playstreamid;
        this.playstreamurl = playstreamurl;
    }

    @Override
    public String toString() {
        return "Livelog{" +
                "userid=" + userid +
                ", playstreamid=" + playstreamid +
                ", playstreamurl=" + playstreamurl +
                '}';
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getPlaystreamid() {
        return playstreamid;
    }

    public void setPlaystreamid(Long playstreamid) {
        this.playstreamid = playstreamid;
    }

    public String getPlaystreamurl() {
        return playstreamurl;
    }

    public void setPlaystreamurl(String playstreamurl) {
        this.playstreamurl = playstreamurl;
    }
}
