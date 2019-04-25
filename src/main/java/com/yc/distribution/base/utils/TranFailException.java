package com.yc.distribution.base.utils;

import org.apache.commons.logging.Log;

import java.util.Map;

public class TranFailException extends Exception {
	private static final long serialVersionUID = 1L;
	// 错误码
	public String errorCode;
	// 错误信息
	public String errorMsg;
	// 是否进行指定跳转，true为是，false为跳转到错误页面
	public boolean turnFlag = false;
	// 错误Map
	public Map<String, String> errorMap;
	// 需要跳转的页面
	public String jumpPage;
	// 是否为ajax跳转
	public boolean ajaxFlag = false;


	public TranFailException() {
	}


	public TranFailException(Throwable e) {
		super(e);
	}
	public TranFailException(String errorCode) {
		this.errorCode = errorCode;
	}

	public TranFailException(String errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	

	public TranFailException(String errorCode, String errorMsg, Throwable e) {
		super(errorMsg,e);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public TranFailException(String errorCode, String errorMsg,
                             Map<String, String> errorMap) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.errorMap = errorMap;
	}

	/**
	 * 指定页面跳转异常对象
	 * 
	 * @param errorCode
	 * @param errorMsg
	 * @param errorMap
	 * @param turnFlag
	 * @param jumpPage
	 */
	public TranFailException(String errorCode, String errorMsg,
                             Map<String, String> errorMap, boolean turnFlag, String jumpPage) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.errorMap = errorMap;
		this.turnFlag = turnFlag;
		this.jumpPage = jumpPage;
	}

	/**
	 * ajax异步跳转异常对象[跳转错误页面】
	 * 
	 * @param errorCode
	 * @param errorMsg
	 * @param ajaxFlag
	 */
	public TranFailException(String errorCode, String errorMsg, boolean ajaxFlag) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.ajaxFlag = ajaxFlag;
	}

	/**
	 * ajax异步跳转异常对象[不跳转错误页面】
	 * 
	 * @param errorCode
	 * @param errorMsg
	 * @param ajaxFlag
	 * @param errorMap
	 *            :错误对象
	 */
	public TranFailException(String errorCode, String errorMsg,
                             boolean ajaxFlag, Map<String, String> errorMap) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.ajaxFlag = ajaxFlag;
		this.errorMap = errorMap;
	}
	
	

	public static void addErrorLog(Log log, TranFailException e) {
		log.error("错误码:" + e.errorCode + ",错误原因:" + e.errorMsg);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Map<String, String> getErrorMap() {
		return errorMap;
	}

	public void setErrorMap(Map<String, String> errorMap) {
		this.errorMap = errorMap;
	}


	
}
