package com.laifu.module.entity;

public class ReturnData {

	/**
	 * 返回结果
	 */
	private int returnResult;

	/**
	 * 返回信息
	 */
	private String returnDetail;

	/**
	 * 返回object
	 */
	private Object returnData;

	/**
	 * 平台调用（接口不用）
	 */
	private boolean success;

	/**
	 * 是否加密
	 */
	private int ifDecrypt;

	public ReturnData() {
		super();
		success = true;
		ifDecrypt = 0;
	}

	public int getReturnResult() {
		return returnResult;
	}

	public void setReturnResult(int returnResult) {
		this.returnResult = returnResult;
	}

	public String getReturnDetail() {
		return returnDetail;
	}

	public void setReturnDetail(String returnDetail) {
		this.returnDetail = returnDetail;
	}

	public Object getReturnData() {
		return returnData;
	}

	public void setReturnData(Object returnData) {
		this.returnData = returnData;
	}

	@Override
	public String toString() {
		return "ReturnData [returnResult=" + returnResult + ", returnDetail=" + returnDetail + ", returnData="
				+ returnData + "]";
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getIfDecrypt() {
		return ifDecrypt;
	}

	public void setIfDecrypt(int ifDecrypt) {
		this.ifDecrypt = ifDecrypt;
	}

}
