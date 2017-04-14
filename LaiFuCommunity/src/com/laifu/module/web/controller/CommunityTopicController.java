package com.laifu.module.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.laifu.common.Constants;
import com.laifu.common.utils.MultiUploadPicture;
import com.laifu.module.entity.Comment;
import com.laifu.module.entity.Praise;
import com.laifu.module.entity.Topic;
import com.laifu.module.entity.User;
import com.laifu.module.service.CommunityTopicService;
import com.laifu.module.service.UserService;
import com.laifu.module.support.editor.DateEditor;
import com.laifu.module.vo.AuthorVo;
import com.laifu.module.vo.CommentVo;
import com.laifu.module.vo.TopicVo;

/**
 * @ClassName: CommunityTopicController
 * @Description: 小区话题Controller
 * @author ：chenjianshuo
 * @date 2016-9-8 上午11:25:08
 * 
 */
@Controller
@RequestMapping("/user/topic")
public class CommunityTopicController {
	@Resource(name = "CommunityTopicService")
	private CommunityTopicService communityTopicService;

	@Autowired
	@Qualifier("UserService")
	private UserService userService;

	/**
	 * 设置通用属性
	 * 
	 * @param model
	 */
	private void setCommonData(Model model) {
		// 设置通用属性
	}

	/**
	 * 
	 * @Title: list
	 * @Description: 跟据类型获取的话题信息
	 * @param @param request
	 * @param @param model
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(method = { RequestMethod.GET })
	public String getCommunityTopicList(HttpServletRequest request) {
		// String t = request.getParameter("communityTopic");
		/*
		 * int topicType = Integer.parseInt(t); List<Topic> list =
		 * communityTopicService.getTopicTypeAll(topicType);// 存所有话题信息
		 * List<String> userList = new ArrayList<String>();// 存放发表话题人的信息
		 * List<String> communityList = new ArrayList<String>();// 存放查询到所有小区名字
		 * List<User> userlist = new ArrayList<User>();// 存放发表话题人的信息 List<User>
		 * praiseuserlist = new ArrayList<User>();// 存放点赞人的信息 List<Community>
		 * communitylist = new ArrayList<Community>();// 存放社区信息 List
		 * commentList1 = new ArrayList();// 存评论信息，包括评论内容，评论时间，评论人头像，昵称 List
		 * praiseUserIdList = new ArrayList();// 存点赞信息，包括点赞用户id，话题id,点赞用户头像
		 * 
		 * for (int n = 0; n < list.size(); n++) { List<User> userlist1 = new
		 * ArrayList<User>();// 存放评论人的信息 List<Praise> praiseList =
		 * communityTopicService
		 * .getPraiseUserIdByTopicId(list.get(n).getTopic_id());
		 * userlist.add(communityTopicService.getUser(list.get(n)
		 * .getTopic_userid()));
		 * communitylist.add(communityTopicService.getCommunity(userlist
		 * .get(n).getUser_community()));
		 * userList.add(userlist.get(n).getUser_head());
		 * userList.add(userlist.get(n).getUser_nickname());
		 * communityList.add(communitylist.get(n).getCommunity_name());
		 * List<Comment> commentslist = communityTopicService
		 * .getCommentByTopicId(list.get(n).getTopic_id()); if
		 * (praiseList.size() > 0) { List praiseTopicIdList = new ArrayList();
		 * praiseTopicIdList.add(list.get(n).getTopic_id()); List praise = new
		 * ArrayList(); List praiseuser = new ArrayList(); for (int a = 0; a <
		 * praiseList.size(); a++) {
		 * praiseuserlist.add(communityTopicService.getUser(praiseList
		 * .get(a).getPraise_userid()));
		 * praise.add(praiseList.get(a).getPraise_userid());
		 * 
		 * praiseuser.add(praiseuserlist.get(a).getUser_head()); //
		 * praiseuser.add(praiseuserlist.get(a).getUser_nickname()); }
		 * praiseTopicIdList.add(praiseList.size());
		 * praiseTopicIdList.add(praise); praiseTopicIdList.add(praiseuser);
		 * praiseUserIdList.add(praiseTopicIdList); } //
		 * System.out.println(praiseUserIdList); if (commentslist.size() > 0) {
		 * for (int m = 0; m < commentslist.size(); m++) { List commentList =
		 * new ArrayList(); List userList1 = new ArrayList();
		 * commentList.add(list.get(n).getTopic_id());
		 * commentList.add(commentslist.size());
		 * commentList.add(commentslist.get(m).getComment_content());
		 * commentList.add(commentslist.get(m).getComment_time() + "");
		 * userlist1.add(communityTopicService.getUser(commentslist
		 * .get(m).getComment_user()));
		 * userList1.add(userlist1.get(m).getUser_head());
		 * userList1.add(userlist1.get(m).getUser_nickname());
		 * commentList.add(userList1); commentList1.add(commentList);
		 * System.out.println(commentList1);
		 * 
		 * } } } List datetimeList = new ArrayList(); for (int i = 0; i <
		 * list.size(); i++) { datetimeList.add(list.get(i).getTopic_datetime()
		 * + "");
		 * 
		 * } // System.out.println(userList); //
		 * System.out.println(communityList); JSONArray jsonArray =
		 * JSONArray.fromObject(list); JSONArray jsonArray2 =
		 * JSONArray.fromObject(commentList1); JSONArray jsonArray3 =
		 * JSONArray.fromObject(praiseUserIdList);
		 * request.setAttribute("topic_datetime", datetimeList);
		 * request.setAttribute("topicList", jsonArray);
		 * request.setAttribute("communityList", communityList);
		 * request.setAttribute("userList", userList);
		 * request.setAttribute("commentList", jsonArray2);
		 * request.setAttribute("praiseUserIdList", jsonArray3);
		 */
		return "/user/topic/add";

	}

	/**
	 * 
	 * @Title: toAdd
	 * @Description: TODO用户添加话题
	 * @param @param model
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public String toAdd(Model model) {
		if (!model.containsAttribute(Constants.COMMAND)) {
			model.addAttribute(Constants.COMMAND, new Topic());
		}
		setCommonData(model);
		return "user/topic/add";
	}

	/**
	 * 进入别人的话题空间
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/other_topic", method = { RequestMethod.GET })
	public String other_topic(HttpServletRequest request) {
		User user = userService.get(Integer.parseInt(request
				.getParameter("user_id")));
		request.setAttribute("author", user);
		return "user/topic/other_topic";
	}

	/**
	 * 
	 * @Title: Add
	 * @Description: TODO用户添加话题
	 * @param @param model
	 * @param @param topic
	 * @param @param request
	 * @param @param file
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public String Add(@ModelAttribute("topic") Topic topic,
			HttpServletRequest request, @RequestParam MultipartFile[] file) {
		User user = (User) request.getSession().getAttribute("user");
		int id = user.getUser_id();
		topic.setTopic_userid(id);
		topic.setTopic_datetime(new Date());
		MultiUploadPicture multiPicture = new MultiUploadPicture();
		String filename = multiPicture.MutiUpload(request, file);
		topic.setTopicpicture_path(filename);
		communityTopicService.save(topic);
		return "redirect:/user/topic";
	}

	/**
	 * 
	 * @Title: getAllSelfTopic
	 * @Description: TODO用户查看自己的所有话题和评论
	 * @param @param request
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/{id}/getAllSelfTopic", method = { RequestMethod.GET })
	public String getAllSelfTopic(HttpServletRequest request,
			@PathVariable Integer id) {

		return "user/topic/add";

	}

	/**
	 * 
	 * @Title: deleteTopic
	 * @Description: 删除本人话题和删除该话题的评论
	 * @param @param id
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/{topic_id}/deleteTopic", method = { RequestMethod.GET })
	public void deleteTopic(HttpServletResponse response,
			@PathVariable Integer topic_id, Topic topic) {
		try {
			communityTopicService.deleteTopic(topic_id);
			response.getWriter().write("success");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: commentTopic
	 * @Description: 话题评论
	 * @param @param topic_id
	 * @param @param comment
	 * @param @param request
	 * @param @param user_id
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */

	@RequestMapping(value = "/{topic_id}/{reply_userid}/{parent_id}/commentTopic", method = { RequestMethod.POST })
	public void commentTopic(@PathVariable int topic_id,
			@PathVariable int reply_userid, @PathVariable int parent_id,
			@ModelAttribute("command") Comment comment,
			HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		int user_id = user.getUser_id();
		comment.setComment_time(new Date());
		comment.setComment_topic(topic_id);
		comment.setComment_user(user_id);
		comment.setComment_replyuserid(reply_userid);
		comment.setComment_replycommentid(parent_id);
		communityTopicService.addComment(comment);
		List<List<CommentVo>> list = new ArrayList<List<CommentVo>>();
		try {
			list = communityTopicService.getComment(topic_id);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			JSONArray jsonArray = JSONArray.fromObject(list);
			response.getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/{comment_id}/deleteComment", method = { RequestMethod.GET })
	public void deleteComment(@PathVariable int comment_id) {
		// topic_id=topic.getTopic_id();
		communityTopicService.deleteComment(comment_id);
		// communityTopicService.deleteCommentByTopicid(topic_id);
		System.out.println("delete Comment success");
	}

	/**
	 * 
	 * @Title: PraiseTopic
	 * @Description: TODO话题点赞
	 * @param @param topic_id
	 * @param @param request
	 * @param @param user_id
	 * @param @param praise
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/{topic_id}/praiseTopic", method = { RequestMethod.GET })
	public void PraiseTopic(@PathVariable int topic_id,
			HttpServletResponse response, HttpServletRequest request) {
		Praise praise = new Praise();
		User user = (User) request.getSession().getAttribute("user");
		int user_id = user.getUser_id();
		praise.setPraise_topicid(topic_id);
		praise.setPraise_userid(user_id);
		communityTopicService.addPraise(praise);
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
	 * 
	 * @Title: deletePrise
	 * @Description: 根据用户id和话题id取消点赞
	 * @param @param topic_id
	 * @param @param request
	 * @param @param user_id
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/{topic_id}/deletePraise", method = { RequestMethod.GET })
	public void deletePrise(@PathVariable int topic_id,
			HttpServletResponse response, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
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

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	/***********************************************************************************************/

	@RequestMapping(value = "/{pn}/{size}/getTopicList", method = { RequestMethod.GET })
	public void getTopicList(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int pn,
			@PathVariable int size) {
		try {
			if (request.getParameter("communityTopic") != null) {
				List<TopicVo> list = communityTopicService.getTopicVo(
						((User) request.getSession().getAttribute("user"))
								.getUser_id(), pn, size, request
								.getParameter("communityTopic"));
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/plain; charset=UTF-8");
				JSONArray jsonArray = JSONArray.fromObject(list);
				response.getWriter().write(jsonArray.toString());
				// response.getWriter().write("hello");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/{user_id}/{pn}/{size}/getTopicListByUser", method = { RequestMethod.GET })
	public void getTopicListByUser(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int pn,
			@PathVariable int size, @PathVariable int user_id) {
		try {
			List<TopicVo> list = communityTopicService.getTopicVoByUser(
					user_id, pn, size, ((User) request.getSession()
							.getAttribute("user")).getUser_id());
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=UTF-8");
			JSONArray jsonArray = JSONArray.fromObject(list);
			response.getWriter().write(jsonArray.toString());
			// response.getWriter().write("hello");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
