package com.ecodation.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.sql.DriverManager;

public class DatabaseUtil {

	private Connection connection;
	private String url = "jdbc:mysql://localhost:3306/atm?useUnicode=yes&characterEncoding=UTF-8";
	private String user = "root";
	private String password = "1234";

	private static final Logger LOGGER = Logger.getLogger(DatabaseUtil.class.getName());

	// database baðlantýsý
	public Connection getConnection() {
		try {
			if (this.connection == null || this.connection.isClosed()) {

				try {
					LOGGER.info("Driver installed successfully! ");
					connection = DriverManager.getConnection(url, user, password);
					LOGGER.info("Successfully connected!");

				} catch (SQLException e) {
					e.printStackTrace();
					LOGGER.warning(" !!!ERROR!!! :  " + DatabaseUtil.class);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}
}
