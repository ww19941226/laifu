package com.laifu.module.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.laifu.common.Constants;
import com.laifu.common.pagination.Page;
import com.laifu.module.entity.People;
import com.laifu.module.entity.Usertype;
import com.laifu.module.service.CommunityTopicService;
import com.laifu.module.service.PeopleService;
import com.laifu.module.service.PropertyService;
import com.laifu.module.support.editor.DateEditor;

/**
 * 测试类Controller
 * 
 * @author Raindrops
 * @version 2016/9/5
 */
@Controller
public class PeopleController {
	@Autowired
	@Qualifier("PeopleService")
	private PeopleService peopleService;

	@Autowired
	@Qualifier("CommunityTopicService")
	private CommunityTopicService communityTopicService;

	@Autowired
	@Qualifier("PropertyService")
	private PropertyService propertyService;

	/**
	 * 显示所有的数据 分页处理
	 * 
	 * @param request
	 * @param model
	 * @return url
	 */
	@RequestMapping(value = "/people", method = { RequestMethod.GET })
	public String list(HttpServletRequest request, Model model) {

		setCommonData(model);
		model.addAttribute(Constants.COMMAND, new People());

		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		Integer id = ServletRequestUtils.getIntParameter(request, "id", -1);
		boolean pre = ServletRequestUtils.getBooleanParameter(request, "pre",
				false);
		Page<People> page = null;
		if (id > 0) {
			if (pre) {
				page = peopleService.pre(id, pn);
			} else {
				page = peopleService.next(id, pn);
			}
		} else {
			page = peopleService.listAll(pn);
		}
		request.setAttribute("page", page);
		return "people/list";
	}

	@RequestMapping(value = "/saveAll", method = { RequestMethod.GET })
	public void saveAll(HttpServletRequest request, Model model)
			throws Exception {
		System.out.println("start");
		// peopleService.saveAll();
		try {

			peopleService.saveAll();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end");

	}

	@RequestMapping(value = "/people/mutiadd", method = { RequestMethod.POST })
	public String mutiadd(@RequestParam MultipartFile file,
			HttpServletRequest request, Model model) throws Exception {
		System.out.println(file.getOriginalFilename());
		if (file.getSize() != 0) {
			try {
				peopleService.addSample(file, model);
				System.out.println(1234);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(123);
			}
		}
		return "redirect:/people";
	}

	/*
	 * @RequestMapping(value = "/people/query", method = {RequestMethod.GET})
	 * public String query(HttpServletRequest request, Model model,
	 * 
	 * @ModelAttribute("command") People command) { setCommonData(model);
	 * model.addAttribute(Constants.COMMAND, command); int pn =
	 * ServletRequestUtils.getIntParameter(request, "pn", 1);
	 * model.addAttribute("page", peopleService.query(pn,
	 * Constants.DEFAULT_PAGE_SIZE, command));
	 * 
	 * return "people/list"; }
	 */

	/**
	 * 设置通用属性
	 * 
	 * @param model
	 */
	private void setCommonData(Model model) {
		// 设置通用属性
	}

	/*
	 * value="/people/{peopleId}/view",
	 * 中的{peopleId}为占位符表示可以映射请求为/people/xxxx/view 的URL 如：/detail/123/view
	 */
	/*
	 * 方法的参数@PathVariable(value="peopleId") Integer topicId 用于将URL中占位符所对应变量
	 * 映射到参数id上 ，@PathVariable(value="peopleId")
	 * 中value的值要和占位符/{peopleId}大括号中的值一致 只有一个参数可以不写
	 */
	@RequestMapping(value = "/people/{peopleId}/view", method = { RequestMethod.GET })
	public String view(@PathVariable Integer topicId, HttpServletRequest request) {
		request.setAttribute(Constants.COMMAND, peopleService.get(topicId));
		return "people/view";
	}

	/**
	 * 添加数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/people/add", method = { RequestMethod.GET })
	public String toAdd(Model model) {

		if (!model.containsAttribute(Constants.COMMAND)) {
			model.addAttribute(Constants.COMMAND, new People());
		}
		setCommonData(model);
		return "people/add";
	}

	/**
	 * 修改数据
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/people/{id}/update", method = { RequestMethod.GET })
	public String toUpdate(Model model, @PathVariable Integer id) {
		if (!model.containsAttribute(Constants.COMMAND)) {
			model.addAttribute(Constants.COMMAND, peopleService.get(id));
		}
		setCommonData(model);
		return "people/update";
	}

	/**
	 * 删除测试数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/people/{id}/delete", method = { RequestMethod.GET })
	public String toDelete(@PathVariable Integer id) {
		return "people/delete";
	}

	/**
	 * 验证添加数据
	 * 
	 * @param model
	 * @param command
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/people/add", method = { RequestMethod.POST })
	public String add(Model model,
			@ModelAttribute("command") @Valid People command,
			BindingResult result) {

		// 如果有验证错误 返回到form页面
		if (result.hasErrors()) {
			model.addAttribute(Constants.COMMAND, command);
			return toAdd(model);
		}
		peopleService.save(command);
		return "redirect:/people/success";
	}

	@RequestMapping(value = "/people/{id}/update", method = { RequestMethod.PUT })
	public String update(Model model,
			@ModelAttribute("command") @Valid People command,
			BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute(Constants.COMMAND, command);
			return toUpdate(model, command.getId());
		}
		peopleService.update(command);
		return "redirect:/people/success";
	}

	@RequestMapping(value = "/people/{id}/delete", method = { RequestMethod.DELETE })
	public String delete(@PathVariable Integer id) {
		peopleService.delete(id);
		return "redirect:/people/success";
	}

	@RequestMapping(value = "/people/success", method = { RequestMethod.GET })
	public String success() {
		return "people/success";
	}

	/**
	 * 在SpringMVC中，bean中定义了Date，double等类型， 如果没有做任何处理的话，日期以及double都无法绑定。
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	/* /usertype */
	@RequestMapping(value = "/usertype", method = { RequestMethod.GET })
	public String getUserTypeUrl() {
		return "usertype/doadd";
	}

	@RequestMapping(value = "/usertype/add")
	public String testUserType(String usertype_name, HttpServletRequest request) {
		Usertype usertype = new Usertype();
		usertype.setUsertype_name(usertype_name);
		peopleService.test(usertype);
		return "usertype/success";
	}

}
