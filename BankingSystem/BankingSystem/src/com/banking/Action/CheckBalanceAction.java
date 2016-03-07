package com.banking.Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.DAO.CheckBalanceDAO;
import com.banking.DAO.SecurityDAO;
import com.banking.FormBean.CheckBalanceForm;

public class CheckBalanceAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CheckBalanceAction() {
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
		HttpSession session=request.getSession();
		String target ="";
		String customer_id = request.getParameter("customer_id");
		CheckBalanceForm cbf = new CheckBalanceForm();
		cbf.setCustomerId(customer_id);
		System.out.println("customer_id...."+customer_id);
		
		try{
		     String[] sArr = new CheckBalanceDAO().bal_check(cbf);
		     String cid = sArr[0];
		     String account_no = sArr[1];
		     float curr_bal = Float.parseFloat(sArr[2]);
		     System.out.println("in customer_id is.........."+cid);
		     System.out.println("in account_no is.........."+account_no);
		     System.out.println("in curr_bal is.........."+curr_bal);
		     
		     session.setAttribute("cid", cid);
		     session.setAttribute("acc_no", account_no);
		     session.setAttribute("curr_bal", curr_bal);
		     target = "./check_balance.jsp";

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
