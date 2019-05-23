package com.zhdt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zhdt.dao.StockInfoDao;
import com.zhdt.entity.StockInfo;

@WebServlet("/StockList")
public class StockList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StockInfoDao stid = new StockInfoDao();
		List<StockInfo> stis = stid.findAllStockInfo();
		request.setAttribute("stocks", stis);
		request.getRequestDispatcher("stockList.jsp").forward(request, response);
		stid.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
