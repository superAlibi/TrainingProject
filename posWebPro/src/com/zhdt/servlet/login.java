package com.zhdt.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import com.zhdt.dao.loginDao;
import com.zhdt.entity.*;

/**
 * 该类是登录使用的servlet，登录后再session域中添加了一个admin对象
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 新建
 */
@WebServlet("/login")
public class login extends HttpServlet {

	private static final long serialVersionUID = -8248899972442046168L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//获取来自网页端的请求数据
		String webUname = request.getParameter("uname");
		String webPwd = request.getParameter("pwd");
		String remember = request.getParameter("remember");
		Cookie cookie;
		// 判断用户名输入是否为空
		if (webUname != null) {
			// 通过从web获取到的登录名在数据库查找数据，如果在数据库查到的数据为空则返回继续登录，如果不为空则进行下一步判断
			loginDao lgd = new loginDao(webUname);
			Admin adm = lgd.getAdmin();

			if (adm != null) {
				// 判断用户身份操作，身份确认则进行跳转到指定
				if (adm.getPwd().equals(webPwd)) {
					request.setCharacterEncoding("utf8");
					// 判断用户是否记住密码
					if (remember != null && remember.equals("on")) {
						cookie = new Cookie("remember", webUname + "-" + webPwd);
						cookie.setMaxAge(60 * 60 * 24);
						response.addCookie(cookie);
					}
					request.getSession().setAttribute("user_in_sess_key", adm);
					request.getRequestDispatcher("index.jsp").forward(request, response);
					System.out.println(webUname + "--自:" + request.getRemoteAddr() + "--登录");
				} else {
					response.sendRedirect("login.jsp");
					System.out.println("用户登录密码错误或账号输入错误");
				}
			} else {
				System.out.println("不存在该用户用户");
				response.sendRedirect("login.jsp");
			}
			lgd.close();

		} else {
			response.sendRedirect("login.jsp");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
