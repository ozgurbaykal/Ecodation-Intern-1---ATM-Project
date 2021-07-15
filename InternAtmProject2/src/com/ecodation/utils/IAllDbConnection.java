package com.ecodation.utils;

import java.sql.Connection;

import java.util.ArrayList;

import com.ecodation.dto.LogDto;

public interface IAllDbConnection<T> {

	// CRUD
	public void create(T t);

	public void delete(String m);

	public void update(int b, String m, Long id);

	public ArrayList<T> list();

	public T get(int id);

	default Connection getInterfaceConnection() {
		DatabaseUtil databaseUtil = new DatabaseUtil();

		return databaseUtil.getConnection();
	}

	LogDto get1(int id);



}
