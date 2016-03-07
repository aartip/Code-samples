package com.banking.Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.DAO.TransferDAO;
import com.banking.FormBean.TransferForm;

public class TransferAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TransferAction() {
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
		TransferForm tf=new TransferForm();
		
		String t_id = request.getParameter("t_id");
		tf.setT_id(t_id);
		String from_ac_no=request.getParameter("from_ac_no");
		tf.setFrom_ac_no(from_ac_no);
		String to_ac_no=request.getParameter("to_ac_no");
		tf.setTo_ac_no(to_ac_no);
		float t_amount = Float.parseFloat(request.getParameter("t_amount"));
		tf.setT_amount(t_amount);
		String memo = request.getParameter("memo");
		tf.setMemo(memo);

		System.out.println("t_id...."+t_id);
		System.out.println("from_account_no...."+from_ac_no);
		System.out.println("to_account_no...."+to_ac_no);
		System.out.println("t_amount...."+t_amount);
		System.out.println("memo...."+memo);
		
		try{
		     boolean flag = new TransferDAO().transfer_amount(tf);
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
