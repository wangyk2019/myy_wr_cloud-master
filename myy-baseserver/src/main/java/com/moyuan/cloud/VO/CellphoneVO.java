package com.moyuan.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "CellphoneVO",description = "手机号码")
public class CellphoneVO {
	@ApiModelProperty(name = "cellphone",value = "手机号码",dataType = "String",required = true,example = "15988888888")
	private String cellphone;
}
