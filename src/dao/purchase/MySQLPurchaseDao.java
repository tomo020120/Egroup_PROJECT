package dao.purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.AddressBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLPurchaseDao implements PurchaseDao{
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	public boolean getdeliveryInfoId(String userId,String fullAddress){
		boolean existFlag = false;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = " SELECT deliveryInfoId FROM address_table WHERE userId=? AND address=?";

			st = cn.prepareStatement(sql);

			st.setString(1, userId);
			st.setString(2, fullAddress);

			rs = st.executeQuery();


			while(rs.next()) {
				AddressBean p = new AddressBean();
			p.setDeliveryInfoId(rs.getString(1));
			}
			existFlag = rs.next();
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

		return existFlag;
	}
}
