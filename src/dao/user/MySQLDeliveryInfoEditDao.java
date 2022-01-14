package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AddressBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLDeliveryInfoEditDao implements DeliveryInfoEditDao {
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	@Override
	public List<AddressBean> getDeliveryInfo(String userId) {
		List<AddressBean> addressList = new ArrayList<AddressBean>();
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select deliveryInfoId,deliveryName,postalCode,replace(address,'/',''),tel,userId from address_table where userId = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, userId);

			rs = st.executeQuery();

			while (rs.next()) {
				AddressBean addressBean = new AddressBean();

				addressBean.setDeliveryInfoId(rs.getString(1));
				addressBean.setDeliveryName(rs.getString(2));
				addressBean.setPostalCode(rs.getString(3));
				addressBean.setAddress(rs.getString(4));
				addressBean.setTel(rs.getString(5));
				addressBean.setUserId(rs.getString(6));

				addressList.add(addressBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionManager.getInstance().rollback();
			throw new DaoOperationException(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			ConnectionManager.getInstance().rollback();
			throw new DaoOperationException(e.getMessage(), e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DaoOperationException(e.getMessage(), e);
				}
			}
		}
		return addressList;
	}

	@Override
	public AddressBean getTargetDeliveryInfo(String deliveryInfoId) {
		AddressBean addressBean = new AddressBean();
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select * from address_table where deliveryInfoId = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, deliveryInfoId);

			rs = st.executeQuery();

			while (rs.next()) {
				addressBean.setDeliveryInfoId(rs.getString(1));
				addressBean.setDeliveryName(rs.getString(2));
				addressBean.setPostalCode(rs.getString(3));
				addressBean.setAddress(rs.getString(4));
				addressBean.setTel(rs.getString(5));
				addressBean.setUserId(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionManager.getInstance().rollback();
			throw new DaoOperationException(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			ConnectionManager.getInstance().rollback();
			throw new DaoOperationException(e.getMessage(), e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DaoOperationException(e.getMessage(), e);
				}
			}
		}
		return addressBean;
	}

	@Override
	public int getSameAddressQuantity(String address, String userId) {
		int result = 0;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select count(address) from address_table where address = ? and userId = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, address);
			st.setString(2, userId);

			rs = st.executeQuery();

			while(rs.next()) {
				result = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			ConnectionManager.getInstance().rollback();
			throw new DaoOperationException(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			ConnectionManager.getInstance().rollback();
			throw new DaoOperationException(e.getMessage(), e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DaoOperationException(e.getMessage(), e);
				}
			}
		}
		return result;
	}

	@Override
	public boolean addDeliveryInfo(AddressBean addressBean) {
		boolean flag = false;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "insert into address_table(deliveryName,postalCode,address,tel,userId) values(?,?,?,?,?)";

			st = cn.prepareStatement(sql);

			st.setString(1, addressBean.getDeliveryName());
			st.setString(2, addressBean.getPostalCode());
			st.setString(3, addressBean.getAddress());
			st.setString(4, addressBean.getTel());
			st.setString(5, addressBean.getUserId());

			int result = st.executeUpdate();

			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionManager.getInstance().rollback();
			throw new DaoOperationException(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			ConnectionManager.getInstance().rollback();
			throw new DaoOperationException(e.getMessage(), e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DaoOperationException(e.getMessage(), e);
				}
			}
		}
		return flag;
	}

	@Override
	public boolean updateDeliveryInfo(AddressBean addressBean) {
		boolean flag = false;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "update address_table set deliveryName = ?,postalCode = ?,address = ?,tel = ? where deliveryInfoId = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, addressBean.getDeliveryName());
			st.setString(2, addressBean.getPostalCode());
			st.setString(3, addressBean.getAddress());
			st.setString(4, addressBean.getTel());
			st.setString(5, addressBean.getDeliveryInfoId());

			int result = st.executeUpdate();

			if (result > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			ConnectionManager.getInstance().rollback();
			throw new DaoOperationException(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			ConnectionManager.getInstance().rollback();
			throw new DaoOperationException(e.getMessage(), e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DaoOperationException(e.getMessage(), e);
				}
			}
		}
		return flag;
	}

	@Override
	public boolean deleteDeliveryInfo(String userId, String deliveryInfoId) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}


}
