package com.moyuan.cloud.VO;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * &#64;author cao_wencao
 * &#64;date 2018年12月13日 下午4:50:18
 * </pre>
 */
@ExcelTarget("20")
@Setter
@Getter
@ToString
public class UserIn implements java.io.Serializable{
	@Excel(name = "姓名", orderNum = "0", width=30)
	private String name;

	@Excel(name = "手机号", orderNum = "1", width=30)
	private String phonenumber;

	@Excel(name = "身份ID", orderNum = "2", width=30)
	private String id_number;

	@Excel(name = "所属组织", orderNum = "3", width=30)
	private String district_name;

	@Excel(name = "所属河道", orderNum = "4", width=30)
	private String areaone;

//	@Excel(name = "所属河段", orderNum = "5", width=30)
//	private String areatwo;
//
//	@Excel(name = "所属河岸", orderNum = "6", width=30)
//	private String areabottom;



}
