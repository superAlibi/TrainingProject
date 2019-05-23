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
 * *����һ���ṩ��Ʒ�б�����servlet��������Ʒ��Ϣ�Ĺ���
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 �½�
 */
@WebServlet("/GoodsModify")
public class GoodsModify extends HttpServlet {

	private static final long serialVersionUID = -2594657875216686165L;

	GoodDao gdid;
	goodsTypeDao gdtd;
	GoodInfo gdi;

	/**
	 * �÷������goodsList.jsp�ڵı��ύ������ �ж� servlet ����λ��Ϊ goodsList.jsp ��ҳ�治���ܻ�ȡ����������act/
	 * ���ܻ�ȡ��ֵ��id flag �����Դ���Ϊ�ж�ҳ��λ���ж����� ,��ҳ����ܽ��еĲ��� ====>>>>>>>.1��� 2.�༭ 3.ɾ��
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String flag = request.getParameter("flag");

		if (id == null) {
			// �ж�Ϊ��Ӳ�����ֱ��ת����goodsFrom.jsp�����Ʒ��ϸ��Ϣ
			gdtd = new goodsTypeDao();
			List<GoodType> gdts = gdtd.selectGoodsTypes();
			request.setAttribute("types", gdts);
			request.getRequestDispatcher("goodsFrom.jsp").forward(request, response);
			gdtd.close();
		}
		// �ж�Ϊ�޸Ĳ���
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
		// �ж�Ϊɾ��������ɾ��������ת����goodsList.jsp
		else if (id != null && flag.equals("d")) {
			gdid = new GoodDao();
			int i = gdid.deleteGoodsInfo(id);
			if (i == 0)
				System.out.println("û����Ʒ����ɾ��");
			else {
				Admin adm = (Admin) request.getSession().getAttribute("user_in_sess_key");
				System.out.println("�û�" + adm.getName() + "ɾ�������ݿ�һ����Ʒ��Ϣ����");
			}
			response.sendRedirect("GoodsList");
			gdid.close();
		}

	}

	// �÷������goodsFrom.jsp�ڵı��ύ������
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String act = request.getParameter("act");

		String webTid = request.getParameter("tid");
		String gid = request.getParameter("gid");

		String WebName = request.getParameter("name");
		String webUnit = request.getParameter("unit");
		// ��������������Ҫǿ��ת����������
		String pin = request.getParameter("pin");
		String pout = request.getParameter("pout");
		String amount = request.getParameter("amount");

		/**
		 ** �ж� servlet ����λ��Ϊ goodsFrom.jsp ��ҳ�治������id���Ժ�flag�����Դ���Ϊ�ж�ҳ��λ���ж����� ��ҳ����ܽ��еĲ���
		 * ====>>>>>>>.1.�޸�2.���
		 */

		// �ж�Ϊ�޸Ĳ���������Ϣ�����Ĳ���
		if (act.equals("mdy")) {
			gdid = new GoodDao();
			gdi = new GoodInfo();
// ǿ��ת������
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
				System.out.println("û�в����κ�����");
			} else {
				Admin ad = (Admin) request.getSession().getAttribute("user_in_sess_key");
				System.out.println("from :" + request.getLocalAddr() + ":" + ad.getName() + "eleted" + i + "data");
			}
			response.sendRedirect("GoodsList");
			gdid.close();
		}
		// �ж�Ϊ���ҳ�����
		else if (act.equals("add")) {
			gdid = new GoodDao();
			gdtd = new goodsTypeDao();
			gdi = new GoodInfo();
// ǿ��ת������
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
				System.out.println("û�в����κ�����");
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
