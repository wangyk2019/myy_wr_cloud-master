package com.moyuan.cloud.VO;

import com.moyuan.cloud.pojo.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel(value = "MyyCameraIn", description = "摄像头")
@Data
public class MyyCameraIn implements Serializable {

  @ApiModelProperty(value = "id",example = "1")
  private long id;

  @NotBlank(message = "name不能为空")
  @ApiModelProperty(value = "别名",example = "摄像头A",required = true)
  private String name;

  @ApiModelProperty(value = "设备辨识码",example = "asfda12")
  private String deviceid;

  @ApiModelProperty(value = "摄像头类型型号",example = "sss")
  private String cameratype;

//  @ApiModelProperty(value = "方位",example = "河道1号")
//  private String location;

  @Min(value = 1,message = "areabottomid最小不能小于1")
  @ApiModelProperty(value = "所属岸",required = true, example = "1",dataType = "Long")
  private long areabottomid;

  @ApiModelProperty(value = "经度坐标",example = "55,456")
  private String longitude;

  @ApiModelProperty(value = "纬度坐标",example = "33,22")
  private String latitude;

  @NotBlank(message = "username不能为空")
  @ApiModelProperty(value = "访问摄像头的用户名",example = "admin",required = true,dataType = "String")
  private String username;

  @NotBlank(message = "password不能为空")
  @ApiModelProperty(value = "访问摄像头的密码",example = "aa",required = true,dataType = "String")
  private String password;

  @NotBlank(message = "ip不能为空")
  @ApiModelProperty(value = "访问摄像头的Ip",example = "192.168.1.1",required = true,dataType = "String")
  private String ip;

  @ApiModelProperty(value = "访问摄像头的通道",example = "1")
  private int channel;

  @ApiModelProperty(value = "摄像头的品牌",example = "hik")
  private String manufacture;

  @ApiModelProperty(value = "摄像头的链接源",example = "DVR")
  private String linksource;

  @Override
  public String toString() {
    return "MyyCameraIn{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", deviceid='" + deviceid + '\'' +
            ", cameratype='" + cameratype + '\'' +
//            ", location='" + location + '\'' +
            ", areabottomid=" + areabottomid +
            ", longitude='" + longitude + '\'' +
            ", latitude='" + latitude + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", ip='" + ip + '\'' +
            ", channel=" + channel +
            ", manufacture='" + manufacture + '\'' +
            ", linksource='" + linksource + '\'' +
            '}';
  }
}
