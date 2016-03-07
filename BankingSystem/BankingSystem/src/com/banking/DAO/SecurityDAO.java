package com.banking.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.banking.DB.ConnectionFactory;
import com.banking.FormBean.LoginForm;

public class SecurityDAO {
	PreparedStatement pstmt,pstmt1,pstmt2;
	Statement stmt;
	ResultSet rs,rs1,rs2;
	Connection con;
	
	
	public SecurityDAO() throws IOException{
		// Database connection code
		con=ConnectionFactory.getConnection();
	}
	
	public String[] loginCheck(LoginForm lf)
	 {
		String access="";
		String sArr[] = new String[3];
		 
		 try{
			 con=ConnectionFactory.getConnection();
			 
			 System.out.println("in DAO implemetation connection"+con);	 
			 pstmt=con.prepareStatement("select User_id,Access from USERS where User_id=? and Password=?");
			 
			 String uid=lf.getUserId();
			 System.out.println("in Security DAO class..... user_id is"+uid);
			 String pwd=lf.getPassword();
			 System.out.println("in Security DAO class.....password is"+pwd);
			 
			 pstmt.setString(1, uid);
			 pstmt.setString(2, pwd);
			 
			 rs=pstmt.executeQuery();
			 
		 
			 if(rs.next())
			 {
				 String user_id = rs.getString(1);
				 access=rs.getString(2);
				 System.out.println("in result set login access is........."+access);
				 if(access.equalsIgnoreCase("customer")){
					 pstmt1=con.prepareStatement("select C_Name,USERS.CID from CUSTOMERS, USERS where CUSTOMERS.CID=USERS.CID and USERS.User_id=?");
					 pstmt1.setString(1, user_id);
					 rs1=pstmt1.executeQuery();
					 if(rs1.next())
					 {
						 sArr[0] = rs1.getString(1);
						 sArr[1] = rs1.getString(2);
						 System.out.println("in result set customer_name is........."+sArr[0]);
						 System.out.println("in result set customer_id is........."+sArr[1]);
						 pstmt2=con.prepareStatement("select A_NO from ACCOUNTS  where CID=?");
						 pstmt2.setString(1, sArr[1]);
						 rs2=pstmt2.executeQuery();
						 if(rs2.next())
						 {
							 sArr[2] = rs2.getString(1);
							 System.out.println("in result set AC_NO OF CUSTOMER is........."+sArr[2]);
						 }
					 }

				 } 
			 }
		
		 }
		 catch (Exception e) {
			 
			 e.printStackTrace();
			System.out.println("Exception raised"+e);
		}
		 return sArr;
	 }
}
	