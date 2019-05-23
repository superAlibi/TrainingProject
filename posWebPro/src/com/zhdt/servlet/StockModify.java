package com.zhdt.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.StockInfoDao;
import com.zhdt.dao.CustomInfoDao;
import com.zhdt.entity.CustomInfo;
import com.zhdt.entity.StockInfo;

/**
 * 该类用于管理采购信息的servlet
 */
@WebServlet("/StockModify")
public class StockModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StockInfoDao stockid;
	CustomInfoDao customid;
	StockInfo stocki;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		if (id == null) {
			customid = new CustomInfoDao();
			// 查询type为2的客户（供应商）
			List<CustomInfo> customs = customid.findCustoms(2);
			request.setAttribute("provider", customs);
			request.getRequestDispatcher("stockForm.jsp").forward(request, response);
			customid.close();
		} else if (id != null && flag == null) {
			stockid = new StockInfoDao();
			customid = new CustomInfoDao();
			// 查询type为2的客户（供应商）
			List<CustomInfo> customs = customid.findCustoms(2);

			int sid = Integer.parseInt(id);
			stocki = stockid.findStockInfo(sid);

			request.setAttribute("provider", customs);
			request.setAttribute("stock", stocki);
			request.getRequestDispatcher("stockForm.jsp").forward(request, response);
			stockid.close();
			customid.close();
		} else if (id != null && flag.equals("d")) {
			stockid = new StockInfoDao();
			int sid = Integer.parseInt(id);
			int i = stockid.delStockInfo(sid);
			if (i == 0)
				System.out.println("没有采购信息被删除！");
			else
				System.out.println("有一条商品信息被删除");
			response.sendRedirect("StockList");
			stockid.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 获取页面的操作属性
		String act = request.getParameter("act");

		String sid = request.getParameter("sid");
		String cid = request.getParameter("cid");
		String totalprice = request.getParameter("totalprice");
		String webBuyer = request.getParameter("buyer");
		String webDate = request.getParameter("date");
		int Customid = Integer.parseInt(cid);
		double webTotalprice = Double.parseDouble(totalprice);
		customid = new CustomInfoDao();
		// 判定为添加操作
		if (act.equals("add")) {
			customid = new CustomInfoDao();
			stockid = new StockInfoDao();
			stocki = new StockInfo();
			List<CustomInfo> customs = customid.findCustoms(2);
			stocki.setCid(Customid);
			stocki.setDate(webDate);
			stocki.setTotalprice(webTotalprice);
			stocki.setBuyer(webBuyer);
			int i = stockid.addStockInfo(stocki);
			if (i == 0)
				System.out.println("添加采购信息失败");
			else
				System.out.println("添加采购信息成功");
			request.setAttribute("provider", customs);
			request.getRequestDispatcher("stockForm.jsp").forward(request, response);
			stockid.close();
			customid.close();
		}
		// 判定为修改操作
		else if (act.equals("mdy")) {
			stockid = new StockInfoDao();
			stocki = new StockInfo();
			int webSid = Integer.parseInt(sid);
			stocki.setSid(webSid);
			stocki.setCid(Customid);
			stocki.setDate(webDate);
			stocki.setTotalprice(webTotalprice);
			stocki.setBuyer(webBuyer);
			int i = stockid.updateSrockInfo(stocki);
			if (i == 0)
				System.out.println("更新采购数据失败");
			else
				System.out.println("更新采购数据成功");
			response.sendRedirect("StockList");
			stockid.close();
		}

	}

}
