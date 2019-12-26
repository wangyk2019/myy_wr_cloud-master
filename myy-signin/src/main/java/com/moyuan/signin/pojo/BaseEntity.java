package com.moyuan.signin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value = "BaseEntity", description = "基础数据库对象")
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(name = "id", value = "ID", dataType = "Long", required = true, example = "10002")
    protected Long id;

    @CreationTimestamp
    @Column(updatable = false)
    @ApiModelProperty(name = "creattime", value = "创建时间", dataType = "Timestamp", required = true, example = "1321321654765", hidden = true)
    protected Timestamp creattime;

    @UpdateTimestamp
    @ApiModelProperty(name = "updatetime", value = "更新时间", dataType = "Timestamp", required = true, example = "1321321654765", hidden = true)
    protected Timestamp updatetime;

    @ApiModelProperty(name = "comment", value = "备注", dataType = "String", required = false, example = "备注")
    protected String comment;

    @ApiModelProperty(name = "state", value = "状态", dataType = "String", required = false, example = "1", hidden = true)
    protected String state = "1";


}
