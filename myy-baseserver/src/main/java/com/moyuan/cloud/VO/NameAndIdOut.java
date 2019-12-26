package com.moyuan.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author wyk
 * @description:
 * @date 2019-03-30 18:41
 */
@Setter
@Getter
@ApiModel(value = "NameAndIdOut",description = "")
public class NameAndIdOut implements Serializable {

    @ApiModelProperty(name = "id",value = "id",dataType = "long",required = true,example = "1")
    private long id;

    @ApiModelProperty(name = "name",value = "名称",dataType = "String",required = true,example = "1")
    private String name;

}