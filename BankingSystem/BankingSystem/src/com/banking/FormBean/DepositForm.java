package com.banking.FormBean;

public class DepositForm {
	private String t_id;
	private String ac_no;
	private float d_amount;
	private String memo;
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	public String getAc_no() {
		return ac_no;
	}
	public void setAc_no(String ac_no) {
		this.ac_no = ac_no;
	}
	public float getD_amount() {
		return d_amount;
	}
	public void setD_amount(float d_amount) {
		this.d_amount = d_amount;
	}

	
}
