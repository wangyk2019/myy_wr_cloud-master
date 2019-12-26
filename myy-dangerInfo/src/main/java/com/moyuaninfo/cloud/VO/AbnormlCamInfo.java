package com.moyuaninfo.cloud.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author big boy
 * @description:
 * @date 2019-03-30 18:41
 */
@Setter
@Getter
@ApiModel(value = "NoticeMsg",description = "公告信息")
public class AbnormlCamInfo {
//    @ApiModelProperty(name = "msgId",value = "实验室id",dataType = "String",required = true,example = "1")
//    private String labId;
    @ApiModelProperty(name = "labLocate",value = "实验室位置",dataType = "String",required = true,example = "1号楼10024室")
    private String labLocate;
    @ApiModelProperty(name = "user",value = "负责人",dataType = "String",required = true,example = "负责人")
    private String user;
    @ApiModelProperty(name = "cellphone",value = "电话",dataType = "String",required = true,example = "15168331797")
    private String cellphone;
//    @ApiModelProperty(name = "camNo",value = "摄像头编号",dataType = "String",required = true,example = "SW123456")
//    private String camNo;
    @ApiModelProperty(name = "camIp",value = "摄像头ip",dataType = "String",required = true,example = "192.168.0.222")
    private String camIp;
    @ApiModelProperty(name = "camUser",value = "摄像头账号",dataType = "String",required = true,example = "holy")
    private String camuser;
    @ApiModelProperty(name = "camPw",value = "摄像头密码",dataType = "String",required = true,example = "my123456")
    private String camPW;
    @ApiModelProperty(name = "camName",value = "摄像头名称",dataType = "String",required = true,example = "名称")
    private String camName;
}