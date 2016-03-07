package com.banking.Action;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.DAO.WithdrawDAO;
import com.banking.FormBean.WithdrawForm;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.examples.RestExamples;;

public class WithdrawAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WithdrawAction() {
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
		WithdrawForm wf=new WithdrawForm();
		
		String t_id = request.getParameter("t_id");
		wf.setT_id(t_id);
		String ac_no=request.getParameter("ac_no");
		wf.setAc_no(ac_no);
		
		float w_amount = Float.parseFloat(request.getParameter("w_amount"));
		wf.setW_amount(w_amount);
		String memo = request.getParameter("memo");
		wf.setMemo(memo);

		System.out.println("t_id...."+t_id);
		System.out.println("account_no...."+ac_no);
		System.out.println("w_amount...."+w_amount);
		System.out.println("meo...."+memo);
		
		try{
		     boolean flag = new WithdrawDAO().withdraw_amount(wf);
			if (flag == true)
			{
//				RestExamples.send_msg();
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
