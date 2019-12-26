package com.moyuan.cloud.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "myy_camera")
@ApiModel(value = "Myy_camera", description = "摄像头")
@Data
public class MyyCamera extends BaseEntity implements Serializable {
  private static final long serialVersionUID = 200L;

  @ApiModelProperty(value = "别名",example = "摄像头A")
  private String name;

  @ApiModelProperty(value = "设备辨识码",example = "1")
  private String deviceid;

  @ApiModelProperty(value = "摄像头类型型号",example = "摄像头类型型号")
  private String cameratype;

  @Min(value = 1,message = "areabottomid最小不能小于1")
  @ApiModelProperty(value = "区域底层id",example = "1")
  private long areabottomid;

  @ApiModelProperty(value = "经度坐标",example = "经度坐标")
  private String longitude;

  @ApiModelProperty(value = "纬度坐标",example = "纬度坐标")
  private String latitude;

  @ApiModelProperty(value = "链接",example = "链接")
  private String link;

  @ApiModelProperty(value = "链接方式",example = "链接方式")
  private String linktype;

  @ApiModelProperty(value = "方位",example = "方位")
  private String location;

  @ApiModelProperty(value = "产商",example = "产商")
  private String manufacturer;

  @ApiModelProperty(value = "摄像机状态",example = "0：故障；1：启用；")
  private String status;

  @ApiModelProperty(value = "链接状态",example = "0：等待接入；1：已接入；2：闲置")
  private String linkstatus;

  @ApiModelProperty(value = "直播数据流名",example = "直播数据流名")
  private String streamname;

  @NotBlank(message = "username不能为空")
  @ApiModelProperty(value = "访问摄像头的用户名",example = "访问摄像头的用户名")
  private String username;

  @NotBlank(message = "password不能为空")
  @ApiModelProperty(value = "访问摄像头的密码",example = "访问摄像头的密码")
  private String password;

  @NotBlank(message = "Ip不能为空")
  @ApiModelProperty(value = "访问摄像头的Ip",example = "访问摄像头的Ip")
  private String ip;

  @ApiModelProperty(value = "访问端口",example = "访问端口")
  private String rtspport;

  @ApiModelProperty(value = "访问通道",example = "访问通道")
  private String channel;

}
