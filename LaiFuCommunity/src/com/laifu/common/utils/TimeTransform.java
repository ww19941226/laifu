package com.laifu.common.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串和Date互相转换工具类
 * @author zepeng
 *2016-9-30
 */
public class TimeTransform {

	
	static public String DateToString(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");    
		String str=sdf.format(date);
		return str;
	}
	
	static public Date StringToDate(String time) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.parse(time);
		
	}
}
