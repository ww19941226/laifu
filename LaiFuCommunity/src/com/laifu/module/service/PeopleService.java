package com.laifu.module.service;

import java.io.IOException;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.laifu.common.service.IBaseService;
import com.laifu.module.entity.People;
import com.laifu.module.entity.Usertype;

/**
 * 测试类service层
 * 
 * @author Raindrops
 * 
 */
public interface PeopleService extends IBaseService<People, Integer> {
	/**
	 * 根据页面大小
	 * 
	 * @param pn
	 * @param pageSize
	 * @param command
	 * @return
	 */
	/*
	 * Page<People> query(int pn, int pageSize, People command);
	 */
	public void test(Usertype usertype);

	public void saveAll();

	public void addSample(MultipartFile file, Model model) throws IOException;
}
