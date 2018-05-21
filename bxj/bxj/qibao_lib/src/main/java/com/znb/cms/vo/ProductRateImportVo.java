package com.znb.cms.vo;

public class ProductRateImportVo {
	private int pid;
	private int sheetNum;         //sheet 个数
	private int sheetDefineType;  //sheet定义为： 1=缴费年限，2=保障年限，3=领取年龄，4=其他因子（职业类别、地区、是否含社保）
	private int sheetDefaultValue;//sheet个数=1时  sheet定义默认为多少
	private int rowDefineType;    //行定义为：	 1=缴费年限，2=保障年限，3=领取年龄，4=其他因子（职业类别、地区、是否含社保）
	private int rowDefaultValue;  //行定义为：	 缴费期限默认值
	public int getSheetNum() {
		return sheetNum;
	}
	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}
	public int getSheetDefineType() {
		return sheetDefineType;
	}
	public void setSheetDefineType(int sheetDefineType) {
		this.sheetDefineType = sheetDefineType;
	}
	public int getRowDefineType() {
		return rowDefineType;
	}
	public void setRowDefineType(int rowDefineType) {
		this.rowDefineType = rowDefineType;
	}
	public int getSheetDefaultValue() {
		return sheetDefaultValue;
	}
	public void setSheetDefaultValue(int sheetDefaultValue) {
		this.sheetDefaultValue = sheetDefaultValue;
	}
	public int getRowDefaultValue() {
		return rowDefaultValue;
	}
	public void setRowDefaultValue(int rowDefaultValue) {
		this.rowDefaultValue = rowDefaultValue;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}	
	
}
