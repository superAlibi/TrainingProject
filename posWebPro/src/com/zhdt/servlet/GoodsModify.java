package com.zhdt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.GoodDao;
import com.zhdt.dao.goodsTypeDao;
import com.zhdt.entity.Admin;
import com.zhdt.entity.GoodType;
import com.zhdt.entity.GoodInfo;

/**
 * *这是一个提供商品列表管理的servlet，用于商品信息的管理
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 新建
 */
@WebServlet("/GoodsModify")
public class GoodsModify extends HttpServlet {

	private static final long serialVersionUID = -2594657875216686165L;

	GoodDao gdid;
	goodsTypeDao gdtd;
	GoodInfo gdi;

	/**
	 * 该方法针对goodsList.jsp内的表单提交方法， 判断 servlet 请求位置为 goodsList.jsp 该页面不可能获取到的属性有act/
	 * 可能获取的值有id flag 属性以此作为判断页面位置判断依据 ,该页面可能进行的操作 ====>>>>>>>.1添加 2.编辑 3.删除
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String flag = request.getParameter("flag");

		if (id == null) {
			// 判定为添加操作，直接转发到goodsFrom.jsp添加商品详细信息
			gdtd = new goodsTypeDao();
			List<GoodType> gdts = gdtd.selectGoodsTypes();
			request.setAttribute("types", gdts);
			request.getRequestDispatcher("goodsFrom.jsp").forward(request, response);
			gdtd.close();
		}
		// 判定为修改操作
		else if (id != null && flag == null) {
			int webId=Integer.parseInt(id);
			gdid = new GoodDao();
			gdtd = new goodsTypeDao();
			gdi = gdid.findGoodsInfo(webId);
			List<GoodType> gdts = gdtd.selectGoodsTypes();
			request.setAttribute("types", gdts);
			request.setAttribute("goods", gdi);
			request.getRequestDispatcher("goodsFrom.jsp").forward(request, response);
			gdid.close();
			gdtd.close();
		}
		// 判定为删除操作，删除后请求转发到goodsList.jsp
		else if (id != null && flag.equals("d")) {
			gdid = new GoodDao();
			int i = gdid.deleteGoodsInfo(id);
			if (i == 0)
				System.out.println("没有商品数据删除");
			else {
				Admin adm = (Admin) request.getSession().getAttribute("user_in_sess_key");
				System.out.println("用户" + adm.getName() + "删除了数据库一条商品信息数据");
			}
			response.sendRedirect("GoodsList");
			gdid.close();
		}

	}

	// 该方法针对goodsFrom.jsp内的表单提交方法，
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String act = request.getParameter("act");

		String webTid = request.getParameter("tid");
		String gid = request.getParameter("gid");

		String WebName = request.getParameter("name");
		String webUnit = request.getParameter("unit");
		// 以下数据类型需要强制转换数据类型
		String pin = request.getParameter("pin");
		String pout = request.getParameter("pout");
		String amount = request.getParameter("amount");

		/**
		 ** 判断 servlet 请求位置为 goodsFrom.jsp 该页面不可能有id属性和flag属性以此作为判断页面位置判断依据 该页面可能进行的操作
		 * ====>>>>>>>.1.修改2.添加
		 */

		// 判断为修改操作将对信息做更改操作
		if (act.equals("mdy")) {
			gdid = new GoodDao();
			gdi = new GoodInfo();
// 强制转换数据
			double webPin = Double.parseDouble(pin);
			double webPout = Double.parseDouble(pout);
			int webAmount = Integer.parseInt(amount);
			int webGid = Integer.parseInt(gid);

			gdi.setGid(webGid);
			gdi.setName(WebName);
			gdi.setTid(webTid);
			gdi.setUnit(webUnit);
			gdi.setPin(webPin);
			gdi.setPout(webPout);
			gdi.setAmount(webAmount);

			int i = gdid.updateGoodsInfo(gdi);
			if (i == 0) {
				System.out.println("没有插入任何数据");
			} else {
				Admin ad = (Admin) request.getSession().getAttribute("user_in_sess_key");
				System.out.println("from :" + request.getLocalAddr() + ":" + ad.getName() + "eleted" + i + "data");
			}
			response.sendRedirect("GoodsList");
			gdid.close();
		}
		// 判断为添加页面操作
		else if (act.equals("add")) {
			gdid = new GoodDao();
			gdtd = new goodsTypeDao();
			gdi = new GoodInfo();
// 强制转换数据
			double webPin = Double.parseDouble(pin);
			double webPout = Double.parseDouble(pout);
			int webAmount = Integer.parseInt(amount);

			gdi.setName(WebName);
			gdi.setTid(webTid);
			gdi.setUnit(webUnit);
			gdi.setPin(webPin);
			gdi.setPout(webPout);
			gdi.setAmount(webAmount);

			int i = gdid.addGoodsInfo(gdi);
			if (i == 0)
				System.out.println("没有插入任何数据");
			else {
				Admin ad = (Admin) request.getSession().getAttribute("user_in_sess_key");
				System.out.println("from :" + request.getLocalAddr() + " : " + ad.getName() + "added one data");
			}

			List<GoodType> gdts = gdtd.selectGoodsTypes();
			request.setAttribute("types", gdts);
			request.getRequestDispatcher("goodsFrom.jsp").forward(request, response);
			gdid.close();
			gdtd.close();

		}

	}

}
