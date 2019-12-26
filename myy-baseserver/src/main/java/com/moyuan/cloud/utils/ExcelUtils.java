package com.moyuan.cloud.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

//Excel导入导出工具类
public class ExcelUtils {
	/**
	 * 得到Workbook对象
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkBook(MultipartFile file) throws IOException{
		//这样写  excel 能兼容03和07
		InputStream is = file.getInputStream();
		Workbook hssfWorkbook = null;
		try {
			hssfWorkbook = new HSSFWorkbook(is);
		} catch (Exception ex) {
			is =file.getInputStream();
			hssfWorkbook = new XSSFWorkbook(is);
		}
		return hssfWorkbook;
	}

	/**
	 * 得到错误信息
	 * @param sb
	 * @param list
	 * @param i
	 * @param obj
	 * @param name  用哪个属性名去表明不和规定的数据
	 * @param msg
	 * @throws Exception
	 */
	public static void getWrongInfo(StringBuilder sb,List list,int i,Object obj,String name,String msg) throws Exception{
		Class clazz=obj.getClass();
		Object str=null;
		//得到属性名数组
		Field[] fields = clazz.getDeclaredFields();
		for(Field f : fields){
			if(f.getName().equals(name)){
				//用来得到属性的get和set方法
				PropertyDescriptor pd = new PropertyDescriptor(f.getName(), clazz);
				//得到get方法
				Method getMethod=pd.getReadMethod();
				str = getMethod.invoke(obj);
			}
		}
		if(i==0)
			sb.append(msg+str+";");
		else if(i==(list.size()-1))
			sb.append(str+"</br>");
		else
			sb.append(str+";");
	}

	public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
			boolean isCreateHeader, HttpServletResponse response) {
		ExportParams exportParams = new ExportParams(title, sheetName);
		exportParams.setCreateHeadRows(isCreateHeader);
		defaultExport(list, pojoClass, fileName, response, exportParams);
	}

	public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
			HttpServletResponse response) {
		defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
	}

	public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
		defaultExport(list, fileName, response);
	}

	private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response,
			ExportParams exportParams) {
		Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
		if (workbook != null)
			;
		downLoadExcel(fileName, response, workbook);
	}

	private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("content-Type", "application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			// throw new NormalException(e.getMessage());
		}
	}

	private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
		Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
		if (workbook != null)
			;
		downLoadExcel(fileName, response, workbook);
	}

	public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
		if (StringUtils.isBlank(filePath)) {
			return null;
		}
		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);
		List<T> list = null;
		try {
			list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
		} catch (NoSuchElementException e) {
			// throw new NormalException("模板不能为空");
		} catch (Exception e) {
			e.printStackTrace();
			// throw new NormalException(e.getMessage());
		}
		return list;
	}

	public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows,
                                          Class<T> pojoClass) {
		if (file == null) {
			return null;
		}
		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);
		List<T> list = null;
		try {
			list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
		} catch (NoSuchElementException e) {
			// throw new NormalException("excel文件不能为空");
		} catch (Exception e) {
			// throw new NormalException(e.getMessage());
			System.out.println(e.getMessage());
		}
		return list;
	}

}
