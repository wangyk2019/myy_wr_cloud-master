package com.moyuan.ntpservice.service;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class BaseEntity implements Serializable{

	@ApiModelProperty(name = "id",value = "ID",dataType = "Long",required = false,example = "10002")
	protected Long id;

	@ApiModelProperty(name = "creattime",value = "创建时间",dataType = "Timestamp",required = false,example = "1321321654765",hidden = true)
	protected Timestamp creattime;

	@ApiModelProperty(name = "updatetime",value = "更新时间",dataType = "Timestamp",required = false,example = "1321321654765",hidden = true)
	protected Timestamp updatetime;

	@ApiModelProperty(name = "comment",value = "备注",dataType = "String",required = false,example = "备注")
	protected String comment;

	@ApiModelProperty(name = "state",value = "状态",dataType = "String",required = false,example = "1",hidden = true)
	protected String state="1";


}
