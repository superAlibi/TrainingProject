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

		// 判定为新增操作
		if (id == null) {
			request.setAttribute("type", type);
			request.getRequestDispatcher("custForm.jsp").forward(request, response);
		}

		// 判定为编辑操作
		else if (id != null && flag == null) {
			ctid = new CustomInfoDao();
			int customId = Integer.parseInt(id);
			cti = ctid.findCustom(customId);
			request.setAttribute("type", type);
			request.setAttribute("cust", cti);
			request.getRequestDispatcher("custForm.jsp").forward(request, response);
			ctid.close();
		}

		// 判定为删除操作
		else if (id != null && flag.equals("d")) {
			ctid = new CustomInfoDao();
			int customId = Integer.parseInt(id);
			int i = ctid.delCustomInfo(customId);
			if (i == 0)
				System.out.println("位置:" + request.getRemoteAddr() + "删除数据为零");
			else
				System.out.println("位置:" + request.getRemoteAddr() + "成功删除一条数据");
			request.setAttribute("type", type);
			request.getRequestDispatcher("CustList").forward(request, response);
			ctid.close();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 获取隐藏表单属性
		String act = request.getParameter("act");
		String cid = request.getParameter("cid");
		
		// 获取表单属性
		String type = request.getParameter("type");
		
		
		String customName = request.getParameter("name");
		String customLinkMan = request.getParameter("linkman");
		String customAddr = request.getParameter("addr");
		String customTel = request.getParameter("tel");
		String customEmail = request.getParameter("email");
		String customRemark = request.getParameter("remark");
		
		// 判定为修改
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
				System.out.println("位置:" + request.getRemoteAddr() + "修改数据失败");
			else
				System.out.println("位置:" + request.getRemoteAddr() + "修改一条数据");
			request.setAttribute("type", type);
			request.getRequestDispatcher("CustList").forward(request, response);
		}

		// 判定为添加
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
				System.out.println("位置:" + request.getRemoteAddr() + "数据添加失败");
			else
				System.out.println("位置:" + request.getRemoteAddr() + "数据添加成功");
			request.setAttribute("type", type);
			request.getRequestDispatcher("custForm.jsp").forward(request, response);

		} else
			System.out.println("检测到提交的表单错误");

	}
}
