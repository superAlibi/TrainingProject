package com.zhdt.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.SellDao;
import com.zhdt.entity.SellInfo;

/**
 * Servlet implementation class SellList
 */
@WebServlet("/SellList")
public class SellList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SellDao sed;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sed = new SellDao();
		List<SellInfo> sells = new ArrayList<>();
		sells = sed.findSells();
		request.setAttribute("sells", sells);
		request.getRequestDispatcher("SellList.jsp").forward(request, response);
		sed.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
