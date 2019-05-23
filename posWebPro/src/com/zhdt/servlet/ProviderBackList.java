package com.zhdt.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhdt.dao.ProviderBackDao;
import com.zhdt.entity.ProviderBack;


@WebServlet("/ProviderBackList")
public class ProviderBackList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProviderBackDao pbd=new ProviderBackDao();
		List<ProviderBack> pbs=new ArrayList<>();
		pbs=pbd.findProviderBacks();
		
		request.setAttribute("probs", pbs);
		request.getRequestDispatcher("providerBackList.jsp").forward(request, response);
		pbd.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
