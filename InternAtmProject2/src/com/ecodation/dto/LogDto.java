package com.ecodation.dto;

import java.io.Serializable;
import java.util.Date;

public class LogDto implements Serializable {
	private static final long serialVersionUID = -4971657894146363663L;

	private String log_mail;
	private String log_event;
	private Date log_date;
	private int log_id;

	public String getLog_mail() {
		return log_mail;
	}

	public void setLog_mail(String log_mail) {
		this.log_mail = log_mail;
	}

	public String getLog_event() {
		return log_event;
	}

	public void setLog_event(String log_event) {
		this.log_event = log_event;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getLog_id() {
		return log_id;
	}

	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}

	public Date getLog_date() {
		return log_date;
	}

	public void setLog_date(Date log_date) {
		this.log_date = log_date;
	}

	@Override
	public String toString() {
		return "-- Ýþlem Yapan Mail= " + log_mail + "  --  Yapýlan Ýþlem: " + log_event + "  --  Ýþlem Tarihi: "
				+ log_date + "  --  Kullanýcý Id: " + log_id + "]";
	}

	public LogDto() {
		super();

	}

}
