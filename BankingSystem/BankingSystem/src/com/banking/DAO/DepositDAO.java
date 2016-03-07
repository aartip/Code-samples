package com.banking.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.banking.DB.ConnectionFactory;
import com.banking.FormBean.DepositForm;

public class DepositDAO {
	PreparedStatement pstmt,pstmt1, pstmt2;
	Statement stmt;
	ResultSet rs,rs1;
	Connection con;
	boolean flag;
	
	public DepositDAO() throws IOException{
		// Database connection code
		con=ConnectionFactory.getConnection();
	}
	
	public boolean deposit_amount(DepositForm df)
	 {
		 try{
			 con=ConnectionFactory.getConnection();
			 
			 System.out.println("in DAO implemetation connection"+con); 
			 pstmt1=con.prepareStatement("select CURR_BAL, A_NO from ACCOUNTS where A_NO=?");
			 
			 String ac_no=df.getAc_no();
			 System.out.println("in Security DAO class.....a_no is"+ac_no);
			 
			 pstmt1.setString(1, ac_no);
			 rs=pstmt1.executeQuery();
			 if(rs.next())
			 {
				 float cur_bal = rs.getFloat(1);
				 String a_no = rs.getString(2);
				 float d_amount = df.getD_amount();
				 float total_bal = cur_bal + d_amount;
				 
				 
				 String tid=df.getT_id();
				 System.out.println("in Security DAO class..... t_id is"+tid);
				 
				 
				 String memo = df.getMemo();
				 
				 pstmt=con.prepareStatement("INSERT INTO TRANSACTIONS(TID,A_NO,T_TYPE,T_DATE,T_AMOUNT,T_TIME,MEMO) values (?,?,?,CURDATE(),?,CURTIME(),?)");
				 
				 pstmt.setString(1, tid);
				 pstmt.setString(2, a_no);
				 pstmt.setString(3, "Deposit");
				 pstmt.setFloat(4, d_amount);
				 pstmt.setString(5, memo);

				 int i=pstmt.executeUpdate();
				 if(i>0)
				 {
					 flag=true;
				 }
				 
				 pstmt2=con.prepareStatement("UPDATE ACCOUNTS SET CURR_BAL=? where A_NO=?");
				 pstmt2.setFloat(1,total_bal);
				 pstmt2.setString(2, a_no);
				 int j=pstmt2.executeUpdate();
				 if(j>0){
					 System.out.println("Updated the current balance successfully");
				 }
				 
			 }
			
			 
		
		 }
		 catch (SQLException e) {
			 e.printStackTrace();
			System.out.println("Exception raised"+e);
		}
		return flag;
	 }

}
