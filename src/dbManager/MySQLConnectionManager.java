package dbManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ex.ConnectionManageException;

public class MySQLConnectionManager extends ConnectionManager{
	@Override
	public Connection getConnection() {
		System.out.println("接続");

		if(cn == null) {
			System.out.println("新規接続");
			try {
				cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ibanezdb?characterEncoding=UTF-8&serverTimezone=JST","customer","cpass");
				cn.setAutoCommit(false);
				cn.setCatalog("Ibanezdb");
			}
			catch(SQLException e) {
				e.printStackTrace();
				throw new ConnectionManageException(e.getMessage(), e);
			}
		}
		return cn;
	}

	@Override
	public void beginTransaction() {
		System.out.println("トランザクション開始");
		if(cn == null) {
			getConnection();
		}
	}

	@Override
	public void commit() {
		System.out.println("コミットします。");
		try {
			cn.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ConnectionManageException(e.getMessage(), e);
		}
	}

	@Override
	public void rollback() {
		System.out.println("ロールバックします。");
		try {
			cn.rollback();
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ConnectionManageException(e.getMessage(), e);
		}
	}

	@Override
	public void closeTransaction() {
		System.out.println("クローズしました。");
		try {
			if(cn != null) {
				cn.close();
				cn = null;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ConnectionManageException(e.getMessage(), e);
		}
	}

}
