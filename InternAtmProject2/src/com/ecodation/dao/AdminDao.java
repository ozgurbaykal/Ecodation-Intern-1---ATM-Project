package com.ecodation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import com.ecodation.controller.AdminController;
import com.ecodation.dto.LogDto;
import com.ecodation.dto.RegisterDto;
import com.ecodation.utils.DatabaseUtil;
import com.ecodation.utils.IAllDbConnection;

public class AdminDao implements IAllDbConnection<AdminController> {

	Scanner scan = new Scanner(System.in);

	private static final Logger LOGGER = Logger.getLogger(DatabaseUtil.class.getName());

	public void statusUpdate(String c_status, String t_mail) {

		try (Connection connection = getInterfaceConnection()) {
			connection.setAutoCommit(false);

			String sql = "update userinfo set user_status=? WHERE user_mail=?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, c_status);
			preparedStatement.setString(2, t_mail);

			int rowEffected = preparedStatement.executeUpdate();

			if (rowEffected > 0) {
				LOGGER.info(RegisterDto.class + " Veri Güncellendi! ");
				connection.commit();
			} else {
				LOGGER.warning(RegisterDto.class + " Veri Güncellenemedi! ");
				connection.rollback();
				connection.setAutoCommit(true);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void create(AdminController t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String m) {
		try (Connection connection = getInterfaceConnection()) {
			String sql = "delete from registers where register_mail=?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, m);

			int rowEffected = preparedStatement.executeUpdate();

			if (rowEffected > 0) {
				LOGGER.info(RegisterDto.class + " Kullanýcý Baþarýyla Silindi! ");

			} else {
				LOGGER.warning(RegisterDto.class + " Kullanýcý Silinemedi! ");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(int b, String m, Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<AdminController> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdminController get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogDto get1(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
