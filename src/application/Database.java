package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/employment_db";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "123456";

	private Database() {

	}

	public static Connection getDBConnection() throws SQLException {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException exception) {
			System.out.println("MySQL driver not found: " + exception.getMessage());
		}
		return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
	}
}