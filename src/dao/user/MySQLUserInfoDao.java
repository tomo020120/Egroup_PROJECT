package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.TemporaryUserBean;
import dao.sql.MySQLConnector;

public class MySQLUserInfoDao implements UserInfoDao{

	private Connection cn=null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	@Override
	public List<String> getUserMailAddress(){
		List<String> mailAddressList = new ArrayList<String>();

		try {
			cn = MySQLConnector.getConnection();

			String sql = "select * from user_table";

			st = cn.prepareStatement(sql);

			rs = st.executeQuery();

			while(rs.next()) {
				mailAddressList.add(rs.getString("mailAddress"));
			}

			MySQLConnector.commitTransaction();
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

		return mailAddressList;
	}

	@Override
	public boolean addTempUserLoginInfo(TemporaryUserBean tempUserBean) {
		boolean flag = false; // insert結果flag

		try {
			cn = MySQLConnector.getConnection();

			String sql = "insert into temporary_user_data(userName,userPassword,mailAddress,UUID) values(?,?,?,?)";

			st = cn.prepareStatement(sql);

			st.setString(1, tempUserBean.getUserName());
			st.setString(2, tempUserBean.getUserPassword());
			st.setString(3, tempUserBean.getMailAddress());
			st.setString(4, tempUserBean.getUUID());

			int result = st.executeUpdate();

			if(result > 0) {
				flag = true;
				MySQLConnector.commitTransaction();
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
