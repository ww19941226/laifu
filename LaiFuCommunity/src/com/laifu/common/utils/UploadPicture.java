package com.laifu.common.utils;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadPicture {
	public static String uploadHead(HttpServletRequest request, MultipartFile file, String preFileName) {
		try {
			String imgpath="/upload";
			String path = request.getServletContext().getRealPath(imgpath);
			String fileName = getFileName() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			
			if(preFileName != null && preFileName.equals("null") && !preFileName.trim().equals("")) {
				File preFile = new File(path, preFileName.substring(imgpath.length()+1));
				if(preFile.exists()) preFile.delete();
			}
			
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path, fileName));
			
			/*
			File targetFile = new File(path, fileName);
			
			if(!targetFile.exists()){
	            targetFile.mkdirs();  
	        }
			file.transferTo(targetFile);*/
			
			return imgpath + "/" + fileName;
				
		} catch(Exception e) {
			e.printStackTrace();
			//throw new Exception();
		}
		return "/upload/default.jpg";
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
