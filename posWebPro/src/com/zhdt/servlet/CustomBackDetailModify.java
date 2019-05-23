package com.zhdt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.CustomBackDao;
import com.zhdt.dao.CustomBackDetailDao;
import com.zhdt.dao.GoodDao;
import com.zhdt.entity.CustomBack;
import com.zhdt.entity.CustomBackDetail;
import com.zhdt.entity.GoodInfo;

@WebServlet("/CustomBackDetailModify")
public class CustomBackDetailModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomBackDetailDao custombdd;
	CustomBackDao sellbd;
	GoodDao gdd;
	CustomBackDetail custombd;
	CustomBack sellb;
	GoodInfo gdi;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String act = request.getParameter("act");
		String cbid = request.getParameter("cbid");
		int webCbid = Integer.parseInt(cbid);
		if (act == null) {
			custombdd = new CustomBackDetailDao();
			custombd = custombdd.findCustomBackDetail(webCbid);
			request.setAttribute("custombd", custombd);
			request.setAttribute("cbid", webCbid);
			request.getRequestDispatcher("CustomBackDetailList.jsp").forward(request, response);
			custombdd.close();
		} else if (act.equals("add")) {
			gdd = new GoodDao();
			List<GoodInfo> goods = gdd.findAllGoodsInfo();
			request.setAttribute("cbid", webCbid);
			request.setAttribute("goods", goods);
			request.getRequestDispatcher("CustomBackDetailFrom.jsp").forward(request, response);
			gdd.close();

		} else if (act.equals("mdy")) {
			custombdd = new CustomBackDetailDao();
			gdd = new GoodDao();
			custombd = custombdd.findCustomBackDetail(webCbid);
			List<GoodInfo> good = gdd.findGoodInfo(custombd.getGid());
			request.setAttribute("goods", good);
			request.setAttribute("custombd", custombd);
			request.getRequestDispatcher("CustomBackDetailFrom.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String act = request.getParameter("act");
		String cbdid = request.getParameter("cbdid");
		String cbid = request.getParameter("cbid");
		String gid = request.getParameter("gid");
		String totalprice = request.getParameter("totalprice");
		String oldTotalprice = request.getParameter("oldTotalprice");
		String price = request.getParameter("price");

		if (act.equals("add")) {
			System.out.println("已进入销售退货信息详细添加选项，获取到来CustomBackDetailFrom.jsp信息如下：");
			System.out.println("cbid=" + cbid + "  gid=" + gid + "  totalprice" + "  totalprice=" + totalprice
					+ "  price=" + price);
			if (cbid != null && gid != null && totalprice != null && price != null) {
				custombdd = new CustomBackDetailDao();
				custombd = new CustomBackDetail();
				int webPbid = Integer.parseInt(cbid);
				int webGid = Integer.parseInt(gid);
				int webTotalprice = Integer.parseInt(totalprice);
				double webPrice = Double.parseDouble(price);

				custombd.setCbid(webPbid);
				custombd.setGid(webGid);
				custombd.setAmount(webTotalprice);
				custombd.setPrice(webPrice);
				boolean ok = custombdd.addCustomBackDetail(custombd);
				if (ok)
					System.out.println("添加采购退货详细信息成功");
				else
					System.out.println("添加采购退货详细信息失败");
				custombd = custombdd.findCustomBackDetail(webPbid);
				request.setAttribute("providerbd", custombd);
				request.getRequestDispatcher("CustomBackDetailList.jsp").forward(request, response);
				custombdd.close();
			}
		} else if (act.equals("mdy")) {

			if (cbdid != null && cbid != null && gid != null && totalprice != null && oldTotalprice != null
					&& price != null) {
				custombdd = new CustomBackDetailDao();
				custombd = new CustomBackDetail();
				int webPbdid = Integer.parseInt(cbdid);
				int webPbid = Integer.parseInt(cbid);
				int webGid = Integer.parseInt(gid);
				int webTotalprice = Integer.parseInt(totalprice);
				int webOldTotalprice = Integer.parseInt(oldTotalprice);
				int newTotalprice = webTotalprice - webOldTotalprice;
				double webPrice = Double.parseDouble(price);

				custombd.setCbdid(webPbdid);
				custombd.setCbid(webPbid);
				custombd.setGid(webGid);
				custombd.setAmount(newTotalprice);
				custombd.setPrice(webPrice);
				boolean ok = custombdd.updateCustomBackDetail(custombd);
				if (ok)
					System.out.println("修改销售退货详细信息成功");
				else
					System.out.println("修改销售退货详细信息失败");
				custombd = custombdd.findCustomBackDetail(webPbid);
				request.setAttribute("custombd", custombd);
				request.getRequestDispatcher("CustomBackDetailList.jsp").forward(request, response);
				custombdd.close();
			}
		}
	}

}
