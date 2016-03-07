package com.banking.FormBean;

public class WithdrawForm {
	private String t_id;
	private String ac_no;
	private float w_amount;
	private String memo;
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
	public float getW_amount() {
		return w_amount;
	}
	public void setW_amount(float w_amount) {
		this.w_amount = w_amount;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
