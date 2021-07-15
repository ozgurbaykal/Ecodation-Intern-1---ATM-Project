package com.ecodation.dto;

public class TransferDto {

	private String transfer_mail;
	private String transfer_name;
	private String transfer_sname;
	private int transfer_balance;
	private String transfer_status;

	public String getTransfer_mail() {
		return transfer_mail;
	}

	public void setTransfer_mail(String transfer_mail) {
		this.transfer_mail = transfer_mail;
	}

	public String getTransfer_name() {
		return transfer_name;
	}

	public void setTransfer_name(String transfer_name) {
		this.transfer_name = transfer_name;
	}

	public String getTransfer_sname() {
		return transfer_sname;
	}

	public void setTransfer_sname(String transfer_sname) {
		this.transfer_sname = transfer_sname;
	}

	public int getTransfer_balance() {
		return transfer_balance;
	}

	public void setTransfer_balance(int transfer_balance) {
		this.transfer_balance = transfer_balance;
	}

	public TransferDto(String transfer_mail, String transfer_name, String transfer_sname, int transfer_balance) {
		super();
		this.transfer_mail = transfer_mail;
		this.transfer_name = transfer_name;
		this.transfer_sname = transfer_sname;
		this.transfer_balance = transfer_balance;
	}

	public TransferDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTransfer_status() {
		return transfer_status;
	}

	public void setTransfer_status(String transfer_status) {
		this.transfer_status = transfer_status;
	}

}
