package com.moyuaninfo.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "AddNoticeIn",description = "")
public class AddNoticeIn {

	@ApiModelProperty(value = "平台维护公告",example = "平台维护公告")
	private String title;

	@ApiModelProperty(value = "周几",example = "由于系统维护,所以暂关闭平台,具体开放时间请关注系统公告")
	private String text;

}
