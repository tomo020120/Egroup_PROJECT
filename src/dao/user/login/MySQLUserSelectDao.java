package dao.user.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;
import dao.sql.MySQLConnector;

public class MySQLUserSelectDao implements UserSelectDao{
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	public UserBean getUserInfo(String _mail,String _pass) {
		UserBean userBean = new UserBean();
		try {
			cn = MySQLConnector.getConnection();

			String sql = "select * from user_table where mailAddress = ? AND userPassword = ?";

			st = cn.prepareStatement(sql);

			rs = st.executeQuery();

			while(rs.next()) {
				userBean.setUserId(rs.getString(1));
				userBean.setUserName(rs.getString(2));
				userBean.setUserPassword(rs.getString(3));
				userBean.setMailAddress(rs.getString(4));
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
		return userBean;
	}
}