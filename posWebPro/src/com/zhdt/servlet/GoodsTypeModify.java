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
 * ��Ϊ��servletΪ����ҳ���ṩservlet���񣬷ֱ�Ϊ�� 1.goodsTypeList.jsp 2.goodsTypeFrom.jsp
 ** ���Ա��ཫ�Բ���ҳ���λ�ý����ж� ��������Ҫ
 * 
 * @author alibi
 * @since JDK1.8
 * @history 2018-12-8 �½�
 */
@WebServlet("/GoodsTypeModify")
public class GoodsTypeModify extends HttpServlet {
	private static final long serialVersionUID = -2253651900219559148L;
	goodsTypeDao gdtd;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

//���ղ�������
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		/**
		 *** �ж� servlet ����λ��Ϊ goodsTypeList.jsp ��ҳ�治���ܻ�ȡ����������act/webTid/webName ��ҳ����ܽ��еĲ���
		 * ====>>>>>>>.1��� 2.�༭ 3.ɾ��
		 */
		// �ж�Ϊ��Ӳ���
		if (id == null) {
			response.sendRedirect("goodsTypeFrom.jsp");
		}
		// �ж�Ϊ�༭����
		else if (id != null && flag == null) {
			gdtd = new goodsTypeDao();
			GoodType gdt = gdtd.selectGoodsType(id);
			request.setAttribute("type", gdt);
			request.getRequestDispatcher("goodsTypeFrom.jsp").forward(request, response);
			gdtd.close();
		}
		// �ж�Ϊɾ������
		else if (id != null && flag.equals("d")) {
			gdtd = new goodsTypeDao();
			int i = gdtd.delGoodsType(id);
			if (i == 0)
				System.out.println("��Ʒ��������ɾ��ʧ�ܣ�");
			else
				System.out.println("��Ʒ��������ɾ���ɹ���");
			response.sendRedirect("GoodsTypeList");
			gdtd.close();
		}

	}

	/**
	 ** �ж� servlet ����λ��Ϊ goodsTypeFrom.jsp ��ҳ�治������id���Ժ�flag�����Դ���Ϊ�ж�ҳ��λ���ж�����
	 * ��ҳ����ܽ��еĲ��� ====>>>>>>>.1.�޸�2.���
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
					System.out.println("��Ʒ�������ݸ���ʧ�ܣ�");
				} else
					System.out.println("��Ʒ�������ݸ��³ɹ���");
			} else {
				gdtd = new goodsTypeDao();
				GoodType gdt = gdtd.selectGoodsType(webTid);
				request.setAttribute("type", gdt);
				request.getRequestDispatcher("goodsTypeFrom.jsp").forward(request, response);
				gdtd.close();
				System.out.println("�û������˿���Ϣ,���·����޸�");
			}
			response.sendRedirect("GoodsTypeList");
		} else if (act.equals("add")) {
			if (webTid != null && webName != null) {
				gdtd = new goodsTypeDao();
				int i = gdtd.addGoodsType(webTid, webName);
				if (i == 0)
					System.out.println("���ݿ������Ʒ����ʧ��");
				else
					System.out.println("���ݿ������һ����Ʒ����");
			} else {
				System.out.println("��ӵ����ݲ���ȫ�����δ�ɹ���");
			}
			response.sendRedirect("goodsTypeFrom.jsp");
		} else
			System.out.println("ɶҲû����");

		gdtd.close();
	}
}
