package com.laifu.module.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.laifu.common.exception.UserException;
import com.laifu.common.pagination.Page;
import com.laifu.common.utils.MD5;
import com.laifu.common.utils.UploadPicture;
import com.laifu.module.entity.Community;
import com.laifu.module.entity.Complains;
import com.laifu.module.entity.User;
import com.laifu.module.service.SystemManageService;
import com.laifu.module.service.UserService;
import com.laifu.module.vo.UserVo;

/**
 * 系统管理员Controller
 * 
 * @author zepeng
 * @version 2016-9-7
 */
@Controller
public class SystemAdminController {

	@Autowired
	@Qualifier("SystemManageService")
	private SystemManageService systemManageService;

	@Autowired
	@Qualifier("UserService")
	private UserService userService;

	/************************************************************************************************/

	/**
	 * 通过手机号判断用户是否存在
	 * 
	 * @param response
	 * @param user
	 */
	@RequestMapping(value = "/sysadmin/exit", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void userAccountExit(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String SendCode = userService.exit(request
					.getParameter("user_account")) ? "1" : "0";
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().write(SendCode);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断账号密码是否对应
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/sysadmin/checkAccountAndPassword", method = {
			RequestMethod.POST, RequestMethod.GET })
	public void checkAccountAndPassword(HttpServletResponse response,
			@ModelAttribute("command") @Valid User user) {
		try {
			// System.out.println(user.getUser_account());
			// System.out.println(user.getUser_password());
			MD5 md5 = new MD5();
			user.setUser_password(md5.EncoderByMd5(user.getUser_password()));
			User u = userService.login(user, 0);
			String code;
			if (u != null)
				code = "1";
			else
				code = "0";
			// System.out.println(code);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().write(code);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***********************************************************************************************************/

	/**
	 * 进入登录页面
	 * 
	 * @return 登录页面
	 */
	@RequestMapping(value = "/sysadmin/sysadmin_login", method = { RequestMethod.GET })
	public String gotoLogin(HttpServletRequest request) {
		return "/sysadmin/sysadmin_login";
	}

	/**
	 * 进入管理员首页
	 * 
	 * @param request
	 * @return 首页
	 */
	@RequestMapping(value = "/sysadmin/sysadmin", method = { RequestMethod.GET })
	public String gotoIndex(HttpServletRequest request) {
		try {

			UserVo vo = (UserVo) request.getSession().getAttribute("admin");
			if (vo == null || vo.getUsertype().getUsertype_id() != 4)
				return "redirect:/sysadmin/sysadmin_login";
			request.setAttribute("user", ((UserVo) request.getSession()
					.getAttribute("admin")).getUser());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/sysadmin";
	}

	/**
	 * 进入更改信息的页面
	 * 
	 * @param request
	 *            请求的对象
	 * @return 个人信息页面
	 */
	@RequestMapping(value = "/sysadmin/sysadmin_infor", method = { RequestMethod.GET })
	public String gotoInfor(HttpServletRequest request) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/sysadmin_infor";
	}

	/**
	 * 进入密码修改的页面
	 * 
	 * @param request
	 *            请求对象
	 * @return 密码修改的页面
	 */
	@RequestMapping(value = "/sysadmin/information/password_update", method = { RequestMethod.GET })
	public String gotoPasswordupdate(HttpServletRequest request) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/information/password_update";
	}

	/**
	 * 进入小区管理页面
	 * 
	 * @param request
	 *            请求对象
	 * @return 小区管理页面
	 */
	@RequestMapping(value = "/sysadmin/sysadmin_community", method = { RequestMethod.GET })
	public String gotoCommunity(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
			String hql = "from Community order by community_id desc";
			Page<Community> page = systemManageService.community_listAll(hql,
					pn, 10);
			request.setAttribute("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/sysadmin_community";
	}

	/**
	 * 进入小区详细信息页面
	 * 
	 * @param request
	 *            请求对象
	 * @return
	 */
	@RequestMapping(value = "/sysadmin/{community_id}/community/community_detail", method = { RequestMethod.GET })
	public String gotoCommunity_detail(HttpServletRequest request,
			@PathVariable int community_id) {
		try {
			Community community = systemManageService
					.getCommunity(community_id);
			request.setAttribute("community", community);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/community/community_detail";
	}

	/**
	 * 进入小区编辑的页面
	 * 
	 * @param request
	 *            请求对象
	 * @param community_id
	 *            小区id
	 * @return 小区编辑的页面
	 */
	@RequestMapping(value = "/sysadmin/{community_id}/community/community_edit", method = { RequestMethod.GET })
	public String gotoCommunity_edit(HttpServletRequest request,
			@PathVariable int community_id) {
		try {
			Community community = systemManageService
					.getCommunity(community_id);
			request.setAttribute("community", community);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/community/community_edit";
	}

	/**
	 * 进入小区审核页面
	 * 
	 * @param request
	 *            请求对象
	 * @param community_id
	 *            要审核的小区id
	 * @return 小区详情页面
	 */
	@RequestMapping(value = "/sysadmin/{community_id}/checkcomm/checkcomm_detail", method = { RequestMethod.GET })
	public String gotoCheckcomm_detail(HttpServletRequest request,
			@PathVariable int community_id) {
		try {
			Community community = systemManageService
					.getCommunity(community_id);
			request.setAttribute("community", community);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/checkcomm/checkcomm_detail";
	}

	/**
	 * 进入审核小区的列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sysadmin/sysadmin_checkcomm", method = { RequestMethod.GET })
	public String gotoSysadmin_checkcomm(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
			String hql = "from Community where community_id = 0 order by community_id desc";
			Page<Community> page = systemManageService.community_listAll(hql,
					pn, 10);
			request.setAttribute("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/sysadmin_checkcomm";
	}

	/**
	 * 进入未审核小区的页面
	 * 
	 * @param request
	 *            请求的对象
	 * @return 未审核小区列表页面
	 */
	@RequestMapping(value = "/sysadmin/sysadmin_failcomm", method = { RequestMethod.GET })
	public String gotoSysadmin_failcomm(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
			String hql = "from Community where community_id = 2 order by community_id desc";
			Page<Community> page = systemManageService.community_listAll(hql,
					pn, 10);
			request.setAttribute("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/sysadmin_failcomm";
	}

	/**
	 * 获取投诉建议的列表
	 * 
	 * @param request
	 * @return 投诉建议的列表页面
	 */
	@RequestMapping(value = "/sysadmin/sysadmin_complains", method = { RequestMethod.GET })
	public String gotoSysadmin_complains(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
			String hql = "from Complains where complains_usertype=3 order by complains_id desc";
			Page<Complains> page = systemManageService.complains_listAll(hql,
					pn, 10);
			request.setAttribute("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/sysadmin_complains";
	}

	/**
	 * 进入未审核小区的列表页面
	 * 
	 * @param request
	 * @param community_id
	 * @return 未审核小区的列表页面
	 */
	@RequestMapping(value = "/sysadmin/{community_id}/failcomm_detail", method = { RequestMethod.GET })
	public String gotoFailcomm_detail(HttpServletRequest request,
			@PathVariable int community_id) {
		try {
			Community c = systemManageService.getCommunity(community_id);
			request.setAttribute("community", c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/failcomm/failcomm_detail";
	}

	/**
	 * 进入
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sysadmin/{complains_id}/complains/complains_detail", method = { RequestMethod.GET })
	public String gotoComplains_detail(HttpServletRequest request,
			@PathVariable int complains_id) {
		try {
			Complains complains = systemManageService
					.getComplains(complains_id);
			User user = userService
					.getByAccount(complains.getComplains_phone());
			request.setAttribute("complains", complains);
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/complains/complains_detail";
	}

	/**
	 * 进入审核小区管理员列表页
	 * 
	 * @param request
	 * @return 审核小区管理员列表页
	 */
	@RequestMapping(value = "/sysadmin/sysadmin_comadmin", method = { RequestMethod.GET })
	public String gotoSysadmin_comadmin(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
			String hql = "from User where user_type = 3 order by user_id desc";
			Page<User> page = userService.listAll(hql, pn, 10);
			request.setAttribute("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/sysadmin_comadmin";
	}

	/**
	 * 进入系统小区管理员列表页
	 * 
	 * @param request
	 * @return 审核系统管理员列表页
	 */
	@RequestMapping(value = "/sysadmin/sysadmin_sysadmin", method = { RequestMethod.GET })
	public String gotoSysadmin_sysadmin(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
			String hql = "from User where user_type = 4 order by user_id desc";
			Page<User> page = userService.listAll(hql, pn, 10);
			request.setAttribute("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/sysadmin_sysadmin";
	}

	/**
	 * 进入小区管理员的详情页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sysadmin/comadmin/{user_id}/comadmin_detail", method = { RequestMethod.GET })
	public String gotoComadmin_detail(HttpServletRequest request,
			@PathVariable int user_id) {
		try {
			User user = userService.getUser(user_id);
			request.setAttribute("user", user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/comadmin/comadmin_detail";
	}

	/**
	 * 进入系统管理员的详情页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sysadmin/sysadmin/{user_id}/sysadmin_detail", method = { RequestMethod.GET })
	public String gotoSysadmin_detail(HttpServletRequest request,
			@PathVariable int user_id) {
		try {
			User user = userService.getUser(user_id);
			request.setAttribute("user", user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/sysadmin/sysadmin_detail";
	}

	@RequestMapping(value = "/sysadmin/comadmin/{user_id}/comadmin_edit", method = { RequestMethod.GET })
	public String gotoComadmin_edit(HttpServletRequest request,
			@PathVariable int user_id) {
		try {
			User user = userService.getUser(user_id);
			request.setAttribute("user", user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/comadmin/comadmin_edit";
	}

	@RequestMapping(value = "/sysadmin/sysadmin/{user_id}/sysadmin_edit", method = { RequestMethod.GET })
	public String gotoSysadmin_edit(HttpServletRequest request,
			@PathVariable int user_id) {
		try {
			User user = userService.getUser(user_id);
			request.setAttribute("user", user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/sysadmin/sysadmin_edit";
	}

	@RequestMapping(value = "/sysadmin/comadmin/comadmin_add", method = { RequestMethod.GET })
	public String gotoComadmin_add(HttpServletRequest request) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/comadmin/comadmin_add";
	}

	@RequestMapping(value = "/sysadmin/sysadmin/sysadmin_add", method = { RequestMethod.GET })
	public String gotoSysadmin_add(HttpServletRequest request) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/sysadmin/sysadmin/sysadmin_add";
	}

	/*********************************************************************************/

	/**
	 * 系统管理员登录判断操作
	 * 
	 * @param request
	 *            请求对象
	 * @param user
	 *            要登陆的用户
	 * @return 登录或首页
	 */
	@RequestMapping(value = "/sysadmin/login", method = { RequestMethod.POST })
	public String login(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("command") @Valid User user) {
		try {
			MD5 md5 = new MD5();
			user.setUser_password(md5.EncoderByMd5(user.getUser_password()));
			User u = userService.login(user, 0);

			if (u != null) {
				int type = u.getUser_type();
				request.getSession().setAttribute("admin",
						userService.getUserVoByAccount(u.getUser_account()));

				if (type == 3) {
					return "redirect:/property/property";
				} else if (type == 2) {
					return "redirect:/sysadmin/redirect";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/sysadmin/sysadmin_login";
	}

	@RequestMapping(value = "/sysadmin/redirect", method = { RequestMethod.GET })
	public void redirect(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.sendRedirect(request.getContextPath()
					+ "/extjs/index.html");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 进行密码修改操作
	 * 
	 * @param request
	 *            请求对象
	 * @return 个人信息页面
	 */
	@RequestMapping(value = "/sysadmin/updatePassword", method = { RequestMethod.POST })
	public String updatePassword(HttpServletRequest request,
			@ModelAttribute("command") @Valid User user) {
		try {
			UserVo vo = ((UserVo) request.getSession().getAttribute("admin"));
			userService.updatePassword(vo.getUser());
			vo.getUser().setUser_password(user.getUser_password());
			request.getSession().setAttribute("admin", vo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/sysadmin/sysadmin_infor";
	}

	/**
	 * 注销登录
	 * 
	 * @param request
	 *            请求对象
	 * @return 登录页面
	 */
	@RequestMapping(value = "/sysadmin/logout", method = { RequestMethod.GET })
	public String logout(HttpServletRequest request) {

		try {

			userService.logout(request);

		} catch (UserException e) {
			e.printStackTrace();
		}

		return "redirect:/sysadmin/sysadmin_login";
	}

	/**
	 * 更新个人信息的功能
	 * 
	 * @param request
	 *            请求对象
	 * @param user
	 *            要修改的对象
	 * @return 用户个人信息页面
	 */
	@RequestMapping(value = "/sysadmin/updateInfor", method = { RequestMethod.POST })
	public String updateInfor(HttpServletRequest request,
			@ModelAttribute("command") @Valid User user) {
		try {
			userService.updateInfor(user, request);

		} catch (UserException e) {
			e.printStackTrace();
		}
		return "redirect:/sysadmin/sysadmin_infor";
	}

	/**
	 * 更新小区信息的功能
	 * 
	 * @param request
	 *            请求对象
	 * @param community
	 *            要修改的小区对象
	 * @return 小区列表页
	 */
	@RequestMapping(value = "/sysadmin/{community_id}/updateCommunity", method = { RequestMethod.POST })
	public String updateCommunity(HttpServletRequest request,
			@ModelAttribute("command") @Valid Community community,
			@PathVariable int community_id) {
		try {
			community.setCommunity_id(community_id);
			systemManageService.updateCommunity(community);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/sysadmin/" + community_id
				+ "/community/community_detail";
	}

	/**
	 * 进行小区删除操作
	 * 
	 * @param request
	 *            请求的对象
	 * @param community_id
	 *            要删除的小区id
	 * @return 小区列表页面
	 */
	@RequestMapping(value = "/sysadmin/{community_id}/deleteCommunity", method = { RequestMethod.GET })
	public String deleteCommunity(HttpServletRequest request,
			@PathVariable int community_id) {
		try {
			// System.out.println(community_id);
			systemManageService.deleteCommunity(community_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/sysadmin/sysadmin_community";
	}

	/**
	 * 进行小区审核操作
	 * 
	 * @param request
	 *            请求的对象
	 * @param community_id
	 *            要审核的小区id
	 * @return
	 */
	@RequestMapping(value = "/sysadmin/{community_id}/{community_checkstate}/checkCommunity", method = { RequestMethod.GET })
	public String checkCommunity(HttpServletRequest request,
			@PathVariable int community_id,
			@PathVariable int community_checkstate) {
		try {
			systemManageService.checkCommunity(community_id,
					community_checkstate);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/sysadmin/sysadmin_checkcomm";
	}

	/**
	 * 回复投诉建议
	 * 
	 * @param request
	 * @param complains
	 *            要回复的投诉建议
	 * @return
	 */
	@RequestMapping(value = "/sysadmin/replyComplains", method = { RequestMethod.POST })
	public String replyComplains(HttpServletRequest request,
			@ModelAttribute("command") @Valid Complains complains) {
		try {
			systemManageService.replyComplains(complains);

		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * return "redirect:/sysadmin/" + complains.getComplains_id() +
		 * "/complains/complains_detail";
		 */
		return "redirect:/property/property_complains";
	}

	/**
	 * 添加用户
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/sysadmin/addComadmin", method = { RequestMethod.POST })
	public String addComadmin(HttpServletRequest request,
			@ModelAttribute("command") @Valid User user) {
		try {
			user.setUser_type(3);
			user.setUser_registertime(new Date());
			user.setUser_head("/upload/default.jpg");
			userService.add(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/sysadmin/sysadmin_comadmin";
	}

	/**
	 * 更新小区管理员信息
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/sysadmin/updateComadmin", method = { RequestMethod.POST })
	public String updateComadmin(HttpServletRequest request,
			@ModelAttribute("command") @Valid User user) {
		try {
			userService.ChangeUserInfor(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/sysadmin/sysadmin_comadmin";
	}

	@RequestMapping(value = "/sysadmin/{comadmin_id}/deleteComadmin", method = { RequestMethod.GET })
	public String deleteComadmin(HttpServletRequest request,
			@PathVariable int comadmin_id) {
		try {
			User user = new User();
			user.setUser_id(comadmin_id);
			userService.delete(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/sysadmin/sysadmin_comadmin";
	}

	/**
	 * 添加用户
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/sysadmin/addSysadmin", method = { RequestMethod.POST })
	public String addSysadmin(HttpServletRequest request,
			@ModelAttribute("command") @Valid User user) {
		try {
			user.setUser_type(4);
			user.setUser_registertime(new Date());
			user.setUser_head("/upload/default.jpg");
			userService.add(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/sysadmin/sysadmin_sysadmin";
	}

	/**
	 * 更新小区管理员信息
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/sysadmin/updateSysadmin", method = { RequestMethod.POST })
	public String updateSysadmin(HttpServletRequest request,
			@ModelAttribute("command") @Valid User user) {
		try {
			userService.ChangeUserInfor(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/sysadmin/sysadmin_sysadmin";
	}

	@RequestMapping(value = "/sysadmin/{sysadmin_id}/deleteSysadmin", method = { RequestMethod.GET })
	public String deleteSysadmin(HttpServletRequest request,
			@PathVariable int sysadmin_id) {
		try {
			User user = new User();
			user.setUser_id(sysadmin_id);
			userService.delete(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/sysadmin/sysadmin_sysadmin";
	}

	/**
	 * 系统管理员上传头像的功能
	 * 
	 * @param request
	 * @param file
	 * @return
	 * 
	 */
	@RequestMapping(value = "/sysadmin/uploadPicture", method = { RequestMethod.POST })
	public String uploadPicture(HttpServletRequest request,
			HttpServletResponse response, @RequestParam MultipartFile file) {

		HttpSession session = request.getSession();
		if (session.getAttribute("admin") != null) {
			UserVo vo = (UserVo) session.getAttribute("admin");
			vo.getUser().setUser_head(
					UploadPicture.uploadHead(request, file, vo.getUser()
							.getUser_head()));
			String imagePath = " 0`" + request.getContextPath()
					+ vo.getUser().getUser_head();
			userService.update(vo.getUser());
			try {
				PrintWriter out = response.getWriter();
				out.print(imagePath);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	/********************************************************************************************/

	/**
	 * 跳转用
	 */
	@RequestMapping(value = "/sysadmin/jumping", method = { RequestMethod.GET })
	public String jumping() {

		return "sysadmin/addAdmin";
	}

	/**
	 * 显示小区列表
	 * 
	 * @return modelAndView 模型视图
	 */
	/*
	 * @RequestMapping(value = "/sysadmin/showCommunitylist", method =
	 * {RequestMethod.GET}) public ModelAndView showCommunitylist() {
	 * List<Community> communitys = systemManageService.getAllCommunity();
	 * ModelAndView modelAndView = new ModelAndView();
	 * modelAndView.addObject("communitys",communitys);
	 * modelAndView.setViewName("/sysadmin/communityList"); return modelAndView;
	 * }
	 */

	/**
	 * 显示小区具体信息
	 * 
	 * @param request
	 *            用户请求
	 * @param id
	 *            小区id
	 * @return
	 */
	@RequestMapping(value = "/sysadmin/{community_id}/showCommunity", method = { RequestMethod.GET })
	public String showCommunity(@PathVariable Integer community_id,
			HttpServletRequest request) {

		request.setAttribute("community", systemManageService.get(community_id));

		return "/sysadmin/updateCommunity";
	}

	/**
	 * 建议处理
	 */
	@RequestMapping(value = "/sysadmin/dealComplain")
	public String dealComplain(@ModelAttribute("complain") Complains complain) {
		systemManageService.dealComplains(complain);
		return "sysadmin/success";
	}

	/**
	 * 添加管理员
	 */
	@RequestMapping(value = "/sysadmin/addAdmin", method = { RequestMethod.POST })
	public String addAdmin(User admin) {
		systemManageService.addAdmin(admin);
		return "sysadmin/success";
	}

	/**
	 * 设置通用属性
	 * 
	 * @param model
	 */
	private void setCommonData(Model model) {
		// 设置通用属性
	}

}
