package com.laifu.module.android.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.laifu.common.exception.UserException;
import com.laifu.common.utils.MultiUploadPicture;
import com.laifu.common.utils.UploadPicture;
import com.laifu.common.utils.verificationcode.BSONSms;
import com.laifu.module.entity.Comment;
import com.laifu.module.entity.Complains;
import com.laifu.module.entity.House;
import com.laifu.module.entity.Praise;
import com.laifu.module.entity.Repair;
import com.laifu.module.entity.SendCode;
import com.laifu.module.entity.Topic;
import com.laifu.module.entity.User;
import com.laifu.module.service.CommunityTopicService;
import com.laifu.module.service.PropertyService;
import com.laifu.module.service.UserService;
import com.laifu.module.vo.AuthorVo;
import com.laifu.module.vo.CommentVo;
import com.laifu.module.vo.TopicVo;
import com.laifu.module.vo.UserVo;

@Controller
public class AndroidUserController {
	
	@Autowired
	@Qualifier("UserService")
	private UserService userService;
	
	@Autowired
	@Qualifier("CommunityTopicService")
	private CommunityTopicService communityTopicService;
	
	@Autowired
	@Qualifier("PropertyService")
	private PropertyService propertyService;
	
	/**
	 * 查询账号是否存在
	 * 
	 * @param response
	 * @param request
	 * @param user
	 */
	@RequestMapping( value="/Android/user/exitAccount", method={RequestMethod.GET, RequestMethod.POST} )
	public void exitAccount(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("command") @Valid User user) {
		try {
			response.setContentType("text/plain; charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			
			SendCode code;
			if(userService.exit(user.getUser_account())) {
				code = new SendCode(1, "用户手机存在!");
			}
			else {
				code = new SendCode(0, "用户手机不存在!");
			}
			
			JSONObject jo = JSONObject.fromObject(code);
			writer.print(jo.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取短信验证码的接口
	 * 
	 * @param response
	 * @param request
	 * @param user
	 */
	@RequestMapping( value="/Android/user/getSMS", method={RequestMethod.GET, RequestMethod.POST} )
	public void getSMS(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("command") @Valid User user) {
		try {
			
			//System.out.println("hello");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			
			String number = userService.SendCode(user.getUser_account());
			
			SendCode code = new SendCode(1, "获取短信成功!");
			JSONObject jso = new JSONObject();
			jso.put("SendCode", code);
			jso.put("Number", number);
			//System.out.println(jso.toString());
			writer.print(jso.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用户注册
	 * 
	 * @param response
	 * @param request
	 * @param user
	 */
	@RequestMapping( value="/Android/user/register", method={RequestMethod.GET, RequestMethod.POST} )
	public void register(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("command") @Valid User user) {
		try {
			
			//System.out.println("hello");
			response.setContentType("text/plain; charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			//System.out.println(JSONObject.fromObject(user).toString());
			PrintWriter writer = response.getWriter();
			
			SendCode code;
			try {
				userService.register(user);
				code = new SendCode(1, "注册成功!");
			} catch(Exception e) {
				code = new SendCode(0, "注册失败!");
			}
			
			JSONObject jso = JSONObject.fromObject(code);
			//System.out.println(jso.toString());
			writer.print(jso.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * android登录接口
	 * 
	 * @param response 服务器的响应对象
	 * @param request 请求对象
	 * @param user 要登陆的对象
	 */
	@RequestMapping( value="/Android/user/login", method={RequestMethod.GET, RequestMethod.POST} )
	public void login(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("command") @Valid User user) {
		try {
			
			//System.out.println("hello");
			
			UserVo vo = userService.android_login(user);
			response.setContentType("text/plain; charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			JSONObject jso = new JSONObject();
			
			if(vo != null) {
				//System.out.println("yes");
				SendCode code = new SendCode(1, "登录成功!");
				jso.put("SendCode", code);
				jso.put("UserVo", vo);
				
			} else {
				//System.out.println("no");
				SendCode code = new SendCode(0, "登录失败!");
				jso.put("SendCode", code);
				jso.put("UserVo", new UserVo());
			}
			

			writer.print(jso.toString());
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 用户完善信息的功能
	 * 
	 * @param response
	 * @param request
	 * @param user 要完善的用户信息
	 */
	@RequestMapping( value="/Android/user/completeInfor", method={RequestMethod.GET, RequestMethod.POST} )
	public void completeInfor(HttpServletResponse response, HttpServletRequest request, User user,House house) {
		try {
			System.out.println("hello");
			response.setContentType("text/plain; charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			//System.out.println(JSONObject.fromObject(user).toString());
			//System.out.println(JSONObject.fromObject(house).toString());
			SendCode code;
			System.out.println(user.toString());
			System.out.println(house.toString());
			User preUser = userService.getByAccount(user.getUser_account());
			House h = propertyService.getRoom(house.getHouse_floornumber(), house.getHouse_roomnumber());
			System.out.println(preUser.getUser_account());
			System.out.println(h.getHouse_id());
			if(h!=null) {
				userService.complete(preUser, user, h.getHouse_id());
				code = new SendCode(1, "修改成功!");
			}
			else {
				code = new SendCode(0, "修改失败!");
			}

			PrintWriter writer = response.getWriter();
			JSONObject jso = JSONObject.fromObject(code);
			writer.print(jso.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改用户密码
	 * 
	 * @param response
	 * @param request
	 * @param user
	 */
	@RequestMapping( value="/Android/user/updatePassword", method={RequestMethod.GET, RequestMethod.POST} )
	public void updatePassword(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("command") @Valid User user) {
		try {
			
			//System.out.println("hello");
			
			userService.android_updatePassword(user);
			response.setContentType("text/plain; charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter writer = response.getWriter();
			
			userService.updatePassword(user);
			
			SendCode code = new SendCode(1, "修改成功!");
			JSONObject jso = JSONObject.fromObject(code);
			writer.print(jso.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更改昵称
	 * 
	 * @param response
	 * @param request
	 * @param user
	 */

	@RequestMapping( value="/Android/user/updateNickname", method={RequestMethod.GET, RequestMethod.POST} )
	public void updateNickname(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("command") @Valid User user) {
		try {
			
			//System.out.println("hello");
			//System.out.println(JSONObject.fromObject(user).toString());
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			
			userService.updateNickName(user);
			
			SendCode code = new SendCode(1, "修改成功!");
			JSONObject jso = JSONObject.fromObject(code);
			writer.print(jso.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 话题点赞
	 * 
	 * @param topic_id
	 * @param user
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/Android/user/topic/{topic_id}/praiseTopic", method = { RequestMethod.GET, RequestMethod.POST })
	public void PraiseTopic(@PathVariable int topic_id, @ModelAttribute("command") @Valid User user, HttpServletResponse response, HttpServletRequest request) {
		Praise praise = new Praise();
		int user_id = user.getUser_id();
		praise.setPraise_topicid(topic_id);
		praise.setPraise_userid(user_id);
		communityTopicService.addPraise(praise);
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		try {
			list = communityTopicService.getPraise(topic_id);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			SendCode code = new SendCode(1, "点赞成功!");
			JSONArray jsonArray = JSONArray.fromObject(list);
			JSONObject jso = new JSONObject();
			jso.put("SendCode", code);
			jso.put("topic_id", new Integer(topic_id));
			jso.put("TopicList", jsonArray);
			//System.out.println(jso.toString());
			response.getWriter().write(jso.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 话题取消赞
	 * 
	 * @param topic_id
	 * @param user
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/Android/user/topic/{topic_id}/deletePraise", method = { RequestMethod.GET, RequestMethod.POST })
	public void deletePrise(@PathVariable int topic_id, @ModelAttribute("command") @Valid User user, HttpServletResponse response, HttpServletRequest request) {
		int user_id = user.getUser_id();
		communityTopicService.deletePraise(user_id, topic_id);
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		try {
			list = communityTopicService.getPraise(topic_id);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			JSONArray jsonArray = JSONArray.fromObject(list);
			response.getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取话题列表
	 * 
	 * @param request
	 * @param response
	 * @param pn
	 * @param size
	 * @param user
	 */
	@RequestMapping(value = "/Android/user/topic/{pn}/{size}/getTopicList", method = { RequestMethod.GET , RequestMethod.POST})
	public void getTopicList(HttpServletRequest request, HttpServletResponse response, @PathVariable int pn, @PathVariable int size, @ModelAttribute("command") @Valid User user) {
		try {
			//System.out.println("hello");
			if(request.getParameter("communityTopic") != null) {
				List<TopicVo> list = communityTopicService.getTopicVo(user.getUser_id(), pn, size, request.getParameter("communityTopic"));
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/plain; charset=UTF-8");
				JSONObject jso = new JSONObject();
				SendCode code = new SendCode(1, "获取话题成功!");
				JSONArray jsonArray = JSONArray.fromObject(list);
				jso.put("SendCode", code);
				jso.put("TopicList", jsonArray);
				//System.out.println(jso.toString());
				response.getWriter().write(jso.toString());
				//response.getWriter().write("hello");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 忘记密码
	 * 
	 * @param response
	 * @param request
	 * @param user
	 */
	@RequestMapping( value="/Android/user/forgetPassword", method={RequestMethod.GET, RequestMethod.POST} )
	public void forgetPassword(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("command") @Valid User user) {
		try {
			
			//System.out.println("hello");
			//System.out.println(JSONObject.fromObject(user).toString());
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			
			userService.updatePasswordByAccount(user);
			
			SendCode code = new SendCode(1, "修改密码成功!");
			JSONObject jso = JSONObject.fromObject(code);
			writer.print(jso.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 删除话题
	 * 
	 * @param response
	 * @param topic_id
	 * @param topic
	 */
	@RequestMapping(value = "/Android/{topic_id}/deleteTopic", method = { RequestMethod.GET })
	public void deleteTopic(HttpServletResponse response, @PathVariable Integer topic_id) {
		try {
			//System.out.println("hello");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			
			communityTopicService.deleteTopic(topic_id);
			
			SendCode code = new SendCode(1, "删除话题成功!");
			JSONObject jso = JSONObject.fromObject(code);
			writer.print(jso.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加话题
	 * 
	 * @param user
	 * @param topic
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/Android/addTopicWithPicture", method = { RequestMethod.POST, RequestMethod.GET })
	public void addTopicWithPicture(HttpServletRequest request, HttpServletResponse response, User user, Topic topic, 
			@RequestParam MultipartFile[] file) {
		try {
			topic.setTopic_userid(user.getUser_id());
			topic.setTopic_type(2);
			topic.setTopic_datetime(new Date());
			
			String filename = new MultiUploadPicture().MutiUpload(request, file);
			topic.setTopicpicture_path(filename);
			communityTopicService.save(topic);
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			SendCode code = new SendCode(1, "发表话题成功!");
			JSONObject jso = JSONObject.fromObject(code);
			response.getWriter().write(jso.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加话题
	 * 
	 * @param user
	 * @param topic
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/Android/addTopic", method = { RequestMethod.POST, RequestMethod.GET })
	public void addTopic(HttpServletRequest request, HttpServletResponse response, User user, Topic topic) {
		try {
			topic.setTopic_userid(user.getUser_id());
			topic.setTopic_type(2);
			topic.setTopic_datetime(new Date());
			
			communityTopicService.save(topic);
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			SendCode code = new SendCode(1, "发表话题成功!");
			JSONObject jso = JSONObject.fromObject(code);
			response.getWriter().write(jso.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 评论话题
	 * 
	 * @param topic_id
	 * @param user
	 * @param comment
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/Android/{topic_id}/commentTopic", method = { RequestMethod.POST })
	public void commentTopic(@PathVariable int topic_id, User user, Comment comment, HttpServletRequest request, HttpServletResponse response) {
		try {
			int user_id = user.getUser_id();
			comment.setComment_time(new Date());
			comment.setComment_topic(topic_id);
			comment.setComment_user(user_id);
			comment.setComment_replyuserid(0);
			comment.setComment_replycommentid(0);
			communityTopicService.addComment(comment);
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			SendCode code = new SendCode(1, "评论成功!");
			JSONObject jso = JSONObject.fromObject(code);
			response.getWriter().write(jso.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * android上传头像
	 * 
	 * @param request
	 * @param file
	 */
	@RequestMapping(value = "/Android/user/uploadPicture", method = { RequestMethod.POST })
	public void uploadPicture(HttpServletResponse response, HttpServletRequest request, User user, @RequestParam MultipartFile file) {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			String path = UploadPicture.uploadHead(request, file, user.getUser_head());
			
			String hql = "update User set user_head='"+path+"' where user_id=" + user.getUser_id();
			userService.update(hql);
			
			SendCode code = new SendCode(1, "上传头像成功!");
			JSONObject jso = new JSONObject();
			jso.put("SendCode", code);
			jso.put("user_head", path);
			response.getWriter().write(jso.toString());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
