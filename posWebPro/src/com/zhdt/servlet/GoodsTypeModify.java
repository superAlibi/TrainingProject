package com.zhdt.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.goodsTypeDao;
import com.zhdt.entity.GoodType;

/**
 * 因为本servlet为两个页面提供servlet服务，分别为： 1.goodsTypeList.jsp 2.goodsTypeFrom.jsp
 ** 所以本类将对操作页面的位置进行判断 这至关重要
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 新建
 */
@WebServlet("/GoodsTypeModify")
public class GoodsTypeModify extends HttpServlet {
	private static final long serialVersionUID = -2253651900219559148L;
	goodsTypeDao gdtd;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

//接收操作属性
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		/**
		 *** 判断 servlet 请求位置为 goodsTypeList.jsp 该页面不可能获取到的属性有act/webTid/webName 该页面可能进行的操作
		 * ====>>>>>>>.1添加 2.编辑 3.删除
		 */
		// 判断为添加操作
		if (id == null) {
			response.sendRedirect("goodsTypeFrom.jsp");
		}
		// 判断为编辑操作
		else if (id != null && flag == null) {
			gdtd = new goodsTypeDao();
			GoodType gdt = gdtd.selectGoodsType(id);
			request.setAttribute("type", gdt);
			request.getRequestDispatcher("goodsTypeFrom.jsp").forward(request, response);
			gdtd.close();
		}
		// 判断为删除操作
		else if (id != null && flag.equals("d")) {
			gdtd = new goodsTypeDao();
			int i = gdtd.delGoodsType(id);
			if (i == 0)
				System.out.println("商品类型数据删除失败！");
			else
				System.out.println("商品类型数据删除成功！");
			response.sendRedirect("GoodsTypeList");
			gdtd.close();
		}

	}

	/**
	 ** 判断 servlet 请求位置为 goodsTypeFrom.jsp 该页面不可能有id属性和flag属性以此作为判断页面位置判断依据
	 * 该页面可能进行的操作 ====>>>>>>>.1.修改2.添加
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String act = request.getParameter("act");
		String webTid = request.getParameter("tid");
		String webName = request.getParameter("name");
		if (act.equals("mdy")) {
			if (webTid != null && webName != null) {
				gdtd = new goodsTypeDao();
				int i = gdtd.updateGoodsType(webTid, webName);
				if (i == 0) {
					System.out.println("商品类型数据更新失败！");
				} else
					System.out.println("商品类型数据更新成功！");
			} else {
				gdtd = new goodsTypeDao();
				GoodType gdt = gdtd.selectGoodsType(webTid);
				request.setAttribute("type", gdt);
				request.getRequestDispatcher("goodsTypeFrom.jsp").forward(request, response);
				gdtd.close();
				System.out.println("用户输入了空信息,重新返回修改");
			}
			response.sendRedirect("GoodsTypeList");
		} else if (act.equals("add")) {
			if (webTid != null && webName != null) {
				gdtd = new goodsTypeDao();
				int i = gdtd.addGoodsType(webTid, webName);
				if (i == 0)
					System.out.println("数据库添加商品类型失败");
				else
					System.out.println("数据库添加了一条商品类型");
			} else {
				System.out.println("添加的数据不完全！添加未成功！");
			}
			response.sendRedirect("goodsTypeFrom.jsp");
		} else
			System.out.println("啥也没做！");

		gdtd.close();
	}
}
