package com.banking.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.banking.DB.ConnectionFactory;
import com.banking.FormBean.TransferForm;

public class TransferDAO {
	PreparedStatement pstmt, pstmt1, pstmt2, pstmt3, pstmt4,pstmt5;
	ResultSet rs,rs1;
	Connection con;
	boolean flag;
	
	public TransferDAO() throws IOException{
		// Database connection code
		con=ConnectionFactory.getConnection();
	}
	
	public boolean transfer_amount(TransferForm tf)
	 {
		 try{
			 con=ConnectionFactory.getConnection();
			 
			 System.out.println("in DAO implemetation connection"+con); 
			 pstmt1=con.prepareStatement("select CURR_BAL, A_NO from ACCOUNTS where A_NO=?");
			 
			 String from_ac_no=tf.getFrom_ac_no();
			 System.out.println("in Security DAO class.....a_no is"+from_ac_no);
			 
			 pstmt1.setString(1, from_ac_no);
			 rs=pstmt1.executeQuery();
			 if(rs.next())
			 {
				 float sender_cur_bal = rs.getFloat(1);
				 String from_a_no = rs.getString(2);
				 float t_amount = tf.getT_amount();
				 float sender_total_bal = sender_cur_bal - t_amount;
				 
				 
				 String tid=tf.getT_id();
				 System.out.println("in Security DAO class..... t_id is"+tid);
				 
				 String memo = tf.getMemo();
				 
				 pstmt=con.prepareStatement("INSERT INTO TRANSACTIONS(TID,A_NO,T_TYPE,T_DATE,T_AMOUNT,T_TIME,MEMO) values (?,?,?,CURDATE(),?,CURTIME(),?)");
				 
				 pstmt.setString(1, tid);
				 pstmt.setString(2, from_a_no);
				 pstmt.setString(3, "Transfer");
				 pstmt.setFloat(4, t_amount);
				 pstmt.setString(5, memo);

				 int i=pstmt.executeUpdate();
				 if(i>0)
				 {
					 flag=true;
				 }
				 
				 pstmt2=con.prepareStatement("UPDATE ACCOUNTS SET CURR_BAL=? where A_NO=?");
				 pstmt2.setFloat(1,sender_total_bal);
				 pstmt2.setString(2, from_a_no);
				 int j=pstmt2.executeUpdate();
				 if(j>0){
					 System.out.println("Debited at Sender's end successfully");
					 
					 pstmt3=con.prepareStatement("select CURR_BAL, A_NO from ACCOUNTS where A_NO=?");
					 String to_ac_no=tf.getTo_ac_no();
					 System.out.println("in Security DAO class.....a_no is"+to_ac_no);
					 
					 pstmt3.setString(1, to_ac_no);
					 rs1=pstmt3.executeQuery();
					 
					 if(rs1.next()){
						 float receiver_cur_bal = rs1.getFloat(1);
						 String to_a_no = rs1.getString(2);
						 System.out.println(to_a_no);
						 float receiver_total_bal = receiver_cur_bal + t_amount;
						 
						 pstmt4=con.prepareStatement("INSERT INTO TRANSACTIONS(TID,A_NO,T_TYPE,T_DATE,T_AMOUNT,T_TIME,MEMO) values (?,?,?,CURDATE(),?,CURTIME(),?)");
						 
						 pstmt4.setString(1, tid);
						 pstmt4.setString(2, to_a_no);
						 pstmt4.setString(3, "Transfer");
						 pstmt4.setFloat(4, t_amount);
						 pstmt4.setString(5, memo);

						 int k=pstmt4.executeUpdate();
						 if(k>0)
						 {
							 flag=true;
						 }
						 
						 pstmt5 = con.prepareStatement("UPDATE ACCOUNTS SET CURR_BAL=? where A_NO=?");
						 pstmt5.setFloat(1,receiver_total_bal);
						 pstmt5.setString(2, to_a_no);
						 int l=pstmt5.executeUpdate();
						 if(l>0){
							 System.out.println("Credited at Receiver's end successfully");
						 }
					 }
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
