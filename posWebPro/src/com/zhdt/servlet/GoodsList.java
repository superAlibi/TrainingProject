package com.zhdt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.GoodDao;
import com.zhdt.entity.*;

/**
 * *����һ���ṩ��Ʒ�б���ϸ��Ϣ���࣬������ҳ��goodsList.jsp�ṩ��Ϣ
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 �½�
 */
@WebServlet("/GoodsList")
public class GoodsList extends HttpServlet {

	private static final long serialVersionUID = -3897623146507447360L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GoodDao gdid = new GoodDao();
		List<GoodInfo> gdis = gdid.findAllGoodsInfo();
		request.setAttribute("goods", gdis);
		request.getRequestDispatcher("goodsList.jsp").forward(request, response);
		gdid.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
