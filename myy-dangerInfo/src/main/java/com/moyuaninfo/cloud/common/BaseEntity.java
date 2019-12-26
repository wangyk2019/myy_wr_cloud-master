package com.moyuaninfo.cloud.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value = "BaseEntity",description = "基础数据库对象")
public abstract class BaseEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(name = "id",value = "ID",dataType = "Long",required = false,example = "10002")
	protected Long id;
	@CreatedDate
	@ApiModelProperty(name = "creattime",value = "创建时间",dataType = "Timestamp",required = false,example = "1321321654765")
	private Timestamp creattime;
	@UpdateTimestamp
	@ApiModelProperty(name = "updatetime",value = "更新时间",dataType = "Timestamp",required = false,example = "1321321654765")
	private Timestamp updatetime;

	
}
