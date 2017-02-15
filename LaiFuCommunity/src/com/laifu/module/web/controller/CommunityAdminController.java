package com.laifu.module.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.laifu.common.exception.UserException;
import com.laifu.common.pagination.Page;
import com.laifu.common.utils.UploadPicture;
import com.laifu.module.dao.UserDao;
import com.laifu.module.entity.Community;
import com.laifu.module.entity.Complains;
import com.laifu.module.entity.People;
import com.laifu.module.entity.User;
import com.laifu.module.service.CommunityManageService;
import com.laifu.module.service.UserService;
import com.laifu.module.vo.UserVo;



/**
 * 小区管理员Controller
 * @author zepeng
 * @version 2016-9-12 
 */
@Controller
public class CommunityAdminController {
	
	@Autowired
    @Qualifier("CommunityManageService")
    private CommunityManageService communityManageService;
	
	@Autowired
    @Qualifier("UserService")
    private UserService userService;
	
	/**
	 * 进入小区管理员的界面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/comadmin/comadmin",method = {RequestMethod.GET})
	public String gotoComadmin(HttpServletRequest request) {
		try {
			UserVo vo = (UserVo) request.getSession().getAttribute("admin");
 			if(vo == null || vo.getUsertype().getUsertype_id()!=3) return "redirect:/sysadmin/sysadmin_login";
 			request.setAttribute("user", ((UserVo)request.getSession().getAttribute("admin")).getUser());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/comadmin";
	}
	
	
	@RequestMapping(value = "/comadmin/comadmin_infor",method = {RequestMethod.GET})
	public String gotoComadmin_infor(HttpServletRequest request) {
		try {
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/comadmin_infor";
	}
	
	
	@RequestMapping(value = "/comadmin/comadmin_community",method = {RequestMethod.GET})
	public String gotoComadmin_community(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
	        String hql = "from Community order by community_id desc";
	        Page<Community> page = communityManageService.community_listAll(hql, pn, 10);
			request.setAttribute("page", page);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/comadmin_community";
	}
	
	
	@RequestMapping(value = "/comadmin/comadmin_user",method = {RequestMethod.GET})
	public String gotoComadmin_user(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
	        String hql = "from User where user_type = 1 order by user_id desc";
	        Page<User> page = userService.listAll(hql, pn, 10);
			request.setAttribute("page", page);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/comadmin_user";
	}
	
	
	@RequestMapping(value = "/comadmin/comadmin_property",method = {RequestMethod.GET})
	public String gotoComadmin_property(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
	        String hql = "from User where user_type = 2 order by user_id desc";
	        Page<User> page = userService.listAll(hql, pn, 10);
			request.setAttribute("page", page);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/comadmin_property";
	}
	
	
	@RequestMapping(value = "/comadmin/community/community_add",method = {RequestMethod.GET})
	public String gotoCommunity_add(HttpServletRequest request) {
		try {
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/community/community_add";
	}
	
	@RequestMapping(value = "/comadmin/{community_id}/community/community_detail",method = {RequestMethod.GET})
	public String gotoCommunity_detail(HttpServletRequest request, @PathVariable int community_id) {
		try {
			Community community = communityManageService.getCommunity(community_id);
 			request.setAttribute("community", community);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/community/community_detail";
	}
	
	@RequestMapping(value = "/comadmin/{community_id}/community/community_edit",method = {RequestMethod.GET})
	public String gotoCommunity_edit(HttpServletRequest request, @PathVariable int community_id) {
		try {
			Community community = communityManageService.getCommunity(community_id);
 			request.setAttribute("community", community);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/community/community_edit";
	}
	
	@RequestMapping(value = "/comadmin/comadmin_complains",method = {RequestMethod.GET})
	public String gotoComadmin_complains(HttpServletRequest request) {
		try {
			int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
	        String hql = "from Complains order by complains_id desc";
	        Page<Complains> page = communityManageService.complains_listAll(hql, pn, 10);
			request.setAttribute("page", page);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/comadmin_complains";
	}
	
	@RequestMapping(value = "/comadmin/{complains_id}/complains/complains_detail",method = {RequestMethod.GET})
	public String gotoComplains_detail(HttpServletRequest request, @PathVariable int complains_id) {
		try {
			Complains c = communityManageService.getComplains(complains_id);
			request.setAttribute("complains", c);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/complains/complains_detail";
	}
	
	@RequestMapping(value = "/comadmin/information/password_update",method = {RequestMethod.GET})
	public String gotoPassword_update(HttpServletRequest request) {
		try {
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/information/password_update";
	}
	
	
	@RequestMapping(value = "/comadmin/property/property_add",method = {RequestMethod.GET})
	public String gotoProperty_add(HttpServletRequest request) {
		try {
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/property/property_add";
	}
	
	@RequestMapping(value = "/comadmin/property/{user_id}/property_detail",method = {RequestMethod.GET})
	public String gotoProperty_detail(HttpServletRequest request, @PathVariable int user_id) {
		try {
			User user = communityManageService.getUser(user_id);
			request.setAttribute("user", user);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/property/property_detail";
	}
	
	@RequestMapping(value = "/comadmin/property/{user_id}/property_edit",method = {RequestMethod.GET})
	public String gotoProperty_edit(HttpServletRequest request, @PathVariable int user_id) {
		try {
			User user = communityManageService.getUser(user_id);
			request.setAttribute("user", user);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/property/property_edit";
	}
	
	
	@RequestMapping(value = "/comadmin/user/user_add",method = {RequestMethod.GET})
	public String gotoUser_add(HttpServletRequest request) {
		try {
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/user/user_add";
	}
	
	@RequestMapping(value = "/comadmin/user/{user_id}/user_detail",method = {RequestMethod.GET})
	public String gotoUser_detail(HttpServletRequest request, @PathVariable int user_id) {
		try {
			User user = communityManageService.getUser(user_id);
			request.setAttribute("user", user);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/user/user_detail";
	}
	
	@RequestMapping(value = "/comadmin/user/{user_id}/user_edit",method = {RequestMethod.GET})
	public String gotoUser_edit(HttpServletRequest request, @PathVariable int user_id) {
		try {
			User user = communityManageService.getUser(user_id);
			request.setAttribute("user", user);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/comadmin/user/user_edit";
	}
	
	
	/*******************************************************************************************************/
	
	/**
	 * 更新个人信息
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/comadmin/updateInfor",method = {RequestMethod.POST})
	public String updateInfor(HttpServletRequest request, @ModelAttribute("command") @Valid User user) {
		try {
			userService.updateInfor(user, request);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/comadmin/comadmin_infor";
	}
	
	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping( value="/comadmin/updatePassword", method={RequestMethod.POST} )
	public String updatePassword(HttpServletRequest request, @ModelAttribute("command") @Valid User user) {
		try {
			UserVo vo = ((UserVo) request.getSession().getAttribute("admin"));
 			userService.updatePassword(vo.getUser());
 			vo.getUser().setUser_password(user.getUser_password());
 			request.getSession().setAttribute("admin", vo);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/comadmin/comadmin_infor";
	}
	
	
	
	/**
 	 * 回复投诉建议
 	 * 
 	 * @param request
 	 * @param complains 要回复的投诉建议
 	 * @return
 	 */
 	@RequestMapping( value="/comadmin/replyComplains", method={RequestMethod.POST} )
 	public String replyComplains(HttpServletRequest request, @ModelAttribute("command") @Valid Complains complains) {
 		try {
 			communityManageService.replyComplains(complains);
 			
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 		return "redirect:/comadmin/" + complains.getComplains_id() + "/complains/complains_detail";
 	}
 	
 	/**
 	 * 更新小区信息的功能
 	 * 
 	 * @param request 请求对象
 	 * @param community 要修改的小区对象
 	 * @return 小区列表页
 	 */
 	@RequestMapping( value="/comadmin/{community_id}/updateCommunity", method={RequestMethod.POST} )
 	public String updateCommunity(HttpServletRequest request, @ModelAttribute("command") @Valid Community community, @PathVariable int community_id) {
 		try {
 			community.setCommunity_id(community_id);
 			communityManageService.updateCommunity(community);
 			
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 		return "redirect:/comadmin/" + community_id + "/community/community_detail";
 	}
 	
 	@RequestMapping( value="/comadmin/{community_id}/deleteCommunity", method={RequestMethod.GET} )
 	public String deleteCommunity(HttpServletRequest request, @PathVariable int community_id) {
 		try {
 			communityManageService.deleteCommunity(community_id);
 			
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 		return "redirect:/comadmin/comadmin_community";
 	}
 	
 	@RequestMapping( value="/comadmin/addUser", method={RequestMethod.POST} )
 	public String addUser(HttpServletRequest request, @ModelAttribute("command") @Valid User user) {
 		try {
 			user.setUser_type(1);
 			user.setUser_registertime(new Date());
 			user.setUser_head("/upload/default.jpg");
 			communityManageService.addUser(user);
 			
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 		return "redirect:/comadmin/comadmin_user";
 	}
 	
 	@RequestMapping( value="/comadmin/addProperty", method={RequestMethod.POST} )
 	public String addProperty(HttpServletRequest request, @ModelAttribute("command") @Valid User user) {
 		try {
 			user.setUser_type(2);
 			user.setUser_registertime(new Date());
 			user.setUser_head("/upload/default.jpg");
 			communityManageService.addUser(user);
 			
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 		return "redirect:/comadmin/comadmin_property";
 	}
 	
 	@RequestMapping( value="/comadmin/{user_id}/deleteUser", method={RequestMethod.GET} )
 	public String deleteUser(HttpServletRequest request, @PathVariable int user_id) {
 		try {
 			communityManageService.deleteUser(user_id);
 			
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 		return "redirect:/comadmin/comadmin_user";
 	}
 	
 	@RequestMapping( value="/comadmin/{property_id}/deleteProperty", method={RequestMethod.GET} )
 	public String deleteProperty(HttpServletRequest request, @PathVariable int property_id) {
 		try {
 			communityManageService.deleteUser(property_id);
 			
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 		return "redirect:/comadmin/comadmin_property";
 	}
 	
 	@RequestMapping(value = "/comadmin/updateUser", method = {RequestMethod.POST})
	public String updateUser(HttpServletRequest request, @ModelAttribute("command") @Valid User user) { 
 		try {
 			communityManageService.changeUser(user);
 			
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 		return "redirect:/comadmin/comadmin_user";
	}
 	
 	@RequestMapping(value = "/comadmin/updateProperty", method = {RequestMethod.POST})
	public String updateProperty(HttpServletRequest request, @ModelAttribute("command") @Valid User user) { 
 		try {
 			communityManageService.changeUser(user);
 			
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 		return "redirect:/comadmin/comadmin_property";
 	}
	
	
	/*******************************************************************************************************/
	/**
	 * 跳转用
	 */
	 @RequestMapping(value = "/comdmin/jumping",method = {RequestMethod.GET})
	public String jumping(){
		
		return "comadmin/addCommunity";
	}
	 
	 
	
	/**
	 * 显示用户列表
	 */
	 @RequestMapping(value = "/comadmin/{user_type}/showUserlist",method = {RequestMethod.GET})
	public ModelAndView showUserlist(@PathVariable Integer user_type){
		List<User> users = communityManageService.getUserList(user_type);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("users",users);
		modelAndView.setViewName("/comadmin/userList");
		return modelAndView;
	}
	 
	 
	 
	 /**
	  * 删除用户
	  *//*
	 @RequestMapping(value = "/comadmin/{user_id}/deleteUser", method = {RequestMethod.GET})
	 public String deleteUser(@PathVariable Integer user_id){
		 
		 communityManageService.deleteUser(user_id);
		 return "comadmin/success";
	 }*/
	 
	   /**
	     * 显示用户具体信息
	     * 
	     * @param request 用户请求
	     * @param id 小区id
	     * @return
	     */
	    @RequestMapping(value = "/comadmin/{user_id}/showUser", method = {RequestMethod.GET})
	    public String showUser(@PathVariable Integer user_id,HttpServletRequest request) {
	      
	      request.setAttribute("user",communityManageService.getUser(user_id));
	    
	      return "/comadmin/showUser";
	    }
	    
	    
		 
		
		 /**
		  * 注册小区
		  * @param community 小区对象
		  */
		 @RequestMapping(value = "/comadmin/addCommunity",method = {RequestMethod.POST})
		 public String addCommunity(Community community){
			 communityManageService.addCommunity(community);
			 return "comadmin/success";
		 }
		 
		 /**
		  * 小区管理员跳转登陆用
		  */
		 @RequestMapping(value="/comadmin/comadmin_login", method={RequestMethod.GET} ) 
			public String gotoLogin() {
				return "/admin/user_login";
		}
		 
		 /**
			 * 小区管理员上传头像的功能
			 * 
			 * @param request
			 * @param file
			 * @return
			 */
			@RequestMapping(value = "/comadmin/uploadPicture", method = { RequestMethod.POST })
			public String uploadPicture(HttpServletRequest request,HttpServletResponse response,
					@RequestParam MultipartFile file) {
				
				HttpSession session = request.getSession();
				if (session.getAttribute("admin") != null) {
					UserVo vo = (UserVo) session.getAttribute("admin");
					vo.getUser().setUser_head(UploadPicture.uploadHead(request, file,
							vo.getUser().getUser_head()));
				   String imagePath =" 0`" + request.getContextPath() + vo.getUser().getUser_head();
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
			 *  审核用户
			 * @param request
			 * @param user
			 * @return
			 */
			@RequestMapping(value = "/comadmin/user/{user_id}/{checkstate}/usercheck", method = { RequestMethod.GET })
			public String checkUserState(HttpServletRequest request,@PathVariable int user_id,@PathVariable int checkstate){
				User user = userService.get(user_id);
				UserVo vo = ((UserVo) request.getSession().getAttribute("admin"));
				user.setUser_approver(vo.getUser().getUser_realname());
				user.setUser_approvaltime(new Date());
				user.setUser_checkstate(checkstate);
				userService.update(user);
				return "redirect:/comadmin/comadmin_user";
			}
			
}
