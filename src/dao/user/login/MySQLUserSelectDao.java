package dao.user.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.sql.MySQLConnector;

public class MySQLUserSelectDao implements UserSelectDao{
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	public boolean selectMailAndPass(String _mail,String _pass) {
		boolean flag=false;
		try {
			cn = MySQLConnector.getConnection();

			String sql = "select * from user_table where mailAddress='"+_mail+"' AND userPassword='"+_pass+"'";

			st = cn.prepareStatement(sql);

			rs = st.executeQuery();

			if(rs.next()) {
				flag=true;
			}else {
				flag=false;
			}
		}
		catch(SQLException e) {
			MySQLConnector.rollbackTransaction();
			e.printStackTrace();
		}
		catch(Exception e) {
			MySQLConnector.rollbackTransaction();
			e.printStackTrace();
		}
		finally {
			MySQLConnector.closeTransaction();
		}
		return flag;
	}
}