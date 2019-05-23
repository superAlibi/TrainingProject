package com.zhdt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.CustomBackDao;
import com.zhdt.dao.SellDao;
import com.zhdt.entity.CustomBack;
import com.zhdt.entity.SellInfo;

/**
 * *这是一个对客户退货信息的数据库操作层
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-21 新建
 */

@WebServlet("/CustomBackModify")
public class CustomBackModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomBackDao custombd;
	SellDao selld;
	CustomBack customb;
	SellInfo selli;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		if (id == null) {
			selld = new SellDao();
			List<SellInfo> sells = selld.findSells();
			request.setAttribute("sells", sells);
			request.getRequestDispatcher("CustomBackFrom.jsp").forward(request, response);
		}

		else if (id != null && flag == null) {
			custombd = new CustomBackDao();
			selld = new SellDao();
			int cbid = Integer.parseInt(id);
			customb = custombd.findCstomBack(cbid);
			List<SellInfo> sell = selld.findSell(customb.getSid());

			request.setAttribute("sells", sell);
			request.setAttribute("custom", customb);

			request.getRequestDispatcher("CustomBackFrom.jsp").forward(request, response);

		}

		else if (id != null && flag.equals("d")) {
			custombd = new CustomBackDao();
			int cbid = Integer.parseInt(id);
			int i = 0;
			i = custombd.delCustomBack(cbid);
			if (i == 0)
				System.out.println("删除客户退货信息失败");
			else
				System.out.println("删除客户退货信息成功");
			response.sendRedirect("CustomBackList");
			custombd.close();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");

		String cbid = request.getParameter("cbid");
		String sid = request.getParameter("sid");
		String date = request.getParameter("date");
		String totalprice = request.getParameter("totalprice");
		if (act.equals("add")) {
			// 判定数据是否为空，然后进行数据转换
			if (sid != null && date != null && totalprice != null) {
				int webSid = Integer.parseInt(sid);
				double webTotalprice = Double.parseDouble(totalprice);
				selld = new SellDao();
				custombd = new CustomBackDao();
				customb = new CustomBack();
				selli = selld.findSellInfo(webSid);
				customb.setCbid(webSid);
				customb.setSid(webSid);
				customb.setCid(selli.getCid());
				customb.setDate(date);
				customb.setTotalprice(webTotalprice);

				int i = custombd.addCustomBack(customb);

				if (i == 0)
					System.out.println("修改客户退货信息失败");
				else
					System.out.println("修改客户退货信息成功");

				List<SellInfo> sells = selld.findSells();
				request.setAttribute("sells", sells);
				request.getRequestDispatcher("CustomBackFrom.jsp").forward(request, response);
				selld.close();
				custombd.close();
			}

		}

		else if (act.equals("mdy")) {
			if (cbid != null && sid != null && date != null && totalprice != null) {
				int webCbid = Integer.parseInt(cbid);
				int webSid = Integer.parseInt(sid);
				double webTotalprice = Double.parseDouble(totalprice);
				selld = new SellDao();
				custombd = new CustomBackDao();
				selli = new SellInfo();
				customb = new CustomBack();
				selli = selld.findSellInfo(webSid);
				customb.setCbid(webCbid);
				customb.setSid(webSid);
				customb.setCid(selli.getCid());
				customb.setDate(date);
				customb.setTotalprice(webTotalprice);
				int i = custombd.updateCustomBack(customb);
				if (i == 0)
					System.out.println("修改客户退货信息失败");
				else
					System.out.println("修改客户退货信息成功");

				response.sendRedirect("CustomBackList");
			}
		}

	}

}
