package com.ecodation.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import com.ecodation.dto.LogDto;
import com.ecodation.dto.RegisterDto;
import com.ecodation.utils.DatabaseUtil;
import com.ecodation.utils.IAllDbConnection;

public class RegisterDao implements IAllDbConnection<RegisterDto> {

	Scanner scan = new Scanner(System.in);
	private int LoginControl;

	private static final Logger LOGGER = Logger.getLogger(DatabaseUtil.class.getName());

	@Override
	public void create(RegisterDto registerDto) {

		try (Connection connection = getInterfaceConnection()) {
			connection.setAutoCommit(false);
			String sql = "insert into registers(user_name,user_sname,register_mail,user_passw) values(?,?,?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, registerDto.getUser_name());
			preparedStatement.setString(2, registerDto.getUser_sname());
			preparedStatement.setString(3, registerDto.getUser_mail());
			preparedStatement.setString(4, registerDto.getUser_passw());
			int rowEffected = preparedStatement.executeUpdate();
			if (rowEffected > 0) {
				LOGGER.info(RegisterDto.class + " Veri Kaydý Yapýldý!");
				connection.commit();
			} else {
				LOGGER.warning(RegisterDto.class + " Kayýt Yapýlamadý! ");
				connection.rollback();
				connection.setAutoCommit(true);

			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warning(RegisterDto.class + " Kayýt Yapýlamadý! ");

		}

	}

	@Override
	public void update(int b, String m, Long id) {
		try (Connection connection = getInterfaceConnection()) {
			connection.setAutoCommit(false);

			String sql = "update userinfo set user_balance=? WHERE user_mail=?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, b);
			preparedStatement.setString(2, m);

			int rowEffected = preparedStatement.executeUpdate();

			if (rowEffected > 0) {
				LOGGER.info(RegisterDto.class + " Veri Güncellendi! ");
				connection.commit();
			} else {
				LOGGER.warning(RegisterDto.class + " Veri Güncellenemedi! ");
				connection.rollback();
				connection.setAutoCommit(true);

			}

		} catch (Exception e) {
		}
	}

	public RegisterDto login(RegisterDto registerDto) {

		try (Connection connection = getInterfaceConnection()) {

			String sql = "SELECT * FROM registers WHERE register_mail=? AND user_passw=?";
			String sql2 = "SELECT * FROM userinfo WHERE user_mail=?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);

			String mail = registerDto.getUser_mail();
			String sifre = registerDto.getUser_passw();

			preparedStatement.setString(1, mail);
			preparedStatement.setString(2, sifre);
			preparedStatement2.setString(1, mail);

			ResultSet resultSet = preparedStatement.executeQuery();
			ResultSet resultSet2 = preparedStatement2.executeQuery();

			if (resultSet.next() && resultSet2.next()) {
				System.out.println("------------------------");
				System.out.println("Giriþ baþarýlý");
				registerDto.setUser_role(resultSet2.getString("user_role"));
				registerDto.setUser_balance(resultSet2.getInt("user_balance"));
				registerDto.setUser_status(resultSet2.getString("user_status"));
				registerDto.setUser_id(resultSet2.getLong("userinfo_id"));

			} else {
				setLoginControl(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warning(RegisterDto.class + " Sorgulama iþlemi yapýlamadý!!! ");
			return null;
		}
		return registerDto;
	}

	@Override
	public ArrayList<RegisterDto> list() {

		return null;
	}

	@Override
	public RegisterDto get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLoginControl() {
		return LoginControl;
	}

	public void setLoginControl(int loginControl) {
		LoginControl = loginControl;
	}

	@Override
	public void delete(String m) {
		// TODO Auto-generated method stub

	}

	@Override
	public LogDto get1(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
