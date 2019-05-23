package com.zhdt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.GoodDao;
import com.zhdt.dao.SellDao;
import com.zhdt.dao.SellDetailDao;
import com.zhdt.entity.GoodInfo;
import com.zhdt.entity.SellDetail;

/**
 * Servlet implementation class SellDetailModify
 */
@WebServlet("/SellDetailModify")
public class SellDetailModify extends HttpServlet {

	private static final long serialVersionUID = 1L;
	SellDetailDao selldd;
	SellDao selld;
	GoodDao godd;
	SellDetail sell;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String act = request.getParameter("act");
		String sid = request.getParameter("sid");
		String gid = request.getParameter("gid");
		int webSid = Integer.parseInt(sid);
		if (act == null) {
			selldd = new SellDetailDao();
			sell = selldd.findSellDetail(webSid);
			request.setAttribute("sid", sid);
			request.setAttribute("selld", sell);
			request.getRequestDispatcher("SellDetailList.jsp").forward(request, response);
			selldd.close();
		}

		else if (act.equals("add")) {
			godd = new GoodDao();
			List<GoodInfo> goods = godd.findAllGoodsInfo();
			request.setAttribute("sid", webSid);
			request.setAttribute("goods", goods);
			request.getRequestDispatcher("SellDetailForm.jsp").forward(request, response);
			godd.close();
		}

		else if (act.equals("mdy")) {
			int webGid = Integer.parseInt(gid);
			godd = new GoodDao();
			selldd = new SellDetailDao();
			sell = selldd.findSellDetail(webSid);
			List<GoodInfo> goods = godd.findGoodInfo(webGid);
			request.setAttribute("selld", sell);
			request.setAttribute("sid", webSid);
			request.setAttribute("goods", goods);
			request.getRequestDispatcher("SellDetailForm.jsp").forward(request, response);
			godd.close();
			selldd.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String act = request.getParameter("act");
		String sdid = request.getParameter("sdid");
		String sid = request.getParameter("sid");
		String gid = request.getParameter("gid");
//		String oldGid = request.getParameter("oldGid");
		String totalprice = request.getParameter("totalprice");
		String oldTotalprice = request.getParameter("oldTotalprice");
		String price = request.getParameter("price");
		if (act.equals("add")) {
			if (sid != null && gid != null && totalprice != null && price != null) {
				int webSid = Integer.parseInt(sid);
				int webGid = Integer.parseInt(gid);
				int webTotalprice = Integer.parseInt(totalprice);
				double webPrice = Double.parseDouble(price);
				selldd = new SellDetailDao();
				sell = new SellDetail();
				sell.setSid(webSid);
				sell.setGid(webGid);
				sell.setAmount(webTotalprice);
				sell.setPrice(webPrice);
				boolean ok = selldd.addSellDetail(sell);
				if (ok)
					System.out.println("添加销售详细信息成功");
				else
					System.out.println("添加销售详细信息失败");

				sell = selldd.findSellDetail(webSid);
				request.setAttribute("sid", sid);
				request.setAttribute("selld", sell);
				request.getRequestDispatcher("SellDetailList.jsp").forward(request, response);
				selldd.close();
			}
		}

		else if (act.equals("mdy")) {
			if (sdid != null && sid != null && gid != null && totalprice != null && price != null) {
				int webSdid = Integer.parseInt(sdid);
				int webSid = Integer.parseInt(sid);
				int webGid = Integer.parseInt(gid);
//				int webOldGid = Integer.parseInt(oldGid);
				int webTotalprice = Integer.parseInt(totalprice);
				int webOldTotalprice = Integer.parseInt(oldTotalprice);
				int newTotalprice = webTotalprice - webOldTotalprice;
				double webPrice = Double.parseDouble(price);
				// 以下可以提供强大的改错能力，作为保留用，该条件为添加采购详细信息商品填错的情况下，提供纠错能力，此方法执行必须更改dao层
//				if (webGid == webOldGid) {
//
//				} else {
//
//				}
				selldd = new SellDetailDao();
				sell = new SellDetail();
				sell.setSdid(webSdid);
				sell.setSid(webSid);
				sell.setGid(webGid);
				sell.setAmount(newTotalprice);
				sell.setPrice(webPrice);
				boolean ok = selldd.updateSellDetail(sell);
				if (ok)
					System.out.println("更新销售详细信息成功");
				else
					System.out.println("更新销售详细信息失败");

				sell = selldd.findSellDetail(webSid);
				request.setAttribute("sid", sid);
				request.setAttribute("selld", sell);
				request.getRequestDispatcher("SellDetailList.jsp").forward(request, response);
				selldd.close();
			}
		}

	}

}
