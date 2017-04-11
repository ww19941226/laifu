package com.laifu.module.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.laifu.common.Constants;
import com.laifu.common.exception.UserException;
import com.laifu.common.utils.MD5;
import com.laifu.common.utils.UploadPicture;
import com.laifu.module.entity.House;
import com.laifu.module.entity.Paymenttype;
import com.laifu.module.entity.User;
import com.laifu.module.service.PropertyService;
import com.laifu.module.service.UserService;
import com.laifu.module.vo.FinancialVo;
import com.laifu.module.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	@Qualifier("UserService")
	private UserService userService;

	@Autowired
	@Qualifier("PropertyService")
	private PropertyService propertyService;

	/*************************************************************************************************/

	/**
	 * 进入财务统计页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/user/user_caiwutongji2", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String user_caiwutongji2(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<FinancialVo> list = new ArrayList<FinancialVo>();
			String hql = "from Paymenttype";
			List<Paymenttype> typeList = propertyService.getPaymenttypeList();
			User user = (User) request.getSession().getAttribute("user");

			for (int i = 0; i < typeList.size(); i++) {
				hql = "select sum(payment_units) from Payment where payment_userid="
						+ user.getUser_id()
						+ " and payment_type="
						+ typeList.get(i).getPaymenttype_id();
				double sum = propertyService.getPaymentSum(hql);
				System.out.println(sum);
				list.add(new FinancialVo(typeList.get(i).getPaymenttype_name(),
						sum, Constants.color[i]));
			}

			JSONArray jsa = JSONArray.fromObject(list);
			request.setAttribute("chartDatap", jsa.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/user/user_caiwutongji2";
	}

	/**
	 * 进入财务统计的页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/user/user_caiwutongji", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String user_caiwutongji(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<FinancialVo> list = new ArrayList<FinancialVo>();

			Calendar cal = Calendar.getInstance();
			int year = cal.get(cal.YEAR);
			int month = cal.get(cal.MONTH) + 1;
			int stMonth = month - 12;
			int stYear = year;
			if (stMonth < 1) {
				stMonth += 12;
				stYear--;
			}
			int nextMonth = stMonth + 1;
			int nextYear = stYear;
			if (nextMonth < 1) {
				nextMonth += 12;
				nextYear--;
			}
			for (int i = 0; i < 12; i++) {
				String stsql = stYear + "-" + stMonth + "-1";
				String nextsql = nextYear + "-" + nextMonth + "-1";
				String hql = "select sum(payment_units) from Payment where payment_endtime>='"
						+ stsql + "' and payment_endtime<'" + nextsql + "'";
				Double result = propertyService.getPaymentSum(hql);
				double sum = result == null ? 0 : result;

				list.add(new FinancialVo(stYear + "/" + stMonth, sum,
						Constants.color[i]));
				// System.out.println(list.get(i).getName() + " " +
				// list.get(i).getValue() + " " + list.get(i).getColor());

				stMonth++;
				if (stMonth > 12) {
					stMonth = 1;
					stYear++;
				}
				nextMonth++;
				if (nextMonth > 12) {
					nextMonth = 1;
					nextYear++;
				}
			}

			JSONArray jsa = JSONArray.fromObject(list);
			request.setAttribute("chartData", jsa.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/user/user_caiwutongji";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/gotoFilter", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void gotoFilter(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HttpServletRequest req = (HttpServletRequest) request
					.getAttribute("request");
			HttpServletResponse rep = (HttpServletResponse) request
					.getAttribute("response");
			HttpSession session = req.getSession();

			req.setAttribute("filter", "true");
			User user = (User) session.getAttribute("user");
			UserVo admin = (UserVo) session.getAttribute("admin");
			if (user != null) {
				user = userService.get(user.getUser_id());
				session.setAttribute("user", user);
			}
			if (admin != null) {
				admin = userService.getUserVoById(admin.getUser().getUser_id());
				session.setAttribute("admin", admin);
			}
			request.getRequestDispatcher(
					req.getRequestURI().replace(req.getContextPath(), ""))
					.forward(req, rep);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 进入关于我们的界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/about_us", method = { RequestMethod.GET })
	public String about_us() {
		return "/about_us";
	}

	/**
	 * 进入phone版的关于我们
	 * 
	 * @return
	 */
	@RequestMapping(value = "/about_us_phone", method = { RequestMethod.GET })
	public String about_us_phone() {
		return "/about_us_phone";
	}

	/**
	 * 进入小区简介
	 * 
	 * @return
	 */
	@RequestMapping(value = "/introduce", method = { RequestMethod.GET })
	public String introduce() {
		return "/introduce";
	}

	/**
	 * 进入业主注册页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/user_register", method = { RequestMethod.GET })
	public String gotoRegister() {
		return "/user/user_register";
	}

	/**
	 * 进入安全中心页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/userSercurity", method = { RequestMethod.GET })
	public String gotoUserSecurity() {
		return "user/user_security";
	}

	/**
	 * 进入业主登陆页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/user_login", method = { RequestMethod.GET })
	public String gotoLogin() {
		return "/user/user_login";
	}

	/**
	 * 进入APP下载页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/APPdownload", method = { RequestMethod.GET })
	public String gotoAPPdownload() {
		return "/user/APPdownload";
	}

	/**
	 * 跳转到注册成功页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/toturn", method = { RequestMethod.GET })
	public String toturn() {
		return "/user/toturn";
	}

	/*************************************************************************************************/

	/**
	 * 通过手机号判断用户是否存在
	 * 
	 * @param response
	 * @param user
	 */
	@RequestMapping(value = "/user/exit", method = { RequestMethod.POST,
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
	 * 编辑并发送短信
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/user/code", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void Code(HttpServletRequest request, HttpServletResponse response) {
		try {
			String code = userService.SendCode(request
					.getParameter("user_account"));
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			request.getSession().setAttribute("smsCode", code);
			response.getWriter().write(code);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断短信验证码是否正确
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/user/checkCode", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void checkCode(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// System.out.println("hello");
			String smsCode = request.getParameter("code");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			String code;
			String rightCode = (String) request.getSession().getAttribute(
					"smsCode");
			if (smsCode != null && smsCode.length() == 6
					&& smsCode.equals(rightCode))
				code = "1";
			else
				code = "0";
			response.getWriter().write(code);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/register", method = { RequestMethod.POST })
	public String register(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("command") @Valid User user) {
		try {
			MD5 md5 = new MD5();
			user.setUser_password(md5.EncoderByMd5(user.getUser_password()));
			userService.register(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/user/toturn";
	}

	/**
	 * 判断账号密码是否对应
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/checkAccountAndPassword", method = {
			RequestMethod.POST, RequestMethod.GET })
	public void checkAccountAndPassword(HttpServletResponse response,
			@ModelAttribute("command") @Valid User user) {
		try {
			// System.out.println(user.getUser_account());
			// System.out.println(user.getUser_password());
			MD5 md5 = new MD5();
			user.setUser_password(md5.EncoderByMd5(user.getUser_password()));
			User u = userService.login(user, 1);
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

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/login", method = { RequestMethod.POST })
	public String login(HttpServletRequest request,
			@ModelAttribute("command") @Valid User user) {
		try {
			User u = userService.getByAccount(user.getUser_account());
			request.getSession().setAttribute("user", u);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/index";
	}

	/**
	 * 
	 * @Title: updatePsw
	 * @Description: 用户修改密码
	 * @param @param request
	 * @param @param user
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author jianshuo
	 */
	@RequestMapping(value = "/user/updatepsw", method = { RequestMethod.POST })
	public String updatePsw(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String user_password = request.getParameter("user_password");
			User u = (User) request.getSession().getAttribute("user");
			String password = u.getUser_password();
			String newpassword = request.getParameter("newpassword");
			/*
			 * u.setUser_password(newpassword); userService.update(u);
			 */
			if (user_password.equals(password)) {
				u.setUser_password(newpassword);
				userService.update(u);
				return "redirect:/user/toturn";
			}

			/*
			 * return "redirect:/user/user_login";
			 */
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 用户注销
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/logout", method = { RequestMethod.GET })
	public String logout(HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		return "redirect:/user/user_login";
	}

	@RequestMapping(value = "/user/checkHouse", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void checkHouse(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("command") House house) {
		try {
			String code;
			if (!propertyService.exitHouse(house.getHouse_floornumber()))
				code = "0";
			else
				code = "1";
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().write(code);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 确认该楼房是否存在
	 * 
	 * @param request
	 * @param response
	 * @param house
	 */
	@RequestMapping(value = "/user/checkRoom", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void checkRoom(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("command") House house) {
		try {
			House h = propertyService.getRoom(house.getHouse_floornumber(),
					house.getHouse_roomnumber());
			String code;
			if (h == null)
				code = "0";
			else
				code = "1";
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().write(code);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 完善用户信息
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/complete", method = { RequestMethod.POST })
	public String complete(HttpServletRequest request,
			HttpServletResponse response, User user, House house) {
		try {
			User u = (User) request.getSession().getAttribute("user");
			House h = propertyService.getRoom(house.getHouse_floornumber(),
					house.getHouse_roomnumber());
			if (h != null)
				u = userService.complete(u, user, h.getHouse_id());
			request.getSession().setAttribute("user", u);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/user/userInfor";
	}

	/*************************************************************************************************/

	/**
	 * 获取用户列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user", method = { RequestMethod.GET })
	public String list(HttpServletRequest request) {

		request.setAttribute("test", "我的名字是林金全");
		User user = (User) request.getSession().getAttribute("user");
		List<User> list = userService.getList();
		request.setAttribute("userList", list);
		request.setAttribute("name",
				user == null ? "" : ("hello，" + user.getUser_nickname()));
		request.setAttribute("picture",
				user == null ? "upload/th.jpg" : user.getUser_head());

		return "user/list";
	}

	/**
	 * 管理员对用户的更新功能
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/managerUpdate", method = { RequestMethod.POST })
	public String managerUpdate(HttpServletRequest request,
			@ModelAttribute("command") @Valid User user) {

		// userService.update(user);

		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			/*
			 * user.setUser_registertime(formater.parse(request.getParameter(
			 * "test_user_registertime"))); user.setUser_approvaltime(
			 * request.getParameter("test_user_approvaltime")==null ||
			 * request.getParameter("test_user_approvaltime").trim()==""?
			 * null:formater
			 * .parse(request.getParameter("test_user_approvaltime")));
			 * 
			 * 
			 * userService.update(user);
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/user";

	}

	/**
	 * 用户忘记密码的功能
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/forget", method = { RequestMethod.POST })
	public String forget(HttpServletRequest request) throws Exception {

		String user_account = request.getParameter("user_account");
		String user_password = request.getParameter("user_password");
		User user = userService.getByAccount(user_account);
		user.setUser_password(user_password);

		// userService.update(user);

		return "redirect:/user";
	}

	/**
	 * 用户修改个人信息的功能
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/update", method = { RequestMethod.POST })
	public String update(HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");
		user.setUser_password(request.getParameter("user_password"));
		user.setUser_nickname(request.getParameter("user_nickname"));
		user.setUser_email(request.getParameter("user_email"));

		// userService.update(user);

		return "redirect:/user";
	}

	/**
	 * 管理员删除用户的功能
	 * 
	 * @param request
	 * @param user_id
	 * @return
	 * @throws UserException
	 */
	@RequestMapping(value = "/user/{user_id}/delete", method = { RequestMethod.GET })
	public String delete(HttpServletRequest request,
			@PathVariable Integer user_id) throws UserException {

		// userService.delete(user);
		User user = new User();
		user.setUser_id(user_id);
		userService.delete(user);

		return "redirect:/user";

	}

	/**
	 * 用户上传图片的功能
	 * 
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/user/uploadPicture", method = {
			RequestMethod.POST, RequestMethod.GET })
	public void uploadPicture(HttpServletResponse response,
			HttpServletRequest request, @RequestParam MultipartFile file) {
		try {
			HttpSession session = request.getSession();
			if (session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				user.setUser_head(UploadPicture.uploadHead(request, file,
						user.getUser_head()));
				String imagePath = " 0`" + request.getContextPath()
						+ user.getUser_head();
				userService.update(user);
				PrintWriter out = response.getWriter();
				out.print(imagePath);
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 完善个人信息页面
	 */
	@RequestMapping(value = "/user/userInfor", method = { RequestMethod.GET })
	public String gotoInformation(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		int houseid = user.getUser_house();
		House house = userService.getHouse(houseid);
		if (house != null) {
			request.setAttribute("houseFloorNum", house.getHouse_floornumber());
			request.setAttribute("houseRoomNum", house.getHouse_roomnumber());
		} else {
			request.setAttribute("houseFloorNum", "");
			request.setAttribute("houseRoomNum", "");
		}
		return "user/user_infor";
	}

}
