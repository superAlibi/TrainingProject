package com.zhdt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.GoodDao;
import com.zhdt.dao.ProviderBackDao;
import com.zhdt.dao.ProviderBackDetailDao;
import com.zhdt.entity.GoodInfo;
import com.zhdt.entity.ProviderBack;
import com.zhdt.entity.ProviderBackDetail;

@WebServlet("/ProviderBackDetailModify")
public class ProviderBackDetailModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProviderBackDetailDao providbdd;
	ProviderBackDao providerbdao;
	GoodDao gdd;
	ProviderBackDetail providerbd;
	ProviderBack pback;
	GoodInfo gdi;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String act = request.getParameter("act");
		String pbid = request.getParameter("pbid");
		int webPbid = Integer.parseInt(pbid);
		if (act == null) {
			providbdd = new ProviderBackDetailDao();
			providerbd = providbdd.findProviderBackDetail(webPbid);
			request.setAttribute("providerbd", providerbd);
			request.setAttribute("pbid", webPbid);
			request.getRequestDispatcher("ProviderBackDetailList.jsp").forward(request, response);
			providbdd.close();
		} else if (act.equals("add")) {
			gdd = new GoodDao();
			List<GoodInfo> goods = gdd.findAllGoodsInfo();
			request.setAttribute("pbid", webPbid);
			request.setAttribute("goods", goods);
			request.getRequestDispatcher("ProviderBackDetailFrom.jsp").forward(request, response);
			gdd.close();

		} else if (act.equals("mdy")) {
			providbdd = new ProviderBackDetailDao();
			gdd = new GoodDao();
			providerbd = providbdd.findProviderBackDetail(webPbid);
			List<GoodInfo> good = gdd.findGoodInfo(providerbd.getGid());
			request.setAttribute("goods", good);
			request.setAttribute("stockbd", providerbd);
			request.getRequestDispatcher("ProviderBackDetailFrom.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String act = request.getParameter("act");
		String pbdid = request.getParameter("pbdid");
		String pbid = request.getParameter("pbid");
		String gid = request.getParameter("gid");
		String totalprice = request.getParameter("totalprice");
		String oldTotalprice = request.getParameter("oldTotalprice");
		String price = request.getParameter("price");

		if (act.equals("add")) {
			if (pbid != null && gid != null && totalprice != null && price != null) {
				providbdd = new ProviderBackDetailDao();
				providerbd = new ProviderBackDetail();
				int webPbid = Integer.parseInt(pbid);
				int webGid = Integer.parseInt(gid);
				int webTotalprice = Integer.parseInt(totalprice);
				double webPrice = Double.parseDouble(price);

				providerbd.setPbid(webPbid);
				providerbd.setGid(webGid);
				providerbd.setAmount(webTotalprice);
				providerbd.setPrice(webPrice);
				boolean ok = providbdd.addProviderBackDetail(providerbd);
				if (ok)
					System.out.println("添加采购退货详细信息成功");
				else
					System.out.println("添加采购退货详细信息失败");
				providerbd = providbdd.findProviderBackDetail(webPbid);
				request.setAttribute("providerbd", providerbd);
				request.getRequestDispatcher("ProviderBackDetailList.jsp").forward(request, response);
				providbdd.close();
			}
		} else if (act.equals("mdy")) {
			if (pbid != null && gid != null && totalprice != null && oldTotalprice != null && price != null) {
				providbdd = new ProviderBackDetailDao();
				providerbd = new ProviderBackDetail();
				int webPbdid = Integer.parseInt(pbdid);
				int webPbid = Integer.parseInt(pbid);
				int webGid = Integer.parseInt(gid);
				int webTotalprice = Integer.parseInt(totalprice);
				int webOldTotalprice = Integer.parseInt(oldTotalprice);
				int newTotalprice = webTotalprice - webOldTotalprice;
				double webPrice = Double.parseDouble(price);

				providerbd.setPbdid(webPbdid);
				providerbd.setPbid(webPbid);
				providerbd.setGid(webGid);
				providerbd.setAmount(newTotalprice);
				providerbd.setPrice(webPrice);
				boolean ok = providbdd.updateProviderBackDetail(providerbd);
				if (ok)
					System.out.println("修改采购退货详细信息成功");
				else
					System.out.println("修改采购退货详细信息失败");
				providerbd = providbdd.findProviderBackDetail(webPbid);
				request.setAttribute("providerbd", providerbd);
				request.getRequestDispatcher("ProviderBackDetailList.jsp").forward(request, response);
				providbdd.close();
			}
		}
	}

}
