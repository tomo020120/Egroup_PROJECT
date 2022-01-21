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

				String cardNo = rs.getString(3);
				String lastFourDisits = cardNo.substring(cardNo.length() - 4); // カード番号下四桁抽出

				creditCardBean.setCardId(rs.getString(1));
				creditCardBean.setCardOwnerName(rs.getString(2));
				creditCardBean.setCardNo(lastFourDisits);
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
	public int getSameCreditCardQuantity(String cardNo, String userId) {
		int result = 0;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select count(cardNo) from credit_card_table where cardNo = ? and userId = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, cardNo);
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
	public boolean addCreditCardInfo(CreditCardBean creditCardBean) {
		boolean flag = false;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "insert into credit_card_table(cardOwnerName,cardNo,cardCompany,expirationDate,userId) values(?,?,?,?,?)";

			st = cn.prepareStatement(sql);

			st.setString(1, creditCardBean.getCardOwnerName());
			st.setString(2, creditCardBean.getCardNo());
			st.setString(3, creditCardBean.getCardCompany());
			st.setString(4, creditCardBean.getExpirationDate());
			st.setString(5, creditCardBean.getUserId());

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
	public boolean updateCreditCardInfo(CreditCardBean creditCardBean) {
		boolean flag = false;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "update credit_card_table set cardOwnerName = ?,expirationDate = ? where cardId = ?";

			st = cn.prepareStatement(sql);

			System.out.println(creditCardBean.getCardOwnerName());
			System.out.println(creditCardBean.getExpirationDate());
			System.out.println(creditCardBean.getCardId());

			st.setString(1, creditCardBean.getCardOwnerName());
			st.setString(2, creditCardBean.getExpirationDate());
			st.setString(3, creditCardBean.getCardId());

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
	public boolean deleteCreditCardInfo(String creditCardId) {
		boolean flag = false;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "delete from credit_card_table where cardId = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, creditCardId);

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

}
