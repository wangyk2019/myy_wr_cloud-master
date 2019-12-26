package com.moyuaninfo.suggest.model;

import java.io.Serializable;

/**
 * 用于封装服务器到客户端的Json返回值
 * @author soft01
 *
 */
public class JsonResult<T> implements Serializable {
	//Serializable将对象的状态保存在存储媒体中以便可以在以后重新创建出完全相同的副本
	public static final int SUCCESS=0;
	public static final int ERROR=1;
	public static final int OTHER=2;

	private int code;
	private String msg = "";
	private T data;
	private String pass="";

	public JsonResult(){
		code = SUCCESS;
	}
	//为了方便，重载n个构造器
	public JsonResult(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public JsonResult(int code, String error){
		this(code,error,null);
	}
	public JsonResult(int code, T data){
		this(code,"",data);
	}
	public JsonResult(String error){
		this(ERROR,error,null);
	}

	public JsonResult(T data){
		this(SUCCESS,"",data);
	}
	public JsonResult(int code){
		this(code,"",null);
	}
	public JsonResult(Throwable e){
		this(ERROR,e.getMessage(),null);
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public static int getSuccess() {
		return SUCCESS;
	}
	public static int getError() {
		return ERROR;
	}
	@Override
	public String toString() {
		return "JsonResult [code=" + code + ", msg=" + msg + ", pass=" + pass + ", data=" + data + "]";
	}
}
