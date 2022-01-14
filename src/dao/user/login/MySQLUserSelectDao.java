package dao.user.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLUserSelectDao implements UserSelectDao{
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	public UserBean getUserInfo(String _mail,String _pass) {
		UserBean userBean = null;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select * from user_table where mailAddress = ? AND userPassword = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, _mail);
			st.setString(2, _pass);

			rs = st.executeQuery();


			while(rs.next()) {
				userBean = new UserBean();

				userBean.setUserId(rs.getString(1));
				userBean.setUserName(rs.getString(2));
				userBean.setUserPassword(rs.getString(3));
				userBean.setMailAddress(rs.getString(4));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			ConnectionManager.getInstance().rollback();
			throw new DaoOperationException(e.getMessage(), e);
		}catch(Exception e) {
			e.printStackTrace();
			ConnectionManager.getInstance().rollback();
			throw new DaoOperationException(e.getMessage(), e);
		}finally {
			if(st != null) {
				try {
					st.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
					throw new DaoOperationException(e.getMessage(), e);
				}
			}
		}
		return userBean;
	}
}