package com.javacart.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			String url = <<CONNECTION STRING>>;
			
			connection = DriverManager.getConnection (url, <<USERNAME>>, <<PASSWORD>>);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
