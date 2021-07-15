package com.ecodation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import com.ecodation.controller.AdminController;
import com.ecodation.dto.LogDto;
import com.ecodation.dto.RegisterDto;
import com.ecodation.utils.DatabaseUtil;
import com.ecodation.utils.IAllDbConnection;

public class LogDao implements IAllDbConnection<LogDto> {
	Scanner scan = new Scanner(System.in);

	private static final Logger LOGGER = Logger.getLogger(DatabaseUtil.class.getName());

	public void logUpdate(String l_event, String l_mail) {

		try (Connection connection = getInterfaceConnection()) {
			connection.setAutoCommit(false);

			String sql = " insert into log(log_mail,log_event) values(?,?);";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, l_mail);
			preparedStatement.setString(2, l_event);

			int rowEffected = preparedStatement.executeUpdate();

			if (rowEffected > 0) {
				LOGGER.info(RegisterDto.class + " LOG Verisi Güncellendi! ");
				connection.commit();
			} else {
				LOGGER.warning(RegisterDto.class + " LOG Verisi  Güncellenemedi! ");
				connection.rollback();
				connection.setAutoCommit(true);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

	public void create(AdminController t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int b, String m, Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<LogDto> list() {
		ArrayList<LogDto> logList = new ArrayList<>();

		try (Connection connection = getInterfaceConnection()) {
			String sql = "select * from log";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				LogDto logDto = new LogDto();
				logDto.setLog_date(resultSet.getDate("log_date"));
				logDto.setLog_id(resultSet.getInt("log_id"));
				logDto.setLog_mail(resultSet.getString("log_mail"));
				logDto.setLog_event(resultSet.getString("log_event"));
				logList.add(logDto);
				System.out.println("AAAA");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return logList;
	}

	@Override
	public LogDto get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(LogDto t) {
		// TODO Auto-generated method stub

	}

	@Override
	public LogDto get1(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
