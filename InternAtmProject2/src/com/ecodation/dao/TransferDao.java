package com.ecodation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import com.ecodation.dto.LogDto;
import com.ecodation.dto.RegisterDto;
import com.ecodation.dto.TransferDto;
import com.ecodation.utils.DatabaseUtil;
import com.ecodation.utils.IAllDbConnection;

public class TransferDao implements IAllDbConnection<TransferDto> {
	Scanner scan = new Scanner(System.in);

	private static final Logger LOGGER = Logger.getLogger(DatabaseUtil.class.getName());

	public TransferDto selectTransfer(TransferDto transferDto) {

		try (Connection connection = getInterfaceConnection()) {
			String sql = "SELECT * FROM registers WHERE register_mail=?";
			String sql2 = "SELECT * FROM userinfo WHERE user_mail=?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);

			String t_mail = transferDto.getTransfer_mail();

			preparedStatement.setString(1, t_mail);
			preparedStatement2.setString(1, t_mail);

			ResultSet resultSet = preparedStatement.executeQuery();
			ResultSet resultSet2 = preparedStatement2.executeQuery();

			if (resultSet.next() && resultSet2.next()) {
				System.out.println("------------------------");
				System.out.println("Kullanýcý Bulundu!");
				System.out.println("------------------------");

				transferDto.setTransfer_mail(resultSet2.getString("user_mail"));
				transferDto.setTransfer_name(resultSet.getString("user_name"));
				transferDto.setTransfer_sname(resultSet.getString("user_sname"));
				transferDto.setTransfer_balance(resultSet2.getInt("user_balance"));
				transferDto.setTransfer_status(resultSet2.getString("user_status"));

			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warning(RegisterDto.class + " Sorgulama iþlemi yapýlamadý!!! ");
			return null;

		}
		return transferDto;

	}

	public void transferUpdate(int t_balance, String t_mail) {
		try (Connection connection = getInterfaceConnection()) {
			connection.setAutoCommit(false);

			String sql = "update userinfo set user_balance=? WHERE user_mail=?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, t_balance);
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

		} catch (Exception e) {
		}

	}

	@Override
	public void create(TransferDto t) {
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
	public ArrayList<TransferDto> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransferDto get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogDto get1(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
