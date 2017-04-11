package com.laifu.module.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.laifu.module.service.MarketAdminService;

@Controller
public class MarketAdminController {
	@Resource(name = "MarketAdminService")
	private MarketAdminService marketAdminService;

	/********************************* 商品增删查改 *********************************************************************/

}
