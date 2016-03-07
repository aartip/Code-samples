package com.banking.FormBean;

public class TransferForm {
	private String t_id;
	private String from_ac_no;
	private String to_ac_no;
	private float t_amount;
	private String memo;
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	public String getFrom_ac_no() {
		return from_ac_no;
	}
	public void setFrom_ac_no(String from_ac_no) {
		this.from_ac_no = from_ac_no;
	}
	public String getTo_ac_no() {
		return to_ac_no;
	}
	public void setTo_ac_no(String to_ac_no) {
		this.to_ac_no = to_ac_no;
	}
	public float getT_amount() {
		return t_amount;
	}
	public void setT_amount(float t_amount) {
		this.t_amount = t_amount;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
