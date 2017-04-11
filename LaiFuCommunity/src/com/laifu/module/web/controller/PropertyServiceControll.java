/**
 * @ClassName: PropertyServiceControll
 * @Description: 物业服务Controller
 * @author ：zepeng
 * @date 2016年9月26日16:35:52
 * 
 */
package com.laifu.module.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.laifu.common.pagination.Page;
import com.laifu.common.utils.PaymentUtil;
import com.laifu.common.utils.TimeTransform;
import com.laifu.module.entity.Complains;
import com.laifu.module.entity.Financial;
import com.laifu.module.entity.Notify;
import com.laifu.module.entity.Notifyvisit;
import com.laifu.module.entity.Payment;
import com.laifu.module.entity.Paymenttype;
import com.laifu.module.entity.Repair;
import com.laifu.module.entity.User;
import com.laifu.module.entity.Usertype;
import com.laifu.module.service.PropertyService;
import com.laifu.module.service.UserService;
import com.laifu.module.vo.ComplainsVo;
import com.laifu.module.vo.PayMoneyVo;
import com.laifu.module.vo.PayVo;
import com.laifu.module.vo.PaymentsVo;
import com.laifu.module.vo.RepairVo;

@Controller
public class PropertyServiceControll {

	@Autowired
	@Qualifier("PropertyService")
	private PropertyService propertyService;
	@Autowired
	@Qualifier("UserService")
	private UserService userService;

	/******************************************************************************************************/

	/**
	 * 判断开始时间是否晚于当前时间
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/user/isLaterNow", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void isLaterNow(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startTime = format.parse(request.getParameter("startTime"));
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
	@RequestMapping(value = "/user/isLaterTime", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void isLaterTime(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startTime = format.parse(request.getParameter("startTime"));
			Date endTime = format.parse(request.getParameter("endTime"));
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
	 * 进入用户中心
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/userCenter", method = { RequestMethod.GET })
	public String userCenter(HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");
		if (user.getUser_checkstate() == 1) {
			int notifyvisit_count = propertyService.getNotifyvisit_count(user
					.getUser_id());
			request.getSession().setAttribute("notifyvisit_count",
					notifyvisit_count);
		}
		return "user/user";
	}

	/**
	 * 进入投诉建议的列表页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/user_complains", method = { RequestMethod.GET })
	public String user_comolains(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
			User user = (User) request.getSession().getAttribute("user");
			String hql = "from Complains as c, Usertype as u where u.usertype_id=c.complains_usertype and complains_userid="
					+ user.getUser_id() + " order by complains_id desc";
			Page<ComplainsVo> page = propertyService.complainsVo_listAll(hql,
					pn, 10);
			request.setAttribute("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/user_complains";
	}

	/**
	 * 进入添加投诉的列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/user_complain/complain_add", method = { RequestMethod.GET })
	public String gotoComplain(HttpServletRequest request) {
		try {
			List<Usertype> usertypeList = propertyService.getUserTypeList();
			request.setAttribute("usertypelist", usertypeList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/complains/complains_add";
	}

	/**
	 * 进入投诉详情页
	 * 
	 * @param request
	 * @param complains_id
	 * @return
	 */
	@RequestMapping(value = "/user/{complains_id}/complains_detail", method = { RequestMethod.GET })
	public String complainDetail(HttpServletRequest request,
			@PathVariable int complains_id) {
		try {
			Complains complain = propertyService.getComplain(complains_id);
			request.setAttribute("complain", complain);
			/*
			 * if(complain!=null) { User user =
			 * userService.get(complain.getComplains_userid());
			 * request.setAttribute("user", user); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/user/complains/complains_detail";
	}

	/**
	 * 进入费用缴纳页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/user_pay", method = { RequestMethod.GET })
	public String user_pay(HttpServletRequest request) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
			String hql = "from Payment as p, Paymenttype as pt where p.payment_paystate=0 and p.payment_type=pt.paymenttype_id and payment_userid="
					+ user.getUser_id() + " order by p.payment_id desc";
			Page<PaymentsVo> page = propertyService.paymentVo_listAll(hql, pn,
					15);
			request.setAttribute("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/user_pay";
	}

	/**
	 * 进入财务统计页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/user_financial", method = { RequestMethod.GET })
	public String user_financial(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
			String hqlString = "from Financial order by financial_id desc";
			Page<Financial> page = propertyService.financial_listAll(hqlString,
					pn, 10);
			request.setAttribute("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/user_financial";
	}

	/**
	 * 进入日常通知的页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/user_notify", method = { RequestMethod.GET })
	public String user_notify(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
			String hqlString = "from Notify order by notify_id desc";
			Page<Notify> page = propertyService.notify_listAll(hqlString, pn,
					10);
			// User user = (User) request.getSession().getAttribute("user");
			/*
			 * String hql = "from Notifyvisit where notifyvisit_userid=" +
			 * user.getUser_id(); String sql = "  and notifyvisit_notifyid in ";
			 * String con = "("; for(int i=0; i<page.getItems().size(); i++) {
			 * sql += con + page.getItems().get(i).getNotify_id() ; con = ","; }
			 * sql += ")"; if(page.getItems().size()!=0) hql += sql; hql +=
			 * " order by notifyvisit_notifyid desc";
			 */
			// List<Notifyvisit> list = propertyService.getNotifyvisitList(hql);
			/*
			 * for(int i=0, j=0; i<list.size() && j <page.getItems().size();
			 * j++) {
			 * 
			 * if(list.get(i).getNotifyvisit_notifyid() ==
			 * page.getItems().get(j).getNotify_id()) { i++; } }
			 */

			// request.setAttribute("notRead", list);
			request.setAttribute("page", page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/user_notify";
	}

	/**
	 * 进入用户保修的列表页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/user_repair", method = { RequestMethod.GET })
	public String user_repair(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
			User user = (User) request.getSession().getAttribute("user");
			String hqlString = "from Repair where repair_userid="
					+ user.getUser_id() + " order by repair_id desc";
			Page<Repair> page = propertyService.repair_listAll(hqlString, pn,
					10);
			request.setAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/user_repair";
	}

	/**
	 * 进入通知详情页
	 * 
	 * @param request
	 * @param notify_id
	 * @return
	 */
	@RequestMapping(value = "/user/{notify_id}/notify_detail", method = { RequestMethod.GET })
	public String notifyDetail(HttpServletRequest request,
			@PathVariable Integer notify_id) {
		try {
			Notify notify = propertyService.getNotify(notify_id);
			// User user = userService.get(notify.getNotify_userid());
			request.setAttribute("notify", notify);
			// request.setAttribute("user", user);
			User user = (User) request.getSession().getAttribute("user");
			Notifyvisit vis = new Notifyvisit();
			vis.setNotifyvisit_userid(user.getUser_id());
			vis.setNotifyvisit_notifyid(notify.getNotify_id());
			propertyService.addNotifyvisit(vis);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/notify/notify_detail";
	}

	/**
	 * 进入维修列表页
	 * 
	 * @param request
	 * @param repair_id
	 * @return
	 */
	@RequestMapping(value = "/user/{repair_id}/repair_detail", method = { RequestMethod.GET })
	public String repairDetail(HttpServletRequest request,
			@PathVariable Integer repair_id) {
		try {
			RepairVo repair = propertyService.getRepairDetail(repair_id);
			request.setAttribute("repair", repair);
			/*
			 * if(repair!=null) { User user =
			 * userService.get(repair.getRepair().getRepair_id());
			 * request.setAttribute("user", user); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/repair/repair_detail";
	}

	/**
	 * 进入报修页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/user_repair/repair_add", method = { RequestMethod.GET })
	public String addRepair() {
		return "user/repair/repair_add";
	}

	/**
	 * 进入财务统计的详情页
	 * 
	 * @param request
	 * @param financial_id
	 * @return
	 */
	@RequestMapping(value = "/financial/{financial_id}/financial_detail", method = { RequestMethod.GET })
	public String financialDetail(HttpServletRequest request,
			@PathVariable int financial_id) {
		try {
			Financial financial = propertyService.getFinancial(financial_id);
			User user = userService.get(financial.getFinancial_userid());
			request.setAttribute("financial", financial);
			request.setAttribute("user", user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "user/financial/financial_detail";
	}

	/**
	 * 进入缴费详情页
	 * 
	 * @param request
	 * @param payment_id
	 * @return
	 */
	@RequestMapping(value = "/user/{payment_id}/pay_detail", method = { RequestMethod.GET })
	public String payDetail(HttpServletRequest request,
			@PathVariable int payment_id) {
		try {
			Payment payment = propertyService.getPayment(payment_id);
			Paymenttype paymenttype = propertyService.getPaymentType(payment
					.getPayment_type());
			PayVo payVo = new PayVo(payment, paymenttype);
			request.setAttribute("payvo", payVo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/user/pay/pay_detail";
	}

	/****************************************************************************************************/

	/**
	 * 添加投诉
	 * 
	 * @param request
	 * @param usertype
	 * @param complain
	 * @return
	 */
	@RequestMapping(value = "/user/addComplain", method = { RequestMethod.POST })
	public String addComplain(HttpServletRequest request, Complains complain) {
		try {
			propertyService.addComplain((User) request.getSession()
					.getAttribute("user"), 1, complain);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/user/user_complains";
	}

	/**
	 * 进行报修操作
	 * 
	 * @param request
	 * @param repair
	 * @return
	 */
	@RequestMapping(value = "/user/user_repair/add", method = { RequestMethod.POST })
	public String toAddRepair(HttpServletRequest request,
			@ModelAttribute("command") Repair repair) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			SimpleDateFormat formater = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			repair.setRepair_starttime(formater.parse(request
					.getParameter("starttime")));
			repair.setRepair_endtime(formater.parse(request
					.getParameter("endtime")));
			repair.setRepair_userid(user.getUser_id());
			repair.setRepair_decldatatime(new Date());
			propertyService.addRepair(repair);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/user/user_repair";
	}

	/**
	 * 跳转到支付页面
	 * 
	 * @param request
	 * @param payment_id
	 * @param money
	 */
	@RequestMapping(value = "/user/pay/{payment_id}/{money}/paymoney", method = { RequestMethod.GET })
	public String gotoPay(HttpServletRequest request,
			@PathVariable int payment_id, @PathVariable double money) {
		PayMoneyVo paymoneyvo = new PayMoneyVo(payment_id, money);
		request.setAttribute("paymoneyvo", paymoneyvo);
		return "/user/pay/pay_paymoney";
	}

	/**
	 * 进行支付
	 * 
	 * @param request
	 * @param response
	 * @param paymoneyvo
	 * @throws IOException
	 */
	@RequestMapping(value = "/user/pay/paymoney", method = { RequestMethod.POST })
	public void payMoney(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String p0_Cmd = "Buy", p1_MerId = "10001126856", p2_Order = request
				.getParameter("p2_Order"), p3_Amt = request
				.getParameter("p3_Amt"), p4_Cur = "CNY", p5_Pid = "", p6_Pcat = "", p7_Pdesc = "", p8_Url = "http://119.29.116.99:8080/LaiFuCommunity/user/pay/backPayResult", p9_SAF = "", pa_MP = "", pd_FrpId = request
				.getParameter("pd_FrpId"), pr_NeedResponse = "1";
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);
		System.out.println(p2_Order);
		String url = "https://www.yeepay.com/app-merchant-proxy/node?"
				+ "&p0_Cmd="
				+ p0_Cmd
				+ "&p1_MerId="
				+ p1_MerId
				+ "&p2_Order="
				+ p2_Order
				+ "&p3_Amt="
				+ p3_Amt
				+ "&p4_Cur="
				+ p4_Cur
				+ "&p5_Pid="
				+ p5_Pid
				+ "&p6_Pcat="
				+ p6_Pcat
				+ "&p7_Pdesc="
				+ p7_Pdesc
				+ "&p8_Url="
				+ p8_Url
				+ "&p9_SAF="
				+ p9_SAF
				+ "&pa_MP="
				+ pa_MP
				+ "&pd_FrpId="
				+ pd_FrpId
				+ "&pr_NeedResponse="
				+ pr_NeedResponse + "&hmac=" + hmac;
		response.sendRedirect(url);
	}

	/**
	 * 返回支付结果
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/user/pay/backPayResult", method = { RequestMethod.GET })
	public void backPayResult(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String r1_Code = request.getParameter("r1_Code");
		PrintWriter pw = response.getWriter();
		if ("1".equals(r1_Code)) {
			String p1_MerId = request.getParameter("p1_MerId");
			String r3_Amt = request.getParameter("r3_Amt");
			String r6_Order = request.getParameter("r6_Order");
			String rp_PayDate = request.getParameter("rp_PayDate");
			Payment payment = propertyService.getPayment(Integer
					.parseInt(r6_Order));
			Date date = new Date();
			String paytime = TimeTransform.DateToString(date);
			payment.setPayment_complettime(date);
			payment.setPayment_paystate(1);
			propertyService.updatePaymentState(payment);
			pw.println("支付成功！<br/>" + "支付金额：" + r3_Amt + "<br/>" + "商户订单号："
					+ r6_Order + "<br/>" + "支付成功时间：" + paytime);
		} else {
			pw.println("支付失败！");
		}
		// return "/user/pay/pay_paysuccess";
	}
}
