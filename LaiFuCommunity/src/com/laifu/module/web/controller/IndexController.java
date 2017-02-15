package com.laifu.module.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.laifu.module.entity.Notify;
import com.laifu.module.entity.Paymenttype;
import com.laifu.module.entity.User;
import com.laifu.module.service.PropertyService;


/**主页
 * @author Raindrops
 * @version 1.0
 */
@Controller("indexController")
public class IndexController {
	
	@Autowired
	@Qualifier("PropertyService")
	private PropertyService propertyService;
    
    @RequestMapping(value = "/index", method = { RequestMethod.GET })
    public String index(HttpServletRequest request){
    	
    	User user = (User) request.getSession().getAttribute("user");
		if(user.getUser_checkstate()==1) {
			int notifyvisit_count = propertyService.getNotifyvisit_count(user.getUser_id());
			request.getSession().setAttribute("notifyvisit_count", notifyvisit_count);
		}
    	
    	List<Notify> notifylist= propertyService.getTopNotifyList(3); //获取最新的三条通知
    	request.setAttribute("notifylist",notifylist); 
    	
    	List<Paymenttype> paymenttypelist = propertyService.getPaymenttypeList(3); //获取费用类型的前三条数据
    	request.setAttribute("paymenttypelist",paymenttypelist); 
        return "index";
    }
    
    @RequestMapping(value = "/getNotifyvisit_count", method = { RequestMethod.GET })
    public void getNotifyvisit_count(HttpServletRequest request, HttpServletResponse response) {
    	try {
			User user = (User) request.getSession().getAttribute("user");
			Object obj = request.getSession().getAttribute("notifyvisit_count");
			int oldCount = -1;
			int notifyvisit_count = -1;
			if(obj != null) {
				oldCount = (Integer) obj;
				if(user.getUser_checkstate()==1) {
					notifyvisit_count = propertyService.getNotifyvisit_count(user.getUser_id());
					request.getSession().setAttribute("notifyvisit_count", notifyvisit_count);
				}
			}
			String code = "0";
			if(notifyvisit_count!=-1 && notifyvisit_count>oldCount) code = String.valueOf(notifyvisit_count);
			response.getWriter().write(code);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
