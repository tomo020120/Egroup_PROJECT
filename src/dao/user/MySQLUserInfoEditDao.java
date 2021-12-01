package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.AddressBean;
import bean.CreditCardBean;
import bean.UserBean;
import bean.joinBean.AllUserInfoBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLUserInfoEditDao implements UserInfoEditDao {
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	@Override
	public boolean updateUserInfo(UserBean userBean) {

		boolean flag = false; // update結果flag

		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "update user_table SET UserName= ?, UserPassword= ?, mailAdress= ? WHERE UserId = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, userBean.getUserName());
			st.setString(2, userBean.getUserPassword());
			st.setString(3, userBean.getMailAddress());
			st.setString(4, userBean.getUserId());

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
	public boolean addCreditCard(CreditCardBean creditCardBean,String userId) {
		boolean flag = false; // insert結果flag

		try {
			cn = ConnectionManager.getInstance().getConnection();

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
	public boolean addAddress(AddressBean addressbean, String userId) {
		// TODO 自動生成されたメソッド・スタブ

			boolean flag = false; // update結果flag

			try {
				cn = ConnectionManager.getInstance().getConnection();

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
	public boolean deleteCreditCard(String creditcardId) {
		boolean flag = false; // delete結果flag

		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "DELETE from credit_card_table WHERE cardId= ?";
			st = cn.prepareStatement(sql);

			st.setString(1, creditcardId);

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
	public boolean deleteAddress(String addressId) {
		boolean flag = false; // delete結果flag

		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "DELETE from address_table WHERE deliveryInfoId = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, addressId);

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
	public AllUserInfoBean getTargetUserInfo(String userId) {
		AllUserInfoBean allUserInfoBean = new AllUserInfoBean();
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select u.userId,userName,userPassword,mailAddress,deliveryInfoId,postalCode,address,tel,cardId,cardOwnerName,cardNo,cardCompany,expirationDate,securityCode from user_table u left outer join address_table a on u.userId = a.userId left outer join credit_card_table c on u.userId = c.userId where u.userId = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, userId);

			rs = st.executeQuery();

			while(rs.next()) {
				allUserInfoBean.setUserId(rs.getString(1));
				allUserInfoBean.setUserName(rs.getString(2));
				allUserInfoBean.setUserPassword(rs.getString(3));
				allUserInfoBean.setEmailAddress(rs.getString(4));
				allUserInfoBean.setDeliveryInfoId(rs.getString(5));
				allUserInfoBean.setPostalCode(rs.getString(6));
				allUserInfoBean.setAddress(rs.getString(7));
				allUserInfoBean.setTel(rs.getString(8));
				allUserInfoBean.setCardId(rs.getString(9));
				allUserInfoBean.setCardOwnerName(rs.getString(10));
				allUserInfoBean.setCardNo(rs.getString(11));
				allUserInfoBean.setCardCompany(rs.getString(12));
				allUserInfoBean.setExpirationDate(rs.getString(13));
				allUserInfoBean.setSecurityCode(rs.getString(14));
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
		return allUserInfoBean;
	}
}
