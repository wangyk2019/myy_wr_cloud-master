package com.moyuan.cloud.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "myy_district")
@ApiModel(value = "MyyDistrict", description = "行政区")
@Data
public class MyyDistrict extends BaseEntity implements Serializable {
  private static final long serialVersionUID = 300L;

  @NotBlank(message = "name不能为空")
  @ApiModelProperty(value = "名称",example = "名称A",required = true)
  private String name;

  @ApiModelProperty(value = "直播权限数量",example = "5")
  private int livepowernum=5;

  @ApiModelProperty(value = "腾讯云推流网址",example = "腾讯云推流网址")
  private String push_demain;

  @ApiModelProperty(value = "腾讯云播放网址",example = "腾讯云播放网址")
  private String play_demain;

  @ApiModelProperty(value = "腾讯云鉴权key",example = "腾讯云鉴权key")
  private String api_key;

  @ApiModelProperty(value = "腾讯云短信appid",example = "腾讯云短信appid")
  private String sms_appid;

  @ApiModelProperty(value = "腾讯云短信appkey",example = "腾讯云短信appkey")
  private String sms_appkey;

  @ApiModelProperty(value = "腾讯云cos_appid",example = "腾讯云cos_appid")
  private String cos_appid;

  @ApiModelProperty(value = "腾讯云cos_appkey",example = "腾讯云cos_appkey")
  private String cos_appkey;

  @ApiModelProperty(value = "腾讯云cos存储区域",example = "腾讯云cos存储区域")
  private String cos_region;

  @ApiModelProperty(value = "腾讯云cos存储桶名",example = "腾讯云cos存储桶名")
  private String cos_bucket;

  @ApiModelProperty(value = "腾讯云cos存储桶url",example = "腾讯云cos存储桶url")
  private String cos_bucketurl;

  @ApiModelProperty(value = "云端netty的IP地址",example = "云端netty的IP地址")
  private String cloudnetty_host;

  @ApiModelProperty(value = "云端netty的连接端口",example = "3333")
  private String cloudnetty_port;

  @ApiModelProperty(value = "区域推流器URL",example = "区域推流器URL")
  private String pusher_url;

  @ApiModelProperty(value = "区域推流器类型",example = "区域推流器类型")
  private String pusher_type;

  @ApiModelProperty(value = "直播流视频类型",example = "直播流视频类型")
  private String livestream_type;

  @ApiModelProperty(value = "录像视频类型",example = "录像视频类型")
  private String record_type;

  @ApiModelProperty(value = "深度回传URL",example = "深度回传URL")
  private String deepdetect_back_url;

  @ApiModelProperty(value = "深度接收URL",example = "深度接收URL")
  private String deepdetect_to_url;

  @ApiModelProperty(value = "录像录制时长",example = "1")
  private long record_timelength;

  @Override
  public String toString() {
    return "MyyDistrict{" +
            "name='" + name + '\'' +
            ", push_demain='" + push_demain + '\'' +
            ", play_demain='" + play_demain + '\'' +
            ", api_key='" + api_key + '\'' +
            ", sms_appid='" + sms_appid + '\'' +
            ", sms_appkey='" + sms_appkey + '\'' +
            ", cos_appid='" + cos_appid + '\'' +
            ", cos_appkey='" + cos_appkey + '\'' +
            ", cos_region='" + cos_region + '\'' +
            ", cos_bucket='" + cos_bucket + '\'' +
            ", cos_bucketurl='" + cos_bucketurl + '\'' +
            ", cloudnetty_host='" + cloudnetty_host + '\'' +
            ", cloudnetty_port='" + cloudnetty_port + '\'' +
            ", pusher_url='" + pusher_url + '\'' +
            ", pusher_type='" + pusher_type + '\'' +
            ", livestream_type='" + livestream_type + '\'' +
            ", record_type='" + record_type + '\'' +
            ", deepdetect_back_url='" + deepdetect_back_url + '\'' +
            ", deepdetect_to_url='" + deepdetect_to_url + '\'' +
            ", record_timelength=" + record_timelength +
            ", id=" + id +
            ", creattime=" + creattime +
            ", updatetime=" + updatetime +
            ", comment='" + comment + '\'' +
            ", state='" + state + '\'' +
            '}';
  }
}
