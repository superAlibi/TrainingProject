package com.zhdt.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.CustomInfoDao;
import com.zhdt.dao.SellDao;
import com.zhdt.entity.CustomInfo;
import com.zhdt.entity.SellInfo;

@WebServlet("/SellModify")
public class SellModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomInfoDao customid;
	SellDao selld;
	SellInfo sell;
	CustomInfo custom;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 接收操作属性
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		if (id == null) {
			customid = new CustomInfoDao();
			List<CustomInfo> customs = new ArrayList<>();
			customs = customid.findCustoms(1);
			request.setAttribute("provider", customs);
			request.getRequestDispatcher("SellFrom.jsp").forward(request, response);
			customid.close();
		} else if (id != null && flag == null) {
			int webSid = Integer.parseInt(id);
			selld = new SellDao();
			customid = new CustomInfoDao();
			List<CustomInfo> custom = new ArrayList<>();
			sell = selld.findSellInfo(webSid);
			custom = customid.findCustomList(sell.getCid());

			request.setAttribute("provider", custom);
			request.setAttribute("sell", sell);
			request.getRequestDispatcher("SellFrom.jsp").forward(request, response);
			selld.close();
			customid.close();
		} else if (id != null && flag.equals("d")) {
			if (id != null) {
				int webSid = Integer.parseInt(id);
				selld = new SellDao();
				int i = selld.delSellInfo(webSid);
				if (i == 0)
					System.out.println("删除销售信息失败");
				else
					System.out.println("删除销售信息成功");
				selld.close();
			}
			response.sendRedirect("SellList");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");

		String sid = request.getParameter("sid");
		String cid = request.getParameter("cid");
		String date = request.getParameter("date");
		String totalprice = request.getParameter("totalprice");
		String buyer = request.getParameter("buyer");

		if (act.equals("add")) {
			if (sid != null && totalprice != null && date != null && cid != null) {
				int i = 0;
				int webCid = Integer.parseInt(cid);
				double webTotalprice = Double.parseDouble(totalprice);
				selld = new SellDao();
				sell.setCid(webCid);
				sell.setDate(date);
				sell.setTotalprice(webTotalprice);
				sell.setBuyer(buyer);
				i = selld.addSell(sell);
				if (i == 0)
					System.out.println("添加销售信息失败");
				else
					System.out.println("添加销售信息成功");
				customid = new CustomInfoDao();
				List<CustomInfo> customs = new ArrayList<>();
				customs = customid.findCustoms(1);
				request.setAttribute("provider", customs);
				request.getRequestDispatcher("SellFrom.jsp").forward(request, response);
				selld.close();
				customid.close();
			}

		} else if (act.equals("mdy")) {
			if (sid != null && totalprice != null && date != null && cid != null) {
				int i = 0;
				int webCid = Integer.parseInt(cid);
				int webSid = Integer.parseInt(sid);
				double webTotalprice = Double.parseDouble(totalprice);
				selld = new SellDao();
				sell = new SellInfo();
				sell.setSid(webSid);
				sell.setCid(webCid);
				sell.setDate(date);
				sell.setTotalprice(webTotalprice);
				sell.setBuyer(buyer);
				i = selld.updateSell(sell);
				if (i == 0)
					System.out.println("修改销售信息失败");
				else
					System.out.println("修改销售信息成功");
				response.sendRedirect("SellList");
				selld.close();
			}
			System.out.println("传入的数据含有空值，");
		}
	}

}
