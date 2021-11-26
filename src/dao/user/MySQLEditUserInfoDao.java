package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.AddressBean;
import bean.CreditCardBean;
import bean.UserBean;
import dao.sql.MySQLConnector;

public class MySQLEditUserInfoDao implements EditUserInfoDao {
	private Connection cn = null;
	private PreparedStatement st = null;

	@Override
	public boolean updateUserInfo(UserBean userBean) {

		boolean flag = false; // update結果flag

		try {
			cn = MySQLConnector.getConnection();

			String sql = "update user_table SET UserName= ?, UserPassword= ?, mailAdress= ? WHERE UserId = ?";//temporary_user_data(userName,userPassword,mailAddress,) values(?,?,?,?)

			st = cn.prepareStatement(sql);

			st.setString(1, userBean.getUserName());
			st.setString(2, userBean.getUserPassword());
			st.setString(3, userBean.getMailAddress());
			st.setString(4, userBean.getUserId());

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

	@Override
	public boolean addCreditCard(CreditCardBean creditCardBean,String userId) { //show tables;可以查看所有的table.
		boolean flag = false; // update結果flag

		try {
			cn = MySQLConnector.getConnection();

			String sql = "update credit_card_table SET cardOwnerName= ?, cardNo= ?, cardCompany= ?, expirationDate= ?, securityCode= ? WHERE UserId = ?";//temporary_user_data(userName,userPassword,mailAddress,) values(?,?,?,?)

			st = cn.prepareStatement(sql);

			st.setString(1, creditCardBean.getCardOwnerName());
			st.setString(2, creditCardBean.getCardNo());
			st.setString(3, creditCardBean.getCardCompany());
			st.setString(4, creditCardBean.getExpirationDate());
			st.setString(5, creditCardBean.getSecurityCode());
			st.setString(6, userId);

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


	@Override
	public boolean addAddress(AddressBean addressbean, String userId) {
		// TODO 自動生成されたメソッド・スタブ

			boolean flag = false; // update結果flag

			try {
				cn = MySQLConnector.getConnection();

				String sql = "update address_table SET deliveryInfoId= ?, postalCode= ?, address= ?, TEL = ?  WHERE UserId = ?";//temporary_user_data(userName,userPassword,mailAddress,) values(?,?,?,?)

				st = cn.prepareStatement(sql);

				st.setString(1, addressbean.getDeliveryInfoId());
				st.setString(2, addressbean.getPostalCode());
				st.setString(3, addressbean.getAddress());
				st.setString(4, addressbean.getTel());
				st.setString(5, userId);

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

	@Override
	public boolean deleteCreditCard(String creditcardId) {
		boolean flag = false; // update結果flag

		try {
			cn = MySQLConnector.getConnection();

			String sql = "DELETE from credit_card_table WHERE cardId= ?";//temporary_user_data(userName,userPassword,mailAddress,) values(?,?,?,?)

			st = cn.prepareStatement(sql);

			st.setString(1, creditcardId);

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


	@Override
	public boolean deleteAddress(String addressId) {
		boolean flag = false; // update結果flag

		try {
			cn = MySQLConnector.getConnection();

			String sql = "DELETE from address_table WHERE deliveryInfoId = ?";//temporary_user_data(userName,userPassword,mailAddress,) values(?,?,?,?)

			st = cn.prepareStatement(sql);

			st.setString(1, addressId);

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
