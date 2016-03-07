package com.banking.Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banking.DAO.SecurityDAO;
import com.banking.FormBean.LoginForm;

public class LoginAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginAction() {
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
		LoginForm lf=new LoginForm();
		
		String user_id = request.getParameter("user_id");
		lf.setUserId(user_id);
		String password=request.getParameter("password");
		lf.setPassword(password);
		System.out.println("user_id"+user_id);
		System.out.println("password"+password);
		
		try{
		     String[] sArr = new SecurityDAO().loginCheck(lf);
		     String customer_name = sArr[0];
		     String customer_id = sArr[1];
		     String ac_no = sArr[2];
		     System.out.println("in customer_id is.........."+customer_id);
		     System.out.println("in customer_name is.........."+customer_name);
		     System.out.println("in ac_no is.........."+ac_no);
		     
			if (customer_id == null)
			{
				session.setAttribute("status", "Invalid username&password");
				target = "./signin.jsp";				
			}
			else {
				session.setAttribute("customer_name",customer_name);
				session.setAttribute("customer_id",customer_id);
				session.setAttribute("ac_no",ac_no);
				target = "./welcome.jsp";
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
