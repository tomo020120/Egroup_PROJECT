package dao.user.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.TemporaryUserBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLRegistUserInfoDao implements RegistUserInfoDao {
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	@Override
	public boolean addUserInfo(TemporaryUserBean tempUserBean) {
		boolean flag = false;

		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "insert into user_table(userName,userPassword,mailAddress) values(?,?,?)";

			st = cn.prepareStatement(sql);

			st.setString(1, tempUserBean.getUserName());
			st.setString(2, tempUserBean.getUserPassword());
			st.setString(3, tempUserBean.getMailAddress());

			int result = st.executeUpdate();

			if(result > 0) {
				flag = true;
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

		return flag;
	}

	@Override
	public TemporaryUserBean getTempUserInfo(String UUID) {
		TemporaryUserBean tempUserBean = null;

		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select userName,userPassword,mailAddress from temporary_user_data where UUID = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, UUID);

			rs = st.executeQuery();


			while(rs.next()) {
				tempUserBean = new TemporaryUserBean();

				tempUserBean.setUserName(rs.getString(1));
				tempUserBean.setUserPassword(rs.getString(2));
				tempUserBean.setMailAddress(rs.getString(3));
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
		return tempUserBean;
	}

}
