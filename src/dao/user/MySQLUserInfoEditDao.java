package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.joinBean.UserInfoEditBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLUserInfoEditDao {
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	public List<UserInfoEditBean> getUserInfo(String userId){
		List<UserInfoEditBean> userInfoList = new ArrayList<UserInfoEditBean>();

		try {
			cn = ConnectionManager.getInstance().getConnection();
			String sql = "select userName,userPassword,mailAddress,cardId,cardOwnerName,cardNo,cardCompany,expirationDate,securityCode,deliveryInfoId,address, postalcode,TEL from user_table u join credit_card_table c on u.userId = c.userId join address_table a on u.userId = a.userId where userid = 1";

			st = cn.prepareStatement(sql);

			st.setString(1, userId);

			rs = st.executeQuery();

			while(rs.next()) {
				UserInfoEditBean editBean = new UserInfoEditBean();

				editBean.setUserName(rs.getString(1));
				editBean.setUserPassword(rs.getString(2));
				editBean.setMailAddress(rs.getString(3));
				editBean.setCardId(rs.getString(4));
				editBean.setCardOwnerName(rs.getString(5));
				editBean.setCardNo(rs.getString(6));
				editBean.setCardCompany(rs.getString(7));
				editBean.setExpirationDate(rs.getString(8));
				editBean.setSecurityCode(rs.getString(9));
				editBean.setDeliveryInfoId(rs.getString(10));
				editBean.setAddress(rs.getString(11));
				editBean.setPostCode(rs.getString(12));
				editBean.setTel(rs.getString(13));


				userInfoList.add(editBean);
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

		return userInfoList;
	}

}
