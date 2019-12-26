package com.moyuan.cloud.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "myy_river_checktask")
@ApiModel(value = "MyyRiverChecktask", description = "河道巡查任务")
@Data
public class MyyRiverChecktask extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4001971261020466010L;
    /**
     * 关联区域
     *
     * @mbggenerated
     */
    private Integer areaId;

    /**
     * 河段位置
     *
     * @mbggenerated
     */
    private String areaSite;

    /**
     * 河岸详情信息
     *
     * @mbggenerated
     */
    private String areaDetail;

    /**
     * 组织ID
     *
     * @mbggenerated
     */
    private Integer districtId;

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