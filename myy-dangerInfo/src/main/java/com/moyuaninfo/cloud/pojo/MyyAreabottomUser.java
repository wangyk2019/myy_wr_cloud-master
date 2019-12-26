//package com.moyuaninfo.cloud.pojo;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import org.hibernate.validator.constraints.Length;
//
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.validation.constraints.Min;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "myy_areabottomuser")
//@ApiModel(value = "MyyAreabottomUser", description = "区域三级下的人员")
//@Data
//public class MyyAreabottomUser extends BaseEntity implements Serializable {
//  private static final long serialVersionUID = 1003L;
//
//  @ApiModelProperty(value = "用户id",example = "33")
//  private long userid;
//
//  @ApiModelProperty(value = "区域三级id",example = "11")
//  private long areabottomid;
//
//  @ApiModelProperty(value = "是否负责人",example = "0")
//  private int manager;
//
//  @Override
//  public String toString() {
//    return "MyyAreabottomUser{" +
//            ", userid='" + userid + '\'' +
//            ", areabottomid=" + areabottomid +
//            ", manager=" + manager +
//            ", id=" + id +
//            ", creattime=" + creattime +
//            ", updatetime=" + updatetime +
//            ", comment='" + comment + '\'' +
//            ", state='" + state + '\'' +
//            '}';
//  }
//}
