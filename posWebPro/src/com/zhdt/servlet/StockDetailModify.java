package com.zhdt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.GoodDao;
import com.zhdt.dao.StockDetailDao;
import com.zhdt.dao.StockInfoDao;
import com.zhdt.entity.GoodInfo;
import com.zhdt.entity.StockDetail;

/**
 * *本类一共要处理三个页面如下请求 : *1.第一个页面：StockList.jsp ,该页面是跳转到本servlet的起点
 * *2.第二个页面：StockDetail.jsp，该页面会传入两种信号，添加和修改
 * *3.第三个页面：StockDetailForm.jsp，该页面会进行两种操作，添加和修改
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-23 新建
 */
@WebServlet("/StockDetailModify")
public class StockDetailModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StockDetailDao stockdd;
	StockInfoDao stockid;
	GoodDao godd;
	StockDetail stockd;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String act = request.getParameter("act");
		String sid = request.getParameter("sid");
		String gid = request.getParameter("gid");
		int webSid = Integer.parseInt(sid);
//判断页面来源
		// 1.判断请求来自stockList.jsp的查看采购详细信息的操作
		if (act == null) {
			// 获取采购id，因为采购id与采购详细id一一对应，所以采购id与采购详细id肯定有且只有一个且成对存在
			stockdd = new StockDetailDao();
			stockd = stockdd.findStockDetail(webSid);
			request.setAttribute("sid", sid);
			request.setAttribute("stockd", stockd);
			request.getRequestDispatcher("stockDetailList.jsp").forward(request, response);
			stockdd.close();
		}
		// 2.判断请求来自StockDetail.jsp的添加信号
		else if (act.equals("add")) {
			godd = new GoodDao();
			List<GoodInfo> goods = godd.findAllGoodsInfo();
			request.setAttribute("sid", webSid);
			request.setAttribute("goods", goods);
			request.getRequestDispatcher("stockDetailForm.jsp").forward(request, response);
			stockid.close();
		}
		// 3.判断请求来自StockDetail.jsp的修改信号
		else if (act.equals("mdy")) {
			int webGid=Integer.parseInt(gid);
			godd = new GoodDao();
			stockdd = new StockDetailDao();
			stockd = stockdd.findStockDetail(webSid);
			List<GoodInfo> goods = godd.findGoodInfo(webGid);
			request.setAttribute("stockd", stockd);
			request.setAttribute("sid", webSid);
			request.setAttribute("goods", goods);
			request.getRequestDispatcher("stockDetailForm.jsp").forward(request, response);
			stockid.close();
			godd.close();
			stockdd.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String act = request.getParameter("act");
		String sdid = request.getParameter("sdid");
		String sid = request.getParameter("sid");
		String gid = request.getParameter("gid");
		String oldGid = request.getParameter("oldGid");
		String totalprice = request.getParameter("totalprice");
		String oldTotalprice = request.getParameter("oldTotalprice");
		String price = request.getParameter("price");
		if (act.equals("add")) {
			if (sid != null && gid != null && totalprice != null && price != null) {
				int webSid = Integer.parseInt(sid);
				int webGid = Integer.parseInt(gid);
				int webTotalprice = Integer.parseInt(totalprice);
				double webPrice = Double.parseDouble(price);
				stockdd = new StockDetailDao();
				stockd = new StockDetail();
				stockd.setSid(webSid);
				stockd.setGid(webGid);
				stockd.setAmount(webTotalprice);
				stockd.setPrice(webPrice);
				boolean ok = stockdd.addStockDetail(stockd);
				if (ok)
					System.out.println("添加采购详细信息成功");
				else
					System.out.println("添加采购详细信息失败");

				stockd = stockdd.findStockDetail(webSid);
				request.setAttribute("sid", sid);
				request.setAttribute("stockd", stockd);
				request.getRequestDispatcher("stockDetailList.jsp").forward(request, response);
				stockdd.close();
			}
		}

		else if (act.equals("mdy")) {
			if (sdid != null && sid != null && gid != null && totalprice != null && price != null) {
				int webSdid = Integer.parseInt(sdid);
				int webSid = Integer.parseInt(sid);
				int webGid = Integer.parseInt(gid);
				int webOldGid=Integer.parseInt(oldGid);
				int webTotalprice = Integer.parseInt(totalprice);
				int webOldTotalprice = Integer.parseInt(oldTotalprice);
				int newTotalprice = webTotalprice-webOldTotalprice ;
				double webPrice = Double.parseDouble(price);
				//以下可以提供强大的改错能力，作为保留用，该条件为添加采购详细信息商品填错的情况下，提供纠错能力，此方法执行必须更改dao层
				if(webGid==webOldGid) {
					
				}else {
					
				}
				stockdd = new StockDetailDao();
				stockd = new StockDetail();
				stockd.setSdid(webSdid);
				stockd.setSid(webSid);
				stockd.setGid(webGid);
				stockd.setAmount(newTotalprice);
				stockd.setPrice(webPrice);
				boolean ok = stockdd.updateStockDetail(stockd);
				if (ok)
					System.out.println("更新采购详细信息成功");
				else
					System.out.println("更新采购详细信息失败");

				stockd = stockdd.findStockDetail(webSid);
				request.setAttribute("sid", sid);
				request.setAttribute("stockd", stockd);
				request.getRequestDispatcher("stockDetailList.jsp").forward(request, response);
				stockdd.close();
			}
		}

	}

}
