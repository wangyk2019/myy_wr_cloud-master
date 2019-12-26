package com.moyuan.cloud.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "myy_river_checkplan")
@ApiModel(value = "MyyRiverCheckplan", description = "河道巡查计划")
@Data
public class MyyRiverCheckplan extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -3170332785024921238L;

    /**
     * 关联区域
     *
     * @mbggenerated
     */
    private Integer areaId;

    /**
     * 组织ID
     *
     * @mbggenerated
     */
    private Integer districtId;

    /**
     * 巡检日期
     *
     * @mbggenerated
     */
    private String checkTime;

    private Integer userId;

    /**
     * 手机号码
     *
     * @mbggenerated
     */
    private String phonenumber;

    /**
     * 创建人
     *
     * @mbggenerated
     */
    private Integer createUser;

    /**
     * 更新人
     *
     * @mbggenerated
     */
    private Integer updateUser;

}