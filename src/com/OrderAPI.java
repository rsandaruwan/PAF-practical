package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderAPI
 */
@WebServlet("/OrderAPI")
public class OrderAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Order ordObj = new Order();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String output = ordObj.insertOrders(
				request.getParameter("orderId"),
				request.getParameter("userId"),
				request.getParameter("fundingBodiesId"),
				request.getParameter("researchProjectId"),
				request.getParameter("researchProjectFundId"),
				request.getParameter("orderTotalAmount"));
				
		response.getWriter().write(output);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = ordObj.updateOrders(
				paras.get("hidoIdSave").toString(),
				paras.get("orderId").toString(),
				paras.get("userId").toString(),
				paras.get("fundingBodiesId").toString(), 
				paras.get("researchProjectId").toString(), 
				paras.get("researchProjectFundId").toString(),
				paras.get("orderTotalAmount").toString());
		response.getWriter().write(output);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = ordObj.deleteOrders(paras.get("oId").toString());
		response.getWriter().write(output);
	}

	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}


}
