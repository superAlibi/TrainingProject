package com.zhdt.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhdt.entity.Admin;

/**
 * *该类用于用户登出，该类在session域中去除在loginServlet在session域中添加的admin对象
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 新建
 */
@WebServlet("/LoginOut")
public class LoginOut extends HttpServlet {
	private static final long serialVersionUID = -5206843337286432245L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession htSe = request.getSession(true);
		Admin use = (Admin) htSe.getAttribute("user_in_sess_key");
		System.out.println( use.getName() +"退出登录");
		htSe.removeAttribute("user_in_sess_key");
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
