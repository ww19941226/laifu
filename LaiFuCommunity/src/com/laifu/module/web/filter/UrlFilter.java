package com.laifu.module.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.laifu.module.entity.User;

/**
 * Servlet Filter implementation class UrlFilter
 */
@WebFilter("/UrlFilter")
public class UrlFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public UrlFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String url = req.getRequestURI();

		/*******************************************************************************************************/
		// market/index
		if (url.indexOf("/error") == -1
				&& url.indexOf("/user/login") == -1
				&& url.indexOf("/sysadmin/login") == -1
				&& !(url.indexOf("/css/") != -1 || url.indexOf("/js/") != -1
						|| url.indexOf("/images/") != -1
						|| url.indexOf("/lib/") != -1
						|| url.indexOf("/tld/") != -1 || url
						.indexOf("/Android/") != -1)) {
			if (url.indexOf("/sysadmin/") != -1
					|| url.indexOf("/extjs/index") != -1
					|| url.indexOf("/marketManage") != -1
					|| url.indexOf("/property/") != -1
					|| url.indexOf("/usertype/") != -1
					|| url.indexOf("/people/") != -1
					|| url.indexOf("/inc/") != -1
					|| url.indexOf("/comadmin/") != -1) {
				if (url.indexOf("/sysadmin/sysadmin_login") == -1
						&& session.getAttribute("admin") == null
						&& url.indexOf("/sysadmin/exit") == -1
						&& url.indexOf("/sysadmin/checkAccountAndPassword") == -1) {
					rep.getWriter()
							.write("<script>window.open('"
									+ req.getContextPath()
									+ "/sysadmin/sysadmin_login','_top')</script>");
					return;
				}
			} else if (url.indexOf("/user/") != -1
					|| url.equals(req.getContextPath() + "/")
					|| url.indexOf("/index") != -1
					|| url.indexOf("/market") != -1) {
				if (url.indexOf("/user/user_login") == -1
						&& url.indexOf("/user/exit") == -1
						&& url.indexOf("/user/code") == -1
						&& url.indexOf("/user/toturn") == -1
						&& url.indexOf("/user/checkCode") == -1
						&& url.indexOf("/user/checkAccountAndPassword") == -1
						&& url.indexOf("/user/register") == -1
						&& url.indexOf("/user/user_register") == -1
						&& session.getAttribute("user") == null) {
					rep.getWriter().write(
							"<script>window.open('" + req.getContextPath()
									+ "/user/user_login','_top')</script>");
					return;
				}
			}
		}

		/*******************************************************************************************************/

		if (url.indexOf("user/topic/add") == -1
				&& url.indexOf("/property/property_paymentcontent/exceladd") == -1
				&& url.indexOf("/uploadPicture") == -1
				&& url.indexOf("/Android/") == -1
				&& req.getAttribute("filter") == null
				&& (session.getAttribute("user") != null || session
						.getAttribute("admin") != null)) {
			req.setAttribute("request", req);
			req.setAttribute("response", rep);
			req.getRequestDispatcher("/gotoFilter").forward(req, rep);
			return;
		}

		/*******************************************************************************************************/

		/**
		 * 拦截
		 */
		if (url.indexOf("/user/") != -1) {
			if (url.indexOf("/user/user_financial") != -1
					|| url.indexOf("/user/user_jiaofeijilu") != -1
					|| url.indexOf("/user/user_notify") != -1
					|| url.indexOf("/user/user_pay") != -1
					|| url.indexOf("/user/user_repair") != -1
					|| url.indexOf("/user/user_complains") != -1) {
				User user = (User) session.getAttribute("user");
				if (user.getUser_card() == null) {
					rep.getWriter().write(
							"<script>alert('请先完善个人信息……')</script>");
					rep.getWriter().write(
							"<script>window.open('" + req.getContextPath()
									+ "/user/userInfor','_self')</script>");
					return;
				} else if (user.getUser_checkstate() == 0) {
					rep.getWriter().write("<script>alert('等待审核……')</script>");
					rep.getWriter().write(
							"<script>window.open('" + req.getContextPath()
									+ "/user/userInfor','_self')</script>");
					return;
				} else if (user.getUser_checkstate() == 2) {
					rep.getWriter().write(
							"<script>alert('你的信息审核不通过！')</script>");
					rep.getWriter().write(
							"<script>window.open('" + req.getContextPath()
									+ "/user/userInfor','_self')</script>");
					return;
				}
			}
		}

		/*******************************************************************************************************/

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
