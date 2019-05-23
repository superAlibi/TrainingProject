package com.zhdt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.ProviderBackDao;
import com.zhdt.dao.StockInfoDao;
import com.zhdt.entity.ProviderBack;
import com.zhdt.entity.StockInfo;

@WebServlet("/ProviderBackModify")
public class ProviderBackModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProviderBack provib;
	StockInfoDao stockid;
	ProviderBackDao provibd;
	StockInfo stocki;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 接收操作属性
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		if (id == null) {
			stockid = new StockInfoDao();
			List<StockInfo> stocks = stockid.findAllStockInfo();
			request.setAttribute("stocks", stocks);
			request.getRequestDispatcher("ProviderBackForm.jsp").forward(request, response);
			stockid.close();
		} else if (id != null && flag == null) {
			int pbid = Integer.parseInt(id);
			provibd = new ProviderBackDao();
			stockid = new StockInfoDao();
			provib = new ProviderBack();
			provib = provibd.findProviderBack(pbid);
			int sid = provib.getSid();
			List<StockInfo> stock = stockid.findStockInfoList(sid);
			request.setAttribute("stocks", stock);
			request.setAttribute("probs", provib);
			request.getRequestDispatcher("ProviderBackForm.jsp").forward(request, response);
			provibd.close();
			stockid.close();
		} else if (id != null && flag.equals("d")) {
			provibd = new ProviderBackDao();
			int pbid = Integer.parseInt(id);
			int i = provibd.delProviderBack(pbid);
			if (i == 0)
				System.out.println("删除采购退货信息失败");
			else
				System.out.println("删除采购退货信息成功");
			response.sendRedirect("ProviderBackList");
			provibd.close();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 接收操作属性
		String act = request.getParameter("act");

		String pbid = request.getParameter("pbid");
		String sid = request.getParameter("sid");
		String date = request.getParameter("date");
		String totalprice = request.getParameter("totalprice");

		if (act.equals("add")) {
			if (sid != null && totalprice != null) {
				// 转换数据类型
				int webSid = Integer.parseInt(sid);
				double webTotalprice = Double.parseDouble(totalprice);
				stockid = new StockInfoDao();
				provibd = new ProviderBackDao();
				stocki = stockid.findStockInfo(webSid);
				provib = new ProviderBack();

				provib.setSid(webSid);
				provib.setCid(stocki.getCid());
				provib.setDate(date);
				provib.setTotalprice(webTotalprice);

				int i = provibd.addProviderBack(provib);
				if (i == 0)
					System.out.println("添加退货信息失败");
				else
					System.out.println("添加退货信息成功");

				List<StockInfo> stocks = stockid.findAllStockInfo();
				request.setAttribute("stocks", stocks);
				request.getRequestDispatcher("ProviderBackForm.jsp").forward(request, response);
				provibd.close();
				stockid.close();

			}
		}

		else if (act.equals("mdy")) {
			// 转换数据类型
			int webPbid = Integer.parseInt(pbid);
			int webSid = Integer.parseInt(sid);
			double webTotalprice = Double.parseDouble(totalprice);
			stockid = new StockInfoDao();
			provibd = new ProviderBackDao();
			stocki = stockid.findStockInfo(webSid);
			provib = new ProviderBack();

			provib.setPbid(webPbid);
			provib.setSid(webSid);
			provib.setCid(stocki.getCid());
			provib.setDate(date);
			provib.setTotalprice(webTotalprice);

			int i = provibd.updateProviderBack(provib);
			if (i == 0)
				System.out.println("修改退货信息失败");
			else
				System.out.println("修改退货信息成功");

			List<StockInfo> stocks = stockid.findAllStockInfo();
			request.setAttribute("stocks", stocks);
			request.getRequestDispatcher("ProviderBackList").forward(request, response);
			provibd.close();
			stockid.close();

		}
	}

}
