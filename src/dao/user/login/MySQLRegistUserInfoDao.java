package dao.user.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.UserBean;
import dao.sql.MySQLConnector;

public class MySQLRegistUserInfoDao implements RegistUserInfoDao {
	private Connection cn = null;
	private PreparedStatement st = null;

	@Override
	public boolean addUserInfo(UserBean userBean) {
		boolean flag = false;

		try {
			cn = MySQLConnector.getConnection();

			String sql = "insert into user_table(userName,userPassword,mailAddress) values(?,?,?)";

			st = cn.prepareStatement(sql);

			st.setString(1, userBean.getUserName());
			st.setString(2, userBean.getUserPassword());
			st.setString(3, userBean.getMailAddress());

			int result = st.executeUpdate();

			if(result > 0) {
				flag = true;
				MySQLConnector.commitTransaction();
			}
		}
		catch(SQLException e) {
			MySQLConnector.rollbackTransaction();
		}
		catch(Exception e) {
			MySQLConnector.rollbackTransaction();
		}
		finally {
			MySQLConnector.closeTransaction();
		}

		return flag;
	}

}
