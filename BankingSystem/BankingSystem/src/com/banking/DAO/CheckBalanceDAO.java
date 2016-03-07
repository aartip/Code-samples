package com.banking.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.banking.DB.ConnectionFactory;
import com.banking.FormBean.CheckBalanceForm;;

public class CheckBalanceDAO {
	PreparedStatement pstmt,pstmt1;
	Statement stmt;
	ResultSet rs,rs1;
	Connection con;
	
	
	public CheckBalanceDAO() throws IOException{
		// Database connection code
		con=ConnectionFactory.getConnection();
	}
	
	public String[] bal_check(CheckBalanceForm cbf)
	 {
		String sArr[] = new String[3];
		 
		 try{
			 con=ConnectionFactory.getConnection();
			 
			 System.out.println("in DAO implemetation connection"+con);
			 // Assume user_id and customer_id both are same	 
			 pstmt=con.prepareStatement("select CID,A_NO,CURR_BAL from ACCOUNTS where CID=?");
			 
			 String cid=cbf.getCustomerId();
			 System.out.println("in Security DAO class..... customer_id is"+cid);

			 pstmt.setString(1, cid);
			 rs=pstmt.executeQuery();
			 
			 if(rs.next())
			 {
				 String customer_id = rs.getString(1);
				 String account_no = rs.getString(2);
				 float curr_bal = rs.getFloat(3);
				 System.out.println("in result set customer_id is........."+customer_id);
				 System.out.println("in result set account_no is........."+account_no);
				 System.out.println("in result set curr_bal is........."+curr_bal);
				 
				 sArr[0] = customer_id;
				 sArr[1] = account_no;
				 sArr[2] = Float.toString(curr_bal);
			 }
		
		 }
		 catch (Exception e) {
			 
			 e.printStackTrace();
			System.out.println("Exception raised"+e);
		}
		 return sArr;
	 }

}
