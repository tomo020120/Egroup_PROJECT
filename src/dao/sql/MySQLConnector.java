package dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MySQLConnector {
	private static Connection cn = null;

	public static Connection getConnection() {
		try {
			if(cn == null) {
				synchronized(MySQLConnector.class) {
					if(cn == null) {
						cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ibanezdb?characterEncoding=UTF-8&serverTimezone=JST","customer","cpass");
						cn.setAutoCommit(false);
					}
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return cn;
	}

	public static void commitTransaction() {
		try {
			cn.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeTransaction() {
		try {
			cn.close();
			cn = null;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollbackTransaction() {
		try {
			cn.rollback();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
