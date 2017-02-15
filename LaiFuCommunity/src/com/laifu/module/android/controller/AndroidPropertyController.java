package com.laifu.module.android.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.laifu.common.pagination.Page;
import com.laifu.common.utils.UploadPicture;
import com.laifu.module.entity.Complains;
import com.laifu.module.entity.House;
import com.laifu.module.entity.Notify;
import com.laifu.module.entity.Payment;
import com.laifu.module.entity.Repair;
import com.laifu.module.entity.SendCode;
import com.laifu.module.entity.User;
import com.laifu.module.entity.Usertype;
import com.laifu.module.service.PropertyService;
import com.laifu.module.vo.PaymentsVo;

@Controller
public class AndroidPropertyController {

	@Autowired
	@Qualifier("PropertyService")
	private PropertyService  propertyService;
	
	
	/**
	 * 添加投诉
	 * 
	 * @param response
	 * @param request
	 * @param complains
	 */
	@RequestMapping( value="/Android/property/addComplains", method={RequestMethod.GET, RequestMethod.POST} )
	public void addComplains(HttpServletResponse response, HttpServletRequest request, User user, Usertype usertype, Complains complains) {
		try {
			//System.out.println("hello");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			//System.out.println(JSONObject.fromObject(complains).toString());
			PrintWriter writer = response.getWriter();
			
			propertyService.addComplain(user, usertype.getUsertype_id(), complains);
			
			SendCode code = new SendCode(1, "添加成功!");
			JSONObject jso = JSONObject.fromObject(code);
			writer.print(jso.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取投诉建议的列表
	 * 
	 * @param response
	 * @param request
	 * @param user
	 */
	@RequestMapping( value="/Android/property/getComplains", method={RequestMethod.GET, RequestMethod.POST} )
	public void getComplains(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("command") @Valid User user) {
		try {
			//System.out.println("hello");
			response.setContentType("text/plain; charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			
			List<Complains> list = propertyService.getComplainsList(user.getUser_id(), 0, 6);
			
			SendCode code = new SendCode(1, "获取成功!");
			JSONObject jo = new JSONObject();
			jo.put("SendCode", code);
			jo.put("Complains", list);
			//System.out.println(jo.toString());
			writer.print(jo.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取缴费历史列表
	 * 
	 * @param response
	 * @param request
	 * @param user
	 */
	@RequestMapping( value="/Android/property/getPayment", method={RequestMethod.GET, RequestMethod.POST} )
	public void getPayment(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("command") @Valid User user) {
		try {
			//System.out.println("getPayment");
			response.setContentType("text/plain; charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+1;
			int day = cal.get(Calendar.DATE);
			int startMonth = month - 3;
			int startYear = year;
			if(startMonth < 1) {
				startYear --;
				startMonth += 12;
			}
			String startTime = startYear + "-" + startMonth + "-" + "1";
			String endTime = year + "-" + month + "-" + day;
			String hql = "from Payment as p, Paymenttype as pt where p.payment_paystate=1 and p.payment_userid="+user.getUser_id()+" and p.payment_type=pt.paymenttype_id and p.payment_endtime between '"+startTime+"' and '"+endTime+"' order by p.payment_id desc";
			List<PaymentsVo> list = propertyService.getPaymentList(hql);
			
			SendCode code = new SendCode(1, "获取成功!");
			JSONObject jo = new JSONObject();
			jo.put("SendCode", code);
			jo.put("PaymentVo", list);
			//System.out.println(jo.toString());
			writer.print(jo.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取最新的一条通知
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping( value="/Android/property/getNotify", method={RequestMethod.GET, RequestMethod.POST} )
	public void getNotify(HttpServletResponse response, HttpServletRequest request) {
		try {
			Notify notify = propertyService.getNewestNotify();
			response.setContentType("text/plain; charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			
			SendCode code;
			if(notify.getNotify_content() == null) code = new SendCode(0, "获取失败!");
			else code = new SendCode(1, "获取成功!");
			JSONObject jso = new JSONObject();
			jso.put("SendCode", code);
			jso.put("notifyList", notify);
			//System.out.println(jso.toString());
			writer.print(jso.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取维修列表
	 * 
	 * @param response
	 * @param request
	 * @param repair
	 */
	@RequestMapping( value="/Android/property/getRepair", method={RequestMethod.GET, RequestMethod.POST} )
	public void getRepair(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("command") @Valid Repair repair) {
		try {
			
			//System.out.println("hello");
			response.setContentType("text/plain; charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			//System.out.println(JSONObject.fromObject(repair).toString());
			
			String hql = "from Repair where repair_userid=" + repair.getRepair_userid() + " order by repair_id desc";
			Page<Repair> page = propertyService.repair_listAll(hql, 0, 6);
			
			PrintWriter writer = response.getWriter();
			
			SendCode code = new SendCode(1, "修改成功!");
			JSONObject jo = new JSONObject();
			jo.put("SendCode", code);
			jo.put("Repair", page.getItems());
			//System.out.println(jo.toString());
			writer.print(jo.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 进行报修操作
	 * 
	 * @param response
	 * @param request
	 * @param repair
	 */
	@RequestMapping( value="/Android/property/addRepair", method={RequestMethod.GET, RequestMethod.POST} )
	public void addRepair(HttpServletResponse response, HttpServletRequest request, 
			@ModelAttribute("command") @Valid Repair repair) {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			//System.out.println("hello");
			//System.out.println(JSONObject.fromObject(repair).toString());
			
			repair.setRepair_decldatatime(new Date());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			repair.setRepair_starttime(format.parse(request.getParameter("startTime")));
			repair.setRepair_endtime(format.parse(request.getParameter("endTime")));
			propertyService.addRepair(repair);
			
			PrintWriter writer = response.getWriter();
			SendCode code = new SendCode(1, "修改成功!");
			JSONObject jso = JSONObject.fromObject(code);
			writer.print(jso.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 进行报修操作
	 * 
	 * @param response
	 * @param request
	 * @param repair
	 */
	@RequestMapping( value="/Android/property/addRepairWithPicture", method={RequestMethod.GET, RequestMethod.POST} )
	public void addRepairWithPicture(HttpServletResponse response, HttpServletRequest request, 
			@ModelAttribute("command") @Valid Repair repair, @RequestParam MultipartFile file) {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			//System.out.println("hello");
			//System.out.println(JSONObject.fromObject(repair).toString());
			
			repair.setRepair_decldatatime(new Date());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			repair.setRepair_starttime(format.parse(request.getParameter("startTime")));
			repair.setRepair_endtime(format.parse(request.getParameter("endTime")));
			repair.setRepair_picturepath(UploadPicture.uploadHead(request, file, null));
			propertyService.addRepair(repair);
			
			PrintWriter writer = response.getWriter();
			SendCode code = new SendCode(1, "修改成功!");
			JSONObject jso = JSONObject.fromObject(code);
			writer.print(jso.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取未缴费的列表
	 * 
	 * @param response
	 * @param request
	 * @param repair
	 */
	@RequestMapping( value="/Android/property/getNotPayment", method={RequestMethod.GET, RequestMethod.POST} )
	public void getNotPayment(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("command") @Valid User user) {
		try {
			//System.out.println("hello");
			response.setContentType("text/plain; charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+1;
			int day = cal.get(Calendar.DATE);
			String startTime = year + "-" + month + "-" + "1";
			String endTime = year + "-" + month + "-" + day;
			String hql = "from Payment as p, Paymenttype as pt where p.payment_paystate=0 and p.payment_userid="+user.getUser_id()+" and p.payment_type=pt.paymenttype_id and p.payment_endtime between '"+startTime+"' and '"+endTime+"'";
			//System.out.println(hql);
			List<PaymentsVo> list = propertyService.getPaymentList(hql);
			
			SendCode code = new SendCode(1, "获取成功!");
			JSONObject jo = new JSONObject();
			jo.put("SendCode", code);
			jo.put("PaymentVo", list);
			//System.out.println(jo.toString());
			writer.print(jo.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 确认楼房是否存在
	 * 
	 * @param request
	 * @param response
	 * @param house
	 */
	@RequestMapping(value = "/Android/property/checkHouse", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void checkHouse(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("command") House house) {
		try {
			SendCode code;
			if (!propertyService.exitHouse(house.getHouse_floornumber()))
				code = new SendCode(0, "楼房不存在!");
			else
				code = new SendCode(1, "楼房存在!");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().write(JSONObject.fromObject(code).toString());

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
	@RequestMapping(value = "/Android/property/checkRoom", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void checkRoom(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("command") House house) {
		try {
			House h = propertyService.getRoom(house.getHouse_floornumber(),
					house.getHouse_roomnumber());
			SendCode code;
			if (h == null)
				code = new SendCode(0, "房屋不存在!");
			else
				code = new SendCode(1, "房屋存在!");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().write(JSONObject.fromObject(code).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 支付接口
	 * 
	 * @param request
	 * @param response
	 * @param house
	 */
	@RequestMapping(value = "/Android/property/updatePayment", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void updatePayment(HttpServletRequest request,
			HttpServletResponse response, String paymentIds) {
		try {
			//propertyService.updatePaymentState(payment);
			JSONArray jsa = JSONArray.fromObject(paymentIds);
			for(int i=0; i<jsa.size(); i++) {
				Payment payment = new Payment();
				payment.setPayment_id((Integer)jsa.getJSONObject(i).get("payment_id"));
				payment.setPayment_complettime(new Date());
				propertyService.updatePaymentState(payment);
			}
			SendCode code = new SendCode(1, "更新支付成功!");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			response.getWriter().write(JSONObject.fromObject(code).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
