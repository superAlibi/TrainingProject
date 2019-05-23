package com.zhdt.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import com.zhdt.dao.loginDao;
import com.zhdt.entity.*;

/**
 * �����ǵ�¼ʹ�õ�servlet����¼����session���������һ��admin����
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 �½�
 */
@WebServlet("/login")
public class login extends HttpServlet {

	private static final long serialVersionUID = -8248899972442046168L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//��ȡ������ҳ�˵���������
		String webUname = request.getParameter("uname");
		String webPwd = request.getParameter("pwd");
		String remember = request.getParameter("remember");
		Cookie cookie;
		// �ж��û��������Ƿ�Ϊ��
		if (webUname != null) {
			// ͨ����web��ȡ���ĵ�¼�������ݿ�������ݣ���������ݿ�鵽������Ϊ���򷵻ؼ�����¼�������Ϊ���������һ���ж�
			loginDao lgd = new loginDao(webUname);
			Admin adm = lgd.getAdmin();

			if (adm != null) {
				// �ж��û���ݲ��������ȷ���������ת��ָ��
				if (adm.getPwd().equals(webPwd)) {
					request.setCharacterEncoding("utf8");
					// �ж��û��Ƿ��ס����
					if (remember != null && remember.equals("on")) {
						cookie = new Cookie("remember", webUname + "-" + webPwd);
						cookie.setMaxAge(60 * 60 * 24);
						response.addCookie(cookie);
					}
					request.getSession().setAttribute("user_in_sess_key", adm);
					request.getRequestDispatcher("index.jsp").forward(request, response);
					System.out.println(webUname + "--��:" + request.getRemoteAddr() + "--��¼");
				} else {
					response.sendRedirect("login.jsp");
					System.out.println("�û���¼���������˺��������");
				}
			} else {
				System.out.println("�����ڸ��û��û�");
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
