package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CreditCardBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLCreditCardInfoEditDao implements CreditCardInfoEditDao {
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	@Override
	public List<CreditCardBean> getCreditCardInfo(String userId) {
		List<CreditCardBean> creditCardList = new ArrayList<CreditCardBean>();
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select * from credit_card_table where userId = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, userId);

			rs = st.executeQuery();

			while (rs.next()) {
				CreditCardBean creditCardBean = new CreditCardBean();

				creditCardBean.setCardId(rs.getString(1));
				creditCardBean.setCardOwnerName(rs.getString(2));
				creditCardBean.setCardNo(rs.getString(3));
				creditCardBean.setCardCompany(rs.getString(4));
				creditCardBean.setExpirationDate(rs.getString(5));

				creditCardList.add(creditCardBean);
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
		return creditCardList;
	}

	@Override
	public CreditCardBean getTargetDeliveryInfo(String creditCardId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public boolean addCreditCardInfo(CreditCardBean creditCardBean) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean updateCreditCardInfo(CreditCardBean creditCard) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean deleteCreditCardInfo(String creditCardId) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
