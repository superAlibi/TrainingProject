package com.zhdt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.CustomInfoDao;
import com.zhdt.entity.CustomInfo;

/**
 * Servlet implementation class CustList
 */
@WebServlet("/CustList")
public class CustList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		
		CustomInfoDao cstid = new CustomInfoDao();
		int customType = Integer.parseInt(type);
		List<CustomInfo> customs = cstid.findCustoms(customType);
		request.setAttribute("type", type);
		request.setAttribute("custs", customs);
		request.getRequestDispatcher("custList.jsp").forward(request, response);

		cstid.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
