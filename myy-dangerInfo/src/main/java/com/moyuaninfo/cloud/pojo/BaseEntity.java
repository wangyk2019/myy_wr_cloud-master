package com.moyuaninfo.cloud.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@Data
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(name = "id",value = "ID",dataType = "Long",required = false,example = "10002")
	protected Long id;

	@CreationTimestamp
	@ApiModelProperty(name = "creattime",value = "创建时间",dataType = "Timestamp",required = false,example = "1321321654765",hidden = true)
	@Column(updatable = false)
	protected Timestamp creattime;

	@UpdateTimestamp
	@ApiModelProperty(name = "updatetime",value = "更新时间",dataType = "Timestamp",required = false,example = "1321321654765",hidden = true)
	protected Timestamp updatetime;

	@ApiModelProperty(name = "comment",value = "备注",dataType = "String",required = false,example = "备注")
	protected String comment;

	@ApiModelProperty(name = "state",value = "状态",dataType = "String",required = false,example = "1",hidden = true)
	@Column(name = "state")
	protected String state="1";


}
