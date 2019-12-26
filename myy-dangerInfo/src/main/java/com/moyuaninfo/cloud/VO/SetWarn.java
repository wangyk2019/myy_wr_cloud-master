package com.moyuaninfo.cloud.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetWarn {

	@ApiModelProperty(value = "可疑火点",dataType="boolean",example = "false")
	private boolean isfire;

	@ApiModelProperty(value = "人行移动",dataType="boolean",example = "false")
	private boolean ismove;

	@ApiModelProperty(value = "视野遮挡",dataType="boolean",example = "false")
	private boolean iscover;

	@ApiModelProperty(value = "异物滞留",dataType="boolean",example = "false")
	private boolean isstay;

	@ApiModelProperty(value = "视野移位",dataType="boolean",example = "false")
	private boolean isshape;

}
