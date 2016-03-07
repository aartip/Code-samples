package com.banking.Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.DAO.DepositDAO;
import com.banking.FormBean.DepositForm;;

public class DepositAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DepositAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target ="";
		HttpSession session=request.getSession();
		DepositForm df=new DepositForm();
		
		String t_id = request.getParameter("t_id");
		df.setT_id(t_id);
		String ac_no=request.getParameter("ac_no");
		df.setAc_no(ac_no);
		float d_amount = Float.parseFloat(request.getParameter("d_amount"));
		df.setD_amount(d_amount);
		String memo = request.getParameter("memo");
		df.setMemo(memo);

		System.out.println("t_id...."+t_id);
		System.out.println("account_no...."+ac_no);
		System.out.println("d_amount...."+d_amount);
		System.out.println("memo...."+memo);
		
		try{
		     boolean flag = new DepositDAO().deposit_amount(df);
			if (flag == true)
			{
				session.setAttribute("status", "success");	
				target = "./success.jsp";
			}
			else {
				session.setAttribute("status", "error");
			}
		}catch (Exception e) {
		
			e.printStackTrace();
			
		}
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
