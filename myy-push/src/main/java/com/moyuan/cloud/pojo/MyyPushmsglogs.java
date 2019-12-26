package com.moyuan.cloud.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Myy_Pushmsglogs")
@ApiModel(value = "MyyPushmsglogs", description = "推送记录")
@Data
public class MyyPushmsglogs extends BaseEntity implements Serializable {

  @ApiModelProperty(value = "推送类型",example = "msg,phone,msgcode,app")
  private String type;

  @ApiModelProperty(value = "推送信息",example = "asda113sa")
  private String info;

  @ApiModelProperty(value = "推送结果",example = "1")
  private String result;


}
