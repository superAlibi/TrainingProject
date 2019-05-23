package com.zhdt.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.CustomInfoDao;
import com.zhdt.entity.CustomInfo;

/**
 * Servlet implementation class CustModify
 */
@WebServlet("/CustModify")
public class CustModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomInfoDao ctid;
	CustomInfo cti;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");

		// �ж�Ϊ��������
		if (id == null) {
			request.setAttribute("type", type);
			request.getRequestDispatcher("custForm.jsp").forward(request, response);
		}

		// �ж�Ϊ�༭����
		else if (id != null && flag == null) {
			ctid = new CustomInfoDao();
			int customId = Integer.parseInt(id);
			cti = ctid.findCustom(customId);
			request.setAttribute("type", type);
			request.setAttribute("cust", cti);
			request.getRequestDispatcher("custForm.jsp").forward(request, response);
			ctid.close();
		}

		// �ж�Ϊɾ������
		else if (id != null && flag.equals("d")) {
			ctid = new CustomInfoDao();
			int customId = Integer.parseInt(id);
			int i = ctid.delCustomInfo(customId);
			if (i == 0)
				System.out.println("λ��:" + request.getRemoteAddr() + "ɾ������Ϊ��");
			else
				System.out.println("λ��:" + request.getRemoteAddr() + "�ɹ�ɾ��һ������");
			request.setAttribute("type", type);
			request.getRequestDispatcher("CustList").forward(request, response);
			ctid.close();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// ��ȡ���ر�����
		String act = request.getParameter("act");
		String cid = request.getParameter("cid");
		
		// ��ȡ������
		String type = request.getParameter("type");
		
		
		String customName = request.getParameter("name");
		String customLinkMan = request.getParameter("linkman");
		String customAddr = request.getParameter("addr");
		String customTel = request.getParameter("tel");
		String customEmail = request.getParameter("email");
		String customRemark = request.getParameter("remark");
		
		// �ж�Ϊ�޸�
		if (act.equals("mdy")) {
			int customType = Integer.parseInt(type);
			int customCid = Integer.parseInt(cid);
			cti = new CustomInfo();
			ctid = new CustomInfoDao();
			cti.setCid(customCid);
			cti.setName(customName);
			cti.setLinkman(customLinkMan);
			cti.setAddr(customAddr);
			cti.setTel(customTel);
			cti.setEmail(customEmail);
			cti.setRemark(customRemark);
			cti.setType(customType);

			int i = ctid.updateCustomInfo(cti);
			if (i == 0)
				System.out.println("λ��:" + request.getRemoteAddr() + "�޸�����ʧ��");
			else
				System.out.println("λ��:" + request.getRemoteAddr() + "�޸�һ������");
			request.setAttribute("type", type);
			request.getRequestDispatcher("CustList").forward(request, response);
		}

		// �ж�Ϊ���
		else if (act.equals("add")) {
			int customType = Integer.parseInt(type);
			ctid = new CustomInfoDao();
			cti = new CustomInfo();
			cti.setName(customName);
			cti.setLinkman(customLinkMan);
			cti.setAddr(customAddr);
			cti.setTel(customTel);
			cti.setEmail(customEmail);
			cti.setRemark(customRemark);
			cti.setType(customType);
			int i = ctid.addCustomInfo(cti);
			if (i == 0)
				System.out.println("λ��:" + request.getRemoteAddr() + "�������ʧ��");
			else
				System.out.println("λ��:" + request.getRemoteAddr() + "������ӳɹ�");
			request.setAttribute("type", type);
			request.getRequestDispatcher("custForm.jsp").forward(request, response);

		} else
			System.out.println("��⵽�ύ�ı�����");

	}
}
