package com.laifu.module.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;

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

import com.laifu.common.Constants;
import com.laifu.common.pagination.Page;
import com.laifu.common.utils.TimeTransform;
import com.laifu.common.utils.UploadPicture;
import com.laifu.module.entity.Community;
import com.laifu.module.entity.Complains;
import com.laifu.module.entity.Financial;
import com.laifu.module.entity.Notify;
import com.laifu.module.entity.Payment;
import com.laifu.module.entity.Paymenttype;
import com.laifu.module.entity.User;
import com.laifu.module.service.PropertyService;
import com.laifu.module.service.UserService;
import com.laifu.module.vo.FinancialVo;
import com.laifu.module.vo.HouseVo;
import com.laifu.module.vo.PaymentVo;
import com.laifu.module.vo.PaymentsVo;
import com.laifu.module.vo.RepairVo;
import com.laifu.module.vo.UserVo;

@Controller
public class PropertyController {

	@Autowired
	@Qualifier("PropertyService")
	private PropertyService propertyService;
	@Autowired
	@Qualifier("UserService")
	private UserService userService;

	/**
	 * 进入费用添加页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/property/paymenttype/paymenttype_add", method = { RequestMethod.GET })
	public String paymenttype_add(HttpServletRequest request,
			HttpServletResponse response) {
		return "/property/paymenttype/paymenttype_add";
	}

	/**
	 * 添加缴费类型
	 * 
	 * @param request
	 * @param response
	 * @param paymenttype
	 * @return
	 */
	@RequestMapping(value = "/property/addpaymenttype", method = { RequestMethod.POST })
	public String addpaymenttype(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("command") @Valid Paymenttype paymenttype) {
		try {
			propertyService.addPaymenttype(paymenttype);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/property/property_paymenttype";
	}

	/**
	 * 更新缴费类型
	 * 
	 * @param request
	 * @param response
	 * @param paymenttype
	 * @return
	 */
	@RequestMapping(value = "/property/updatepaymenttype", method = { RequestMethod.POST })
	public String updatepaymenttype(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("command") @Valid Paymenttype paymenttype) {
		try {
			Paymenttype p = propertyService.getPaymenttype(paymenttype
					.getPaymenttype_id());
			p.setPaymenttype_money(paymenttype.getPaymenttype_money());
			propertyService.updatePaymenttype(p);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/property/property_paymenttype";
	}

	/**
	 * 进入详情页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/property/{paymenttype_id}/paymenttype/paymenttype_edit", method = { RequestMethod.GET })
	public String paymenttype_edit(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int paymenttype_id) {
		Paymenttype paymenttype = propertyService
				.getPaymenttype(paymenttype_id);
		request.setAttribute("paymenttype", paymenttype);
		return "/property/paymenttype/paymenttype_edit";
	}

	/**
	 * 进入详情页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/property/{paymenttype_id}/deletePaymenttype", method = { RequestMethod.GET })
	public String deletePaymenttype(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int paymenttype_id) {
		propertyService.deletePaymenttype(paymenttype_id);
		return "redirect:/property/property_paymenttype";
	}

	/**
	 * 进入财务统计页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/property/property_caiwutongji2", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String user_caiwutongji2(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<FinancialVo> list = new ArrayList<FinancialVo>();
			String hql = "from Paymenttype";
			List<Paymenttype> typeList = propertyService.getPaymenttypeList();

			for (int i = 0; i < typeList.size(); i++) {
				hql = "select sum(payment_units) from Payment where payment_type="
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
		return "/property/property_caiwutongji2";
	}

	/**
	 * 进入财务统计的页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/property/property_caiwutongji", method = {
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
		return "/property/property_caiwutongji";
	}

	/**
	 * 判断截至时间是否晚于当前时间
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/property/isLaterNow", method = {
			RequestMethod.GET, RequestMethod.POST })
	public void isLaterNow(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startTime = format.parse(request
					.getParameter("payment_deadtime"));
			String code = "1";
			if (date.after(startTime)) {
				code = "0";
			}
			response.getWriter().write(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断开始时间是否晚于结束时间
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/property/isLaterTime", method = {
			RequestMethod.GET, RequestMethod.POST })
	public void isLaterTime(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startTime = format.parse(request
					.getParameter("payment_starttime"));
			Date endTime = format
					.parse(request.getParameter("payment_endtime"));
			String code = "1";
			if (startTime.after(endTime)) {
				code = "0";
			}
			response.getWriter().write(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新用户
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/property/updateUser", method = { RequestMethod.POST })
	public String updateUser(HttpServletRequest request,
			@ModelAttribute("command") @Valid User user) {
		try {
			String hql = "update User set user_password='"
					+ user.getUser_password() + "', user_nickname='"
					+ user.getUser_nickname() + "', user_email='"
					+ user.getUser_email() + "' where user_id="
					+ user.getUser_id();
			propertyService.updateUser(hql);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/property/comadmin_user";
	}

	/**
	 * 添加业主
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/property/addUser", method = { RequestMethod.POST })
	public String addUser(HttpServletRequest request,
			@ModelAttribute("command") @Valid User user) {
		try {
			user.setUser_type(1);
			user.setUser_registertime(new Date());
			user.setUser_head("/upload/default.jpg");
			propertyService.addUser(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/property/comadmin_user";
	}

	/**
	 * 审核用户
	 * 
	 * @param request
	 * @param user_id
	 * @param checkstate
	 * @return
	 */
	@RequestMapping(value = "/property/user/{user_id}/{checkstate}/usercheck", method = { RequestMethod.GET })
	public String checkUserState(HttpServletRequest request,
			@PathVariable int user_id, @PathVariable int checkstate) {
		User user = userService.get(user_id);
		UserVo vo = ((UserVo) request.getSession().getAttribute("admin"));
		user.setUser_approver(vo.getUser().getUser_realname());
		user.setUser_approvaltime(new Date());
		user.setUser_checkstate(checkstate);
		userService.update(user);
		return "redirect:/property/comadmin_user";
	}

	/**
	 * 更新小区信息
	 * 
	 * @param request
	 * @param community
	 * @param community_id
	 * @return
	 */
	@RequestMapping(value = "/property/{community_id}/updateCommunity", method = { RequestMethod.POST })
	public String updateCommunity(HttpServletRequest request,
			@ModelAttribute("command") @Valid Community community,
			@PathVariable int community_id) {
		try {
			community.setCommunity_id(community_id);
			propertyService.updateCommunity(community);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/property/" + community_id
				+ "/community/community_detail";
	}

	/**
	 * 进行删除业主的操作
	 * 
	 * @param request
	 * @param user_id
	 * @return
	 */
	@RequestMapping(value = "/property/{user_id}/deleteUser", method = { RequestMethod.GET })
	public String deleteUser(HttpServletRequest request,
			@PathVariable int user_id) {
		try {
			propertyService.deleteUser(user_id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/property/comadmin_user";
	}

	/**
	 * 进入房屋管理页面
	 * 
	 * @param request
	 * @param user_id
	 * @return
	 */
	@RequestMapping(value = "/property/property_house", method = { RequestMethod.GET })
	public String property_house(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
			String hql = "from House as h order by house_id desc";
			Page<HouseVo> page = propertyService.HouseVo_listAll(hql, pn, 10);
			request.setAttribute("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/property/property_house";
	}

	/**
	 * 进入费用类型管理页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/property/property_paymenttype", method = { RequestMethod.GET })
	public String property_paymenttype(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
			String hql = "from Paymenttype order by paymenttype_id desc";
			Page<Paymenttype> page = propertyService.paymenttype_listAll(hql,
					pn, 10);
			request.setAttribute("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/property/property_paymenttype";
	}

	/**
	 * 
	 * @Title: paymentcontent_exceladd
	 * @Description: 批量导入excel
	 * @param @param file
	 * @param @param request
	 * @param @param model
	 * @param @return
	 * @param @throws IOException 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/property/property_paymentcontent/exceladd", method = { RequestMethod.POST })
	public String paymentcontent_exceladd(@RequestParam MultipartFile file,
			HttpServletRequest request, Model model) throws IOException {
		System.out.println(file.getOriginalFilename());
		if (file.getSize() != 0) {
			try {
				propertyService.addSample(file, model);
				System.out.println(1234);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(123);
			}
			request.getSession().setAttribute("message",
					"<script>alert('导入成功');</script>");
		}
		return "redirect:/property/property_paymentcontent";

	}

	/**
	 * 批量导出到excel
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/property/property_paymentcontent/excelInput", method = { RequestMethod.GET })
	public String paymentcontent_excelIpnut(HttpServletRequest request)
			throws Exception {
		List<Payment> list = propertyService.getPaymentList();
		propertyService.exportExcel();
		request.getSession().setAttribute("message",
				"<script>alert('导出成功');</script>");
		return "redirect:/property/property_paymentcontent";
	}

	/**
	 * 进入业主详情页面
	 * 
	 * @param request
	 * @param user_id
	 * @return
	 */
	@RequestMapping(value = "/property/user/{user_id}/user_detail", method = { RequestMethod.GET })
	public String gotoUser_detail(HttpServletRequest request,
			@PathVariable int user_id) {
		try {
			User user = propertyService.getUser(user_id);
			request.setAttribute("user", user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/property/user/user_detail";
	}

	/**
	 * 进入业主信息编辑页面
	 * 
	 * @param request
	 * @param user_id
	 * @return
	 */
	@RequestMapping(value = "/property/user/{user_id}/user_edit", method = { RequestMethod.GET })
	public String gotoUser_edit(HttpServletRequest request,
			@PathVariable int user_id) {
		try {
			User user = propertyService.getUser(user_id);
			request.setAttribute("user", user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/property/user/user_edit";
	}

	/**
	 * 进入添加业主的页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/property/user/user_add", method = { RequestMethod.GET })
	public String gotoUser_add(HttpServletRequest request) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/property/user/user_add";
	}

	/**
	 * 进入业主管理
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/property/comadmin_user", method = { RequestMethod.GET })
	public String gotoComadmin_user(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
			String hql = "from User where user_type=1 order by user_id desc";
			String user_checkstate = request.getParameter("user_checkstate");
			if (user_checkstate != null) {
				if (user_checkstate.equals("none"))
					hql = "from User where user_type=1 and user_card=null order by user_id desc";
				else
					hql = "from User where user_type=1 and user_card!=null and user_checkstate="
							+ user_checkstate + " order by user_id desc";
				request.setAttribute("user_checkstate", user_checkstate);
			}
			Page<User> page = userService.listAll(hql, pn, 10);
			request.setAttribute("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/property/comadmin_user";
	}

	/**
	 * 进入小区管理页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/property/sysadmin_community", method = { RequestMethod.GET })
	public String gotoCommunity(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
			String hql = "from Community order by community_id desc";
			Page<Community> page = propertyService.community_listAll(hql, pn,
					10);
			request.setAttribute("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/property/sysadmin_community";
	}

	@RequestMapping(value = "/property/{community_id}/community/community_detail", method = { RequestMethod.GET })
	public String gotoCommunity_detail(HttpServletRequest request,
			@PathVariable int community_id) {
		try {
			Community community = propertyService.getCommunity(community_id);
			request.setAttribute("community", community);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/property/community/community_detail";
	}

	@RequestMapping(value = "/property/{community_id}/community/community_edit", method = { RequestMethod.GET })
	public String gotoCommunity_edit(HttpServletRequest request,
			@PathVariable int community_id) {
		try {
			Community community = propertyService.getCommunity(community_id);
			request.setAttribute("community", community);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/property/community/community_edit";
	}

	/**************************************************************************************************************/

	/**
	 * 跳转到物业管理首页
	 * 
	 * @return 物业管理首页
	 */
	@RequestMapping(value = "/property/property", method = { RequestMethod.GET })
	public String gotoIndex(HttpServletRequest request) {
		System.out.println("gotoindex");
		UserVo vo = (UserVo) request.getSession().getAttribute("admin");
		if (vo == null)
			return "redirect:/sysadmin/sysadmin_login";
		return "/property/property";
	}

	@RequestMapping(value = "/paymentcontent/payment_edit", method = { RequestMethod.GET })
	public String gotoPayment_edit(HttpServletRequest request) {
		return "/property/paymentcontent/payment_edit";
	}

	@RequestMapping(value = "/notify/notify_add", method = { RequestMethod.GET })
	public String gotoNotify_add(HttpServletRequest request) {
		return "/property/notify/notify_add";
	}

	/**
	 * 跳转到费用添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/paymentcontent/payment_add", method = { RequestMethod.GET })
	public String gotoPayment_add(HttpServletRequest request) {
		/**
		 * 1、从数据库拿到所有费用的类型 2、然后显示在前台
		 */
		List<Paymenttype> paymenttypelist = propertyService
				.getPaymenttypeList();
		request.setAttribute("paymenttypelist", paymenttypelist);

		return "/property/paymentcontent/payment_add";
	}

	/**
	 * 跳转到建议列表页面
	 * 
	 * @return 建议列表
	 */
	@RequestMapping(value = "/property/property_complains", method = { RequestMethod.GET })
	public String gotoComplasins(HttpServletRequest request) {
		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hql = "from Complains order by complains_id desc";
		String complains_state = request.getParameter("complains_state");
		if (complains_state != null) {
			hql = "from Complains where complains_state=" + complains_state
					+ " order by complains_id desc";
			request.setAttribute("complains_state", complains_state);
		}
		Page<Complains> page = propertyService.complains_listAll(hql, pn, 10);
		request.setAttribute("page", page);
		return "/property/property_complains";
	}

	/**
	 * tiao zhuan dao cai wu tong ji ye mian
	 * 
	 * @param request
	 * @param financial_id
	 * @return
	 */
	@RequestMapping(value = "/property/{financial_id}/financial/financial_edit", method = { RequestMethod.GET })
	public String gotoFinancial_edit(HttpServletRequest request,
			@PathVariable int financial_id) {

		Financial finacial = propertyService.getFinancial(financial_id);
		request.setAttribute("finacial", finacial);
		return "/property/financial/financial_edit";
	}

	/**
	 * xiu gai cai wu tong ji
	 * 
	 * @param request
	 * @param financial
	 * @return
	 */
	@RequestMapping(value = "/property/{financial_id}/updateFinancial", method = { RequestMethod.POST })
	public String updateFinancial(HttpServletRequest request,
			Financial financial, String datetime) {
		UserVo uservo = (UserVo) request.getSession().getAttribute("admin");
		int userid = uservo.getUser().getUser_id();
		financial.setFinancial_userid(userid);
		try {
			Date date = TimeTransform.StringToDate(datetime);
			financial.setFinancial_datetime(date);
			propertyService.updateFinancial(financial);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:/property/property_financial";
	}

	@RequestMapping(value = "/property/addNotify", method = { RequestMethod.POST })
	public String addNotify(HttpServletRequest request,
			@ModelAttribute("command") @Valid Notify notify) {
		notify.setNotify_datetime(new Date());
		notify.setNotify_userid(((UserVo) request.getSession().getAttribute(
				"admin")).getUser().getUser_id());
		propertyService.addNotify(notify);
		return "redirect:/property/property_notify";
	}

	/**
	 * 跳转到物业信息页面
	 * 
	 * @return 物业信息
	 */
	@RequestMapping(value = "/property/property_infor", method = { RequestMethod.GET })
	public String gotoInfor() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/property/property_infor";
	}

	/**
	 * 跳转到物业缴费页面
	 * 
	 * @return 物业缴费页面
	 */
	@RequestMapping(value = "/property/property_payment", method = { RequestMethod.GET })
	public String gotoPayment(HttpServletRequest request) {
		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hql = "from Payment as p, Paymenttype as pty, User as u where p.payment_type=pty.paymenttype_id and p.payment_userid=u.user_id order by payment_id desc";
		Page<PaymentsVo> page = propertyService.paymentVo_listAll(hql, pn, 10);
		request.setAttribute("page", page);
		return "/property/property_payment";
	}

	@RequestMapping(value = "/property/property_paymentcontent", method = { RequestMethod.GET })
	public String gotoPaymentcontent(HttpServletRequest request) {
		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hql = "from Payment as p, Paymenttype as pty, User as u where p.payment_type=pty.paymenttype_id and p.payment_userid=u.user_id order by payment_id desc";
		String payment_paystate = request.getParameter("payment_paystate");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date());
		if (request.getParameter("payment_paystate") != null) {
			if (payment_paystate.equals("2"))
				hql = "from Payment as p, Paymenttype as pty, User as u where p.payment_paystate=0"
						+ " and p.payment_deadtime<'"
						+ date
						+ "' and p.payment_type=pty.paymenttype_id and p.payment_userid=u.user_id order by payment_id desc";
			else
				hql = "from Payment as p, Paymenttype as pty, User as u where p.payment_paystate="
						+ payment_paystate
						+ " and p.payment_type=pty.paymenttype_id and p.payment_userid=u.user_id order by payment_id desc";
			request.setAttribute("payment_paystate", payment_paystate);

		}

		if (request.getSession().getAttribute("message") != null) {
			request.setAttribute("message",
					request.getSession().getAttribute("message"));
			request.getSession().removeAttribute("message");
		}

		Page<PaymentsVo> page = propertyService.paymentVo_listAll(hql, pn, 10);
		request.setAttribute("page", page);
		return "/property/property_paymentcontent";
	}

	/**
	 * 跳转到维修列表页面
	 * 
	 * @return 维修列表页面
	 */
	@RequestMapping(value = "/property/property_repair", method = { RequestMethod.GET })
	public String gotoRapair(HttpServletRequest request) {
		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hql = "from Repair as r, User as u, House as h where u.user_id = r.repair_userid and u.user_house=h.house_id order by repair_id desc";
		String repair_state = request.getParameter("repair_state");
		if (repair_state != null) {
			hql = "from Repair as r, User as u, House as h where r.repair_state="
					+ repair_state
					+ " and u.user_id = r.repair_userid and u.user_house=h.house_id order by repair_id desc";
			request.setAttribute("repair_state", repair_state);
		}
		Page<RepairVo> page = propertyService.repairVo_listAll(hql, pn, 10);
		request.setAttribute("page", page);
		return "/property/property_repair";
	}

	/**
	 * 
	 * @param request
	 * @param repair_id
	 *            维修id
	 * @return 维修详情页面
	 */
	@RequestMapping(value = "/property/{repair_id}/repair_detail", method = { RequestMethod.GET })
	public String gotoRapairDetail(HttpServletRequest request,
			@PathVariable int repair_id) {
		/**
		 * 1、根据前台传过来的id查找维修详情 2、将查到的数据显示在前端
		 */
		try {
			RepairVo repairdetail = propertyService.getRepairDetail(repair_id);
			request.setAttribute("repairdetail", repairdetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/property/repair/repair_detail";
	}

	/**
	 * 跳转到日常通知列表页面
	 * 
	 * @return 日常通知列表页面
	 */
	@RequestMapping(value = "/property/property_notify", method = { RequestMethod.GET })
	public String gotoNotify(HttpServletRequest request) {
		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
		String hql = "from Notify order by notify_id desc";
		Page<Notify> page = propertyService.notify_listAll(hql, pn, 10);
		request.setAttribute("page", page);
		return "/property/property_notify";
	}

	/**
	 * 
	 * @param request
	 * @param repair_id
	 *            维修id
	 * @return 日常通知详情页面
	 */
	@RequestMapping(value = "/property/{notify_id}/notify_detail", method = { RequestMethod.GET })
	public String gotoNotifyDetail(HttpServletRequest request,
			@PathVariable int notify_id) {
		Notify notify = propertyService.getNotify(notify_id);
		request.setAttribute("notify", notify);
		return "/property/notify/notify_detail";
	}

	/**
	 * 更新维修信息
	 * 
	 * @param request
	 * @param repair_id
	 *            维修id
	 * @return 维修列表视图
	 */
	@RequestMapping(value = "/property/{repair_id}/{state}/updateRepair", method = { RequestMethod.GET })
	public String updateRepair(HttpServletRequest request,
			@PathVariable int repair_id, @PathVariable int state) {
		propertyService.updateRepair(repair_id, state);
		return "redirect:/property/property_repair";
	}

	/**
	 * 删除通知信息
	 * 
	 * @param request
	 * @param notify_id
	 * @return
	 */
	@RequestMapping(value = "/property/{notify_id}/notify_delete", method = { RequestMethod.GET })
	public String deleteNotify(HttpServletRequest request,
			@PathVariable Integer notify_id) {
		Notify notify = new Notify();
		notify.setNotify_id(notify_id);
		propertyService.deleteNotify(notify);
		return "redirect:/property/property_notify";
	}

	/**
	 * 跳转到通知修改页面
	 * 
	 * @param request
	 * @param notify_id
	 * @return
	 */
	@RequestMapping(value = "/property/{notify_id}/gotonotify_edit", method = { RequestMethod.GET })
	public String gotoEdit(HttpServletRequest request,
			@PathVariable Integer notify_id) {
		Notify notify = propertyService.getNotify(notify_id);
		request.setAttribute("notify", notify);
		return "/property/notify/notify_edit";
	}

	/**
	 * 更新通知内容
	 * 
	 * @param request
	 * @param notify
	 * @return
	 */
	@RequestMapping(value = "/property/{notify_id}/update_notify", method = { RequestMethod.POST })
	public String updateNofity(HttpServletRequest request,
			@ModelAttribute("notify") Notify notify) {

		Notify newNotify = propertyService.getNotify(notify.getNotify_id());
		newNotify.setNotify_content(notify.getNotify_content());
		newNotify.setNotify_titile(notify.getNotify_titile());
		propertyService.updateNotify(newNotify);
		return "redirect:/property/property_notify";
	}

	/**
	 * 跳转到财务通知列表页面
	 * 
	 * @return 财务通知列表页面
	 */
	@RequestMapping(value = "/property/property_financial", method = { RequestMethod.GET })
	public String gotoFinancial(HttpServletRequest request) {
		try {
			List<FinancialVo> list = new ArrayList<FinancialVo>();

			Calendar cal = Calendar.getInstance();
			int year = cal.get(cal.YEAR);
			int month = cal.get(cal.MONTH) + 1;
			int stMonth = month - 6;
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
			for (int i = 0; i < 5; i++) {
				String stsql = stYear + "-" + stMonth + "-1";
				String nextsql = nextYear + "-" + nextMonth + "-1";
				String hql = "select count(*) from Payment where payment_endtime>='"
						+ stsql + "' and payment_endtime<'" + nextsql + "'";
				int count = propertyService.getPaymentCount(hql);

				list.add(new FinancialVo(stYear + "-" + stMonth + " "
						+ nextYear + "-" + nextMonth, count, "#FF0F00"));
				System.out
						.println(list.get(i).getName() + " "
								+ list.get(i).getValue() + " "
								+ list.get(i).getColor());

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

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/property/property_financial";
	}

	/**
	 * 删除财务统计信息
	 * 
	 * @param request
	 * @param financial_id
	 * @return
	 */

	@RequestMapping(value = "/property/{financial_id}/financial_delete", method = { RequestMethod.GET })
	public String deleteFinancial(HttpServletRequest request,
			@PathVariable Integer financial_id) {
		Financial financial = new Financial();
		financial.setFinancial_id(financial_id);
		propertyService.deleteFinancial(financial);
		return "redirect:/property/property_financial";
	}

	/**
	 * 跳转到添加页面
	 */
	@RequestMapping(value = "/property/gotofinancial_add", method = { RequestMethod.GET })
	public String gotoFinancialAdd() {
		return "/property/financial/financial_add";
	}

	@RequestMapping(value = "/property/logout", method = { RequestMethod.GET })
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null)
			session.removeAttribute("admin");
		return "redirect:/sysadmin/sysadmin_login";
	}

	/**
	 * 添加财务统计
	 * 
	 * @param request
	 * @param financial
	 * @return
	 */
	@RequestMapping(value = "/property/addFinancial", method = { RequestMethod.POST })
	public String addFinancial(HttpServletRequest request,
			@ModelAttribute("command") @Valid Financial financial) {
		// System.out.println(financial.getFinancial_funds());
		String financial_datetime = request.getParameter("datetime");
		UserVo uservo = (UserVo) request.getSession().getAttribute("admin");
		int userid = uservo.getUser().getUser_id();
		propertyService.addFinancial(financial, financial_datetime, userid);
		return "redirect:/property/property_financial";
	}

	/**
	 * 物业管理员上传头像的功能
	 * 
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/property/uploadPicture", method = { RequestMethod.POST })
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

	/**
	 * 添加费用
	 * 
	 * @param request
	 * @param paymentvo
	 * @return
	 */
	@RequestMapping(value = "/property/addpayment", method = { RequestMethod.POST })
	public String addPayment(HttpServletRequest request, PaymentVo paymentvo,
			Paymenttype paymenttype) {
		try {
			paymentvo.setPaymenttype(paymenttype);
			propertyService.addPayment(request, paymentvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/property/property_paymentcontent";
	}

	/**
	 * 跳转到修改费用页面
	 * 
	 * @param request
	 * @param paymentvo
	 * @param paymenttype
	 * @return
	 */
	@RequestMapping(value = "/paymentcontent/{payment_id}/payment_edit", method = { RequestMethod.GET })
	public String gotoEditPayment(HttpServletRequest request,
			@PathVariable Integer payment_id) {
		try {
			PaymentVo paymentvo = new PaymentVo();
			Payment payment = propertyService.getPayment(payment_id);
			User user = propertyService.getUser(payment.getPayment_userid());
			List<Paymenttype> paymenttypelist = propertyService
					.getPaymenttypeList();
			Paymenttype paymenttype = propertyService.getPaymentType(payment
					.getPayment_type());
			Date starttime = payment.getPayment_starttime();
			Date endtime = payment.getPayment_endtime();
			Date deadtime = payment.getPayment_deadtime();
			paymentvo.setpayment_deadtime(TimeTransform.DateToString(deadtime));
			paymentvo.setPayment_endtime(TimeTransform.DateToString(endtime));
			paymentvo.setPayment_starttime(TimeTransform
					.DateToString(starttime));
			paymentvo.setPaymenttype(paymenttype);
			paymentvo.setPayment_units(payment.getPayment_units());
			paymentvo.setPhone_number(user.getUser_account());
			paymentvo.setPaymenttypelist(paymenttypelist);
			paymentvo.setPayment_id(payment_id);
			request.setAttribute("paymentvo", paymentvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/property/paymentcontent/payment_edit";
	}

	/**
	 * 修改费用
	 * 
	 * @param request
	 * @param paymentvo
	 * @return
	 */
	@RequestMapping(value = "/property/updatepayment", method = { RequestMethod.POST })
	public String updatePayment(HttpServletRequest request,
			PaymentVo paymentvo, Integer paymenttypeid) {
		try {
			System.out.println(paymentvo.getPayment_id());
			propertyService.updatePayment(request, paymentvo, paymenttypeid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/property/property_paymentcontent";
	}

	/**
	 * 进入密码修改的页面
	 * 
	 * @param request
	 *            请求对象
	 * @return 密码修改的页面
	 */
	@RequestMapping(value = "/property/information/password_update", method = { RequestMethod.GET })
	public String gotoPasswordupdate(HttpServletRequest request) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/property/information/password_update";
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/property/updatePassword", method = { RequestMethod.POST })
	public String updatePassword(HttpServletRequest request,
			@ModelAttribute("command") @Valid User user) {
		try {
			UserVo vo = ((UserVo) request.getSession().getAttribute("admin"));
			vo.getUser().setUser_password(user.getUser_password());
			userService.updatePassword(vo.getUser());
			request.getSession().setAttribute("admin", vo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/property/property_infor";
	}

	/**
	 * 删除费用类型
	 */
	@RequestMapping(value = "/paymenttype/{paymenttype_id}/paymenttype_delete", method = { RequestMethod.GET })
	public void deletePaymenttype(HttpServletRequest request,
			@PathVariable Integer paymenttype_id) {
		propertyService.deletePaymenttype(paymenttype_id);
	}

	// @RequestMapping( value="/property/index", method={RequestMethod.GET} )
	// public String GoToIndex(HttpServletRequest request) {
	// return "/property/index";
	// }
	//
	// @RequestMapping( value="/property/manager_index",
	// method={RequestMethod.GET} )
	// public String GoToManagerIndex(HttpServletRequest request) {
	//
	// List<Payment> paymentList = propertyService.getPaymentList();
	// List<Notify> notifyList = propertyService.getNotifyList();
	// request.setAttribute("paymentList", paymentList);
	// request.setAttribute("notifyList", notifyList);
	//
	// return "/property/manager_index";
	// }
	//
	// @RequestMapping( value="/property/addPayment",
	// method={RequestMethod.POST} )
	// public String addPayment(HttpServletRequest request,
	// @ModelAttribute("command") @Valid Payment payment) {
	//
	// SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//
	// try {
	// payment.setPayment_starttime(formater.parse(request.getParameter("test_payment_starttime")));
	// payment.setPayment_endtime(formater.parse(request.getParameter("test_payment_endtime")));
	// payment.setPayment_deadtime(formater.parse(request.getParameter("test_payment_deadtime")));
	// payment.setPayment_complettime(request.getParameter("test_payment_complettime")==null?null:formater.parse(request.getParameter("test_payment_complettime")));
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// propertyService.addPayment(payment);
	//
	// return "redirect:/property/manager_index";
	// }
	//
	// @RequestMapping( value="/property/{payment_id}/deletePayment",
	// method={RequestMethod.GET} )
	// public String deletePayment(HttpServletRequest request, @PathVariable
	// Integer payment_id) {
	//
	// Payment payment = new Payment();
	// payment.setPayment_id(payment_id);
	// propertyService.deletePayment(payment);
	//
	// return "redirect:/property/manager_index";
	// }
	//

	//
	// @RequestMapping( value="/property/updateNotify",
	// method={RequestMethod.POST} )
	// public String updateNotify(HttpServletRequest request,
	// @ModelAttribute("command") @Valid Notify notify) {
	//
	// SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// try {
	// notify.setNotify_datetime(formater.parse(request.getParameter("test_notify_datetime")));
	// propertyService.updateNotify(notify);
	//
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return "redirect:/property/manager_index";
	// }
	//
	//
	// @RequestMapping( value="/property/addRepair", method={RequestMethod.POST}
	// )
	// public String addRepair(HttpServletRequest request,
	// @ModelAttribute("command") @Valid Repair repair) {
	// propertyService.addRepair(repair);
	// return "redirect:/property/manager_index";
	// }
	//
	// @RequestMapping( value="/property/updateRepair",
	// method={RequestMethod.POST} )
	// public String updateRepair(HttpServletRequest request,
	// @ModelAttribute("command") @Valid Repair repair) {
	// propertyService.updateRepair(repair);
	// return "redirect:/property/manager_index";
	// }
	//
	// @RequestMapping( value="/property{repair_id}/deleteRepair",
	// method={RequestMethod.GET} )
	// public String deleteRepair(HttpServletRequest request, @PathVariable
	// Integer repair_id) {
	// Repair repair = new Repair();
	// repair.setRepair_id(repair_id);
	// propertyService.deleteRepair(repair);
	// return "redirect:/property/manager_index";
	// }
	//
	// @RequestMapping( value="/property/checkUser", method={RequestMethod.POST}
	// )
	// public String checkUser(HttpServletRequest request,
	// @ModelAttribute("command") @Valid User user) {
	// propertyService.updateUser(user);
	// return "redirect:/property/manager_index";
	// }
	//

	//
	// @RequestMapping( value="/property/updateFinancial",
	// method={RequestMethod.POST} )
	// public String updateFinancial(HttpServletRequest request,
	// @ModelAttribute("command") @Valid Financial financial) {
	// propertyService.updateFinancial(financial);
	// return "redirect:/property/manager_index";
	// }
	//
	// @RequestMapping( value="/property{financial_id}/deleteFinancial",
	// method={RequestMethod.GET} )
	// public String deleteFinancial(HttpServletRequest request, @PathVariable
	// Integer financial_id) {
	// Financial financial = new Financial();
	// financial.setFinancial_id(financial_id);
	// propertyService.deleteFinancial(financial);
	// return "redirect:/property/manager_index";
	// }
}
