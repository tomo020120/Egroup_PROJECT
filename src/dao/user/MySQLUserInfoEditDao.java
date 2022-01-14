package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AddressBean;
import bean.CreditCardBean;
import bean.UserBean;
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
	public List<CreditCardBean> getCreditCardInfo(String userId) {
		List<CreditCardBean> cardList = new ArrayList<CreditCardBean>();
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select * from credit_card_table where userId = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, userId);

			rs = st.executeQuery();

			while(rs.next()) {
				CreditCardBean creditCardBean = new CreditCardBean();

				creditCardBean.setCardId(rs.getString(1));
				creditCardBean.setCardOwnerName(rs.getString(2));
				creditCardBean.setCardNo(rs.getString(3));
				creditCardBean.setCardCompany(rs.getString(4));
				creditCardBean.setExpirationDate(rs.getString(5));
				creditCardBean.setSecurityCode(rs.getString(6));

				cardList.add(creditCardBean);
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
		return cardList;
	}

	@Override
	public List<AddressBean> getAddressInfo(String userId) {
		List<AddressBean> addressList = new ArrayList<AddressBean>();
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select * from address_table where userId = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, userId);

			rs = st.executeQuery();

			while(rs.next()) {
				AddressBean addressBean = new AddressBean();

				addressBean.setDeliveryInfoId(rs.getString(1));
				addressBean.setPostalCode(rs.getString(2));
				addressBean.setAddress(rs.getString(3));
				addressBean.setTel(rs.getString(4));

				addressList.add(addressBean);
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
		return addressList;
	}
}
