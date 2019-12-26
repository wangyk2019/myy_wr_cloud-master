package com.moyuan.cloud.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Myy_User_Clientid")
@ApiModel(value = "MyyUserClientid", description = "摄像头")
@Data
public class MyyUserClientid extends BaseEntity implements Serializable {

  @ApiModelProperty(value = "用户id",example = "1")
  private long userid;

  @ApiModelProperty(value = "手机cid",example = "11")
  private String clientid;

  @ApiModelProperty(value = "手机deviceToken",example = "1dasds2")
  private String deviceToken;

  @ApiModelProperty(value = "组织id",example = "1")
  private long districtid;


}
