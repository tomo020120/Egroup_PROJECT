package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.TemporaryUserBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLUserInfoDao implements UserInfoDao{
	private Connection cn=null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	@Override
	public List<String> getUserMailAddress(){
		List<String> mailAddressList = new ArrayList<String>();
		try {
			cn = ConnectionManager.getInstance().getConnection();
			String sql = "select * from user_table";
			st = cn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				mailAddressList.add(rs.getString("mailAddress"));
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
		return mailAddressList;
	}

	@Override
	public boolean addTempUserLoginInfo(TemporaryUserBean tempUserBean) {
		boolean flag = false; // insert結果flag
		try {
			cn = ConnectionManager.getInstance().getConnection();
			String sql = "insert into temporary_user_data(userName,userPassword,mailAddress,UUID) values(?,?,?,?)";
			st = cn.prepareStatement(sql);
			st.setString(1, tempUserBean.getUserName());
			st.setString(2, tempUserBean.getUserPassword());
			st.setString(3, tempUserBean.getMailAddress());
			st.setString(4, tempUserBean.getUUID());
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
}