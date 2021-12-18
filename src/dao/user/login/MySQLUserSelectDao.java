package dao.user.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;
import bean.joinBean.UserAndCartBean;
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

	@Override
	public UserAndCartBean getUserAndCartInfo(String userId) {
		UserAndCartBean userAndCartBean = null;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select * from user_table join cart_table using(userId) where userId = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, userId);

			rs = st.executeQuery();

			while(rs.next()) {
				userAndCartBean = new UserAndCartBean();

				userAndCartBean.setUserId(rs.getString(1));
				userAndCartBean.setUserName(rs.getString(2));
				userAndCartBean.setUserPassword(rs.getString(3));
				userAndCartBean.setMailAddress(rs.getString(4));
				userAndCartBean.setCartId(rs.getString(5));
				userAndCartBean.setTotal(rs.getInt(6));
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
		return userAndCartBean;
	}
}