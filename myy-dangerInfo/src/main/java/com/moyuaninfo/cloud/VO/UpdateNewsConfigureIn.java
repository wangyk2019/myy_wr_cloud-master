package com.moyuaninfo.cloud.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNewsConfigureIn {

//	@ApiModelProperty(value = "实验室id",example = "1")
//	private String labid;

	@ApiModelProperty(value = "id",example = "1")
	private int id;

	@ApiModelProperty(value = "电话",dataType="MobileSet", example = "")
	private MobileSet mobileSet;

	@ApiModelProperty(value = "短信",dataType="MsgSet", example = "")
	private MsgSet msgSet;

}
