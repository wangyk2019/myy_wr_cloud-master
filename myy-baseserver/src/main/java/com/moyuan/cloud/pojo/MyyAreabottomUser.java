package com.moyuan.cloud.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "myy_areabottomuser")
@ApiModel(value = "MyyAreabottomUser", description = "区域3级人员表")
@Data
public class MyyAreabottomUser extends BaseEntity implements Serializable {

  @ApiModelProperty(value = "用户id",example = "1")
  private long userid;

  @ApiModelProperty(value = "名称",example = "1")
  private String username;

  @ApiModelProperty(value = "区域3级id",example = "11")
  private long areabottomid;

  @ApiModelProperty(value = "是否负责人",example = "1")
  private String manager;

}
