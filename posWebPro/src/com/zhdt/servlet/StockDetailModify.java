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
 * *����һ��Ҫ��������ҳ���������� : *1.��һ��ҳ�棺StockList.jsp ,��ҳ������ת����servlet�����
 * *2.�ڶ���ҳ�棺StockDetail.jsp����ҳ��ᴫ�������źţ���Ӻ��޸�
 * *3.������ҳ�棺StockDetailForm.jsp����ҳ���������ֲ�������Ӻ��޸�
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-23 �½�
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
//�ж�ҳ����Դ
		// 1.�ж���������stockList.jsp�Ĳ鿴�ɹ���ϸ��Ϣ�Ĳ���
		if (act == null) {
			// ��ȡ�ɹ�id����Ϊ�ɹ�id��ɹ���ϸidһһ��Ӧ�����Բɹ�id��ɹ���ϸid�϶�����ֻ��һ���ҳɶԴ���
			stockdd = new StockDetailDao();
			stockd = stockdd.findStockDetail(webSid);
			request.setAttribute("sid", sid);
			request.setAttribute("stockd", stockd);
			request.getRequestDispatcher("stockDetailList.jsp").forward(request, response);
			stockdd.close();
		}
		// 2.�ж���������StockDetail.jsp������ź�
		else if (act.equals("add")) {
			godd = new GoodDao();
			List<GoodInfo> goods = godd.findAllGoodsInfo();
			request.setAttribute("sid", webSid);
			request.setAttribute("goods", goods);
			request.getRequestDispatcher("stockDetailForm.jsp").forward(request, response);
			stockid.close();
		}
		// 3.�ж���������StockDetail.jsp���޸��ź�
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
					System.out.println("��Ӳɹ���ϸ��Ϣ�ɹ�");
				else
					System.out.println("��Ӳɹ���ϸ��Ϣʧ��");

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
				//���¿����ṩǿ��ĸĴ���������Ϊ�����ã�������Ϊ��Ӳɹ���ϸ��Ϣ��Ʒ��������£��ṩ�����������˷���ִ�б������dao��
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
					System.out.println("���²ɹ���ϸ��Ϣ�ɹ�");
				else
					System.out.println("���²ɹ���ϸ��Ϣʧ��");

				stockd = stockdd.findStockDetail(webSid);
				request.setAttribute("sid", sid);
				request.setAttribute("stockd", stockd);
				request.getRequestDispatcher("stockDetailList.jsp").forward(request, response);
				stockdd.close();
			}
		}

	}

}
