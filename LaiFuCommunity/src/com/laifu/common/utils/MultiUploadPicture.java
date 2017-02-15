package com.laifu.common.utils;

import java.util.Calendar;
import java.util.Date;
import java.io.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class MultiUploadPicture {
  
	public static String MutiUpload(HttpServletRequest request, MultipartFile[] file){
		String newfilename="";
		String newfilename1="";
		try {
			String imgpath="upload";
			String path=request.getSession().getServletContext().getRealPath("/");
			for(MultipartFile files:file){
				if(files!=null){
	         String    filename=	getFileName()+files.getOriginalFilename().substring(files.getOriginalFilename().lastIndexOf("."));
	         File targetFile = new File(path+"/"+imgpath, filename);
				
				if(!targetFile.exists()){
		            targetFile.mkdirs();  
		        }
				files.transferTo(targetFile);	
				newfilename1+=",upload/"+filename;			
				}
			}
	  if(newfilename1.length()>0) newfilename=newfilename1.substring(1);
	  //System.out.println(newfilename);
	  return newfilename;
			} catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
		}
		return "";
		
	}
	private static String getFileName() {
		String name = "";
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		name = name + cal.get(Calendar.YEAR)+getString(cal.get(Calendar.MONTH)+1)+getString(cal.get(Calendar.DAY_OF_MONTH))
				+getString(cal.get(Calendar.HOUR))+getString(cal.get(Calendar.MINUTE))+getString(cal.get(Calendar.SECOND))
				+cal.get(Calendar.MILLISECOND)+(int)(Math.random()*100000000);
		
		return name;
	}
	
	private static String getString(int time) {
		if(time < 10) return "0" + String.valueOf(time);
		else return String.valueOf(time);
	}
}


