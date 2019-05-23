package com.zhdt.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.StockInfoDao;
import com.zhdt.dao.CustomInfoDao;
import com.zhdt.entity.CustomInfo;
import com.zhdt.entity.StockInfo;

/**
 * �������ڹ���ɹ���Ϣ��servlet
 */
@WebServlet("/StockModify")
public class StockModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StockInfoDao stockid;
	CustomInfoDao customid;
	StockInfo stocki;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		if (id == null) {
			customid = new CustomInfoDao();
			// ��ѯtypeΪ2�Ŀͻ�����Ӧ�̣�
			List<CustomInfo> customs = customid.findCustoms(2);
			request.setAttribute("provider", customs);
			request.getRequestDispatcher("stockForm.jsp").forward(request, response);
			customid.close();
		} else if (id != null && flag == null) {
			stockid = new StockInfoDao();
			customid = new CustomInfoDao();
			// ��ѯtypeΪ2�Ŀͻ�����Ӧ�̣�
			List<CustomInfo> customs = customid.findCustoms(2);

			int sid = Integer.parseInt(id);
			stocki = stockid.findStockInfo(sid);

			request.setAttribute("provider", customs);
			request.setAttribute("stock", stocki);
			request.getRequestDispatcher("stockForm.jsp").forward(request, response);
			stockid.close();
			customid.close();
		} else if (id != null && flag.equals("d")) {
			stockid = new StockInfoDao();
			int sid = Integer.parseInt(id);
			int i = stockid.delStockInfo(sid);
			if (i == 0)
				System.out.println("û�вɹ���Ϣ��ɾ����");
			else
				System.out.println("��һ����Ʒ��Ϣ��ɾ��");
			response.sendRedirect("StockList");
			stockid.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// ��ȡҳ��Ĳ�������
		String act = request.getParameter("act");

		String sid = request.getParameter("sid");
		String cid = request.getParameter("cid");
		String totalprice = request.getParameter("totalprice");
		String webBuyer = request.getParameter("buyer");
		String webDate = request.getParameter("date");
		int Customid = Integer.parseInt(cid);
		double webTotalprice = Double.parseDouble(totalprice);
		customid = new CustomInfoDao();
		// �ж�Ϊ��Ӳ���
		if (act.equals("add")) {
			customid = new CustomInfoDao();
			stockid = new StockInfoDao();
			stocki = new StockInfo();
			List<CustomInfo> customs = customid.findCustoms(2);
			stocki.setCid(Customid);
			stocki.setDate(webDate);
			stocki.setTotalprice(webTotalprice);
			stocki.setBuyer(webBuyer);
			int i = stockid.addStockInfo(stocki);
			if (i == 0)
				System.out.println("��Ӳɹ���Ϣʧ��");
			else
				System.out.println("��Ӳɹ���Ϣ�ɹ�");
			request.setAttribute("provider", customs);
			request.getRequestDispatcher("stockForm.jsp").forward(request, response);
			stockid.close();
			customid.close();
		}
		// �ж�Ϊ�޸Ĳ���
		else if (act.equals("mdy")) {
			stockid = new StockInfoDao();
			stocki = new StockInfo();
			int webSid = Integer.parseInt(sid);
			stocki.setSid(webSid);
			stocki.setCid(Customid);
			stocki.setDate(webDate);
			stocki.setTotalprice(webTotalprice);
			stocki.setBuyer(webBuyer);
			int i = stockid.updateSrockInfo(stocki);
			if (i == 0)
				System.out.println("���²ɹ�����ʧ��");
			else
				System.out.println("���²ɹ����ݳɹ�");
			response.sendRedirect("StockList");
			stockid.close();
		}

	}

}
