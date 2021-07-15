package com.ecodation.dto;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;

public class RegisterDto implements Serializable {
	private static final long serialVersionUID = 1866595757012628312L;

	private long user_id;
	private int deneme;
	public String user_name;
	private String user_sname;
	private String user_mail;
	private String user_passw;
	private String user_role;
	private Date user_creation;
	private int user_balance;
	private String user_status;

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public int getDeneme() {
		return deneme;
	}

	public void setDeneme(int deneme) {
		this.deneme = deneme;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_sname() {
		return user_sname;
	}

	public void setUser_sname(String user_sname) {
		this.user_sname = user_sname;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public String getUser_passw() {
		return user_passw;
	}

	public void setUser_passw(String user_passw) {
		this.user_passw = user_passw;
	}

	public Date getUser_creation() {
		return user_creation;
	}

	public void setUser_creation(Date user_creation) {
		this.user_creation = user_creation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RegisterDto [user_id=" + user_id + ", deneme=" + deneme + ", user_name=" + user_name + ", user_sname="
				+ user_sname + ", user_mail=" + user_mail + ", user_passw=" + user_passw + ", user_creation="
				+ user_creation + "]";
	}

	public RegisterDto(long user_id, int deneme, String user_name, String user_sname, String user_mail,
			String user_passw, Date user_creation) {
		super();
		this.user_id = user_id;
		this.deneme = deneme;
		this.user_name = user_name;
		this.user_sname = user_sname;
		this.user_mail = user_mail;
		this.user_passw = user_passw;
		this.user_creation = user_creation;
	}

	public RegisterDto() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(deneme, user_creation, user_id, user_mail, user_name, user_passw, user_sname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisterDto other = (RegisterDto) obj;
		return deneme == other.deneme && Objects.equals(user_creation, other.user_creation) && user_id == other.user_id
				&& Objects.equals(user_mail, other.user_mail) && Objects.equals(user_name, other.user_name)
				&& Objects.equals(user_passw, other.user_passw) && Objects.equals(user_sname, other.user_sname);
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	public int getUser_balance() {
		return user_balance;
	}

	public void setUser_balance(int user_balance) {
		this.user_balance = user_balance;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

}
