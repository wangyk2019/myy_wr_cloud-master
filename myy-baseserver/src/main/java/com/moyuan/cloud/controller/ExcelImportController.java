package com.moyuan.cloud.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.alibaba.fastjson.JSONObject;
import com.moyuan.cloud.VO.MyyAreaIn;
import com.moyuan.cloud.VO.UserIn;
import com.moyuan.cloud.pojo.*;
import com.moyuan.cloud.service.MyyAreaService;
import com.moyuan.cloud.service.MyyDistrictService;
import com.moyuan.cloud.service.MyyUserService;
import com.moyuan.cloud.utils.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * &#64;author cao_wencao
 * &#64;date 2018年12月13日 下午6:17:10
 * </pre>
 */
@RestController
@RequestMapping("/excel")
@Api(value = "导入数据",tags = "导入数据")
@Slf4j
public class ExcelImportController {
	@Autowired
	MyyUserService myyUserService;
	@Autowired
	MyyAreaService myyAreaService;
	@Autowired
	MyyDistrictService myyDistrictService;


	@ApiOperation(value = "信息导入")
	@RequestMapping(value = "/excelImport", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public JsonResult excelImport(MultipartFile file) throws IOException {
		//根据file得到Workbook,主要是要根据这个对象获取,传过来的excel有几个sheet页
		Workbook hssfWorkbook = ExcelUtils.getWorkBook(file);
		StringBuilder sb=new StringBuilder();

		Map<String,String> map = new HashMap<>();
		try {
			ImportParams params = new ImportParams();
			// 循环工作表Sheet
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				//表头在第几行
				params.setTitleRows(1);
				//距离表头中间有几行不要的数据
				params.setStartRows(0);
				//第几个sheet页
				params.setStartSheetIndex(numSheet);
				//验证数据
				params.setNeedVerfiy(true);

//				ExcelImportResult<Object> result=null;
				if(numSheet==1){
					ExcelImportResult<MyyAreaIn> result = ExcelImportUtil.importExcelMore(file.getInputStream(),
							MyyAreaIn.class, params);
//					//插入验证合格的数据
//					CallServiceUtil.callDataService("", "", new Object[] { result.getList() },
//					new Class[] { List.class });
					List<MyyAreaIn> areaList = result.getList();
					int count = 0;
					for (MyyAreaIn myyAreaIn : areaList) {
						MyyDistrict myyDistrict = myyDistrictService.findByName(myyAreaIn.getBelongs());
						MyyAreaone myyAreaone = null;
						if (myyDistrict != null) {
							myyAreaone = myyAreaService.findAllByDistrictidAndNameAndState(myyDistrict.getId(), myyAreaIn.getName());
						}

						if (myyAreaone == null){
							myyAreaone = new MyyAreaone();
						}
						if (myyDistrict != null){
							myyAreaone.setDistrictid(myyDistrict.getId());
						}else {
							myyAreaone.setDistrictid(0);
						}
						myyAreaone.setName(myyAreaIn.getName());
						myyAreaone.setArea_code(myyAreaIn.getArea_code());
						myyAreaone.setBelongs(myyAreaIn.getBelongs());
						myyAreaone.setLength(myyAreaIn.getLength());
						myyAreaone.setWidth(myyAreaIn.getWidth());
						myyAreaone.setAvg_waterlever(myyAreaIn.getAvg_waterlever());
						myyAreaone.setWarning_line(myyAreaIn.getWarning_line());
						myyAreaone.setSupervise_call(myyAreaIn.getSupervise_call());
						myyAreaone.setDestination(myyAreaIn.getDestination());
						myyAreaone.setIntroduction(myyAreaIn.getIntroduction());
						myyAreaone.setLevel(myyAreaIn.getLevel());
						myyAreaone.setOrigin(myyAreaIn.getOrigin());
						myyAreaone.setSewage_outlets(myyAreaIn.getSewage_outlets());
						myyAreaone.setState("1");

						myyAreaService.save_one(myyAreaone);
						count+=1;
						log.info("从Excel导入数据到数据库的详细为 ：{}", JSONObject.toJSONString(myyAreaIn));
						//TODO 将导入的数据做保存数据库操作
					}

					List list=null;
					//如果有些数据验证出来有误   为true
					if(result.isVerfiyFail()){
						//不合规定的数据
						list=result.getFailList();
						//拼凑错误信息,自定义
						for(int i=0;i<list.size();i++){
							if(list.get(i) instanceof MyyAreaIn)
								ExcelUtils.getWrongInfo(sb, list, i, list.get(i), "realName", "区域信息不符");
							else if(list.get(i) instanceof UserIn)
								ExcelUtils.getWrongInfo(sb, list, i, list.get(i), "age", "人员信息不符");
							//....
						}
					}
					map.put("area","成功"+count);
				}else if(numSheet==0){
					ExcelImportResult<UserIn> result = ExcelImportUtil.importExcelMore(file.getInputStream(),
							UserIn.class, params);
					//插入验证合格的数据
					List<UserIn> userList = result.getList();
					int count = 0;
					for (UserIn userIn : userList) {
						// System.out.println(User);
						MyyUser myyUser = myyUserService.findByPhone(userIn.getPhonenumber());
						if (myyUser == null){
							myyUser = new MyyUser();
							myyUser.setUsername(userIn.getName());
							myyUser.setPhonenumber(userIn.getPhonenumber());

							myyUser.setPassword("123456");
							myyUser.setRole("user");
							myyUser.setValid("0");
							myyUser.setState("1");
							myyUser.setLivepower("0");

							List<MyyDistrict> myyDistricts = myyDistrictService.findDistrictsByParams(userIn.getDistrict_name());
							if (myyDistricts.size()>0){
								myyUser.setDistrictid(myyDistricts.get(0).getId());
							}else {
								MyyDistrict myyDistrict = new MyyDistrict();
								myyDistrict.setName(userIn.getDistrict_name());
								myyDistrict.setState("1");
								myyDistrict = myyDistrictService.save(myyDistrict);
								myyUser.setDistrictid(myyDistrict.getId());
							}

							MyyAreaone myyAreaone = myyAreaService.findAllByDistrictidAndNameAndState(myyUser.getDistrictid(),userIn.getAreaone());
							if (myyAreaone != null){
								myyUser.setArea_id(myyAreaone.getId());
							}else {
								myyAreaone = new MyyAreaone();
								myyAreaone.setName(userIn.getAreaone());
								myyAreaone.setState("1");
								myyAreaone.setArea_code("1");
								myyAreaone.setDistrictid(myyUser.getDistrictid());
								myyAreaone = myyAreaService.save_one(myyAreaone);
								myyUser.setArea_id(myyAreaone.getId());
							}
							myyUser = myyUserService.save(myyUser);

//							if (!userIn.getAreatwo().equals("")){
//								String[] areatwos = userIn.getAreatwo().split("，");
//								for (int i=0;i<areatwos.length;i++){
//									MyyAreatwo myyAreatwo = myyAreaService.findAllByAreaoneidAndNameAndState(myyAreaone.getId(),areatwos[i]);
//									if (myyAreatwo == null){
//										myyAreatwo = new MyyAreatwo();
//										myyAreatwo.setName(areatwos[i]);
//										myyAreatwo.setState("1");
//										myyAreatwo.setArea_code("1");
//										myyAreatwo.setAreaoneid(myyAreaone.getId());
//										myyAreatwo = myyAreaService.save_two(myyAreatwo);
//									}
//
//									if (!userIn.getAreabottom().equals("")){
//										String[] areabottoms = userIn.getAreabottom().split("，");
//										for (int j=0;j<areabottoms.length;j++){
//											MyyAreabottom myyAreabottom = myyAreaService.findByAreatwoidAndNameAndState(myyAreatwo.getId(),areabottoms[j]);
//											if (myyAreabottom == null){
//												myyAreabottom = new MyyAreabottom();
//												myyAreabottom.setName(areabottoms[j]);
//												myyAreabottom.setState("1");
//												myyAreabottom.setArea_code("1");
//												myyAreabottom.setAreatwoid(myyAreatwo.getId());
//												myyAreabottom = myyAreaService.save_bottom(myyAreabottom);
//											}
//											MyyAreabottomUser myyAreabottomUser = new MyyAreabottomUser();
//											myyAreabottomUser.setAreabottomid(myyAreabottom.getId());
//											myyAreabottomUser.setUserid(myyUser.getId());
//											myyAreabottomUser.setManager(0);
//											myyUserService.save_areauser(myyAreabottomUser);
//										}
//									}
//
//								}
//							}

							count+=1;
						}else {
							map.put(userIn.getPhonenumber(),"手机号已存在");
						}
						map.put("user","成功"+count);

					}

					List list=null;
					//如果有些数据验证出来有误   为true
					if(result.isVerfiyFail()){
						//不合规定的数据
						list=result.getFailList();
						//拼凑错误信息,自定义
						for(int i=0;i<list.size();i++){
							if(list.get(i) instanceof MyyAreaIn)
								ExcelUtils.getWrongInfo(sb, list, i, list.get(i), "realName", "区域信息不符");
							else if(list.get(i) instanceof UserIn)
								ExcelUtils.getWrongInfo(sb, list, i, list.get(i), "age", "人员信息不符");
							//....
						}
					}
				}//............

			}
			if(sb.length()!=0){
				return new JsonResult(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult("导入失败！请检查导入文档的格式是否正确");
		}
		return new JsonResult(0,map);
	}
}
