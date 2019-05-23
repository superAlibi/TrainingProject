package com.zhdt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.goodsTypeDao;
import com.zhdt.entity.GoodType;

/**
 * *这是商品类型列表的servlet，用于向goodsTypeList.jsp提供信息
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 新建
 */
@WebServlet("/GoodsTypeList")
public class GoodsTypeList extends HttpServlet {

	private static final long serialVersionUID = -4274538775396793768L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		goodsTypeDao gdtd = new goodsTypeDao();
		List<GoodType> godTypes = gdtd.selectGoodsTypes();
		request.setAttribute("goods", godTypes);
		request.getRequestDispatcher("goodsTypeList.jsp").forward(request, response);
		gdtd.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
