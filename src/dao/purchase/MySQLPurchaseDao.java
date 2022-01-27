package dao.purchase;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.AddressBean;
import bean.CreditCardBean;
import bean.joinBean.AllCartBean;
import bean.joinBean.AllOrderConfirmationBean;
import bean.joinBean.AllPurchaseHistoryBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLPurchaseDao implements PurchaseDao{
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	public String getdeliveryInfoId(String userId,String fullAddress){
		String deliveryInfoId=null;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = " SELECT deliveryInfoId FROM address_table WHERE userId=? AND address=?";

			st = cn.prepareStatement(sql);

			st.setString(1, userId);
			st.setString(2, fullAddress);

			rs = st.executeQuery();


			while(rs.next()) {
				deliveryInfoId=rs.getString(1);
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

		return deliveryInfoId;
	}

	public List<AllOrderConfirmationBean> getAllOrderConfirmation(String userId,String deliveryInfoId,String cardId,String cartId) {
		List<AllOrderConfirmationBean> confirmation = new ArrayList<AllOrderConfirmationBean>();
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT user.userId,mailAddress,deliveryInfoId,deliveryName,postalCode,address,TEL,cardId,cardOwnerName,cardNo,cardCompany,expirationDate,name,orderCount,subTotal,itemId,cartId,pictPath,total FROM user_table AS user LEFT OUTER JOIN address_table AS address ON user.userId=address.userId LEFT OUTER JOIN credit_card_table AS credit ON user.userId=credit.userId LEFT OUTER JOIN all_inside_cart_view AS cart ON user.userId=cart.userId WHERE user.userId=? AND deliveryInfoId =? AND cardId=? AND cartId=?;";

			st = cn.prepareStatement(sql);

			st.setString(1, userId);
			st.setString(2, deliveryInfoId);
			st.setString(3, cardId);
			st.setString(4, cartId);

			rs = st.executeQuery();

			while(rs.next()) {

				AllOrderConfirmationBean p = new AllOrderConfirmationBean();
				p.setUserId(rs.getString(1));
				p.setMailAddress(rs.getString(2));//メールアドレス
				p.setDeliveryInfoId(rs.getString(3));//配送ID
				p.setDeliveryName(rs.getString(4));//配送先氏名
				p.setPostalCode(rs.getString(5));//郵便番号
				p.setAddress(rs.getString(6));//住所
				p.setTel(rs.getString(7));//電話番号
				p.setCardId(rs.getString(8));//カードID
				p.setCardOwnerName(rs.getString(9));//カード所有者名
				p.setCardNo(rs.getString(10));//カード番号
				p.setCardCompany(rs.getString(11));//カード会社
				p.setExpirationDate(rs.getString(12));//有効期限


				p.setName(rs.getString(13));//商品名
				p.setOrderCount(rs.getString(14));//注文個数
				p.setSubTotal(rs.getString(15));//小計
				p.setItemId(rs.getString(16));//アイテムID
				p.setCartId(rs.getString(17));//カートID
				p.setPictPath(rs.getString(18));//商品の写真
				p.setTotal(rs.getString(19));//合計

				confirmation.add(p);
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
		return confirmation;
	}

	public boolean PurchaseCompleted(String ItemId,String OrderCount,String subTotal,String cartId,int total,String userId) {
		boolean flag = false; // プロシージャ実行結果flag

		// 各データを配列化
		String[] item = ItemId.split(",");
		String[] order = OrderCount.split(",");
		String[] sub = subTotal.split(",");

		for(int i=0; i<item.length; i++) {
			try {
				cn = ConnectionManager.getInstance().getConnection();

				String sql = "call purchase(?,?,?,?,?,now(),?)"; // 購入確定の一連の処理を行うプロシージャ

				CallableStatement cst = cn.prepareCall(sql);


				cst.setString(1, item[i]);
				cst.setString(2, order[i]);
				cst.setString(3, sub[i]);
				cst.setString(4, cartId);
				cst.setInt(5, total);
				cst.setString(6, userId);

				int result = cst.executeUpdate();

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
		}
		return flag;
	}

	public AllCartBean getInsideCart(String cartId) {
		AllCartBean p = new AllCartBean();
		try {
			cn = ConnectionManager.getInstance().getConnection();


			String sql = " SELECT group_Concat(itemId),group_Concat(orderCount),group_Concat(subTotal) FROM inside_cart_table WHERE cartId=?";

			st=cn.prepareStatement(sql);

			st.setString(1, cartId);

			rs=st.executeQuery();



			while(rs.next()) {

				p.setItemId(rs.getString(1));
				p.setOrderCount(rs.getString(2));
				p.setSubTotal(rs.getString(3));

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

	return p;

	}

	public List<AllPurchaseHistoryBean> getAllPurchaseHistory(String userId) {
		List<AllPurchaseHistoryBean> purchaseHistory = new ArrayList<AllPurchaseHistoryBean>();
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT history.purchaseId,userId,details.itemId,details.orderCount,subTotal,name,pictPath,price,purchaseDate FROM purchase_history_table AS history LEFT OUTER JOIN purchase_details_table AS details ON history.purchaseId = details.purchaseId LEFT OUTER JOIN product_table AS product ON details.itemId=product.itemId LEFT OUTER JOIN item_pict_table AS pict ON details.itemId=pict.itemId WHERE userId=? ORDER BY purchaseDate DESC";

			st = cn.prepareStatement(sql);

			st.setString(1, userId);

			rs = st.executeQuery();

			while(rs.next()) {

				AllPurchaseHistoryBean p = new AllPurchaseHistoryBean();

				p.setName(rs.getString(6));//商品名
				p.setOrderCount(rs.getString(4));//注文個数
				p.setSubTotal(rs.getString(5));//小計
				p.setItemId(rs.getString(3));//アイテムID
				p.setPictPath(rs.getString(7));//商品の写真
				p.setPrice(rs.getString(8));//小計
				p.setDate(rs.getString(9));//時間

				purchaseHistory.add(p);
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
		return purchaseHistory;
	}

	@Override
	public AddressBean getTargetAddressInfo(String deliveryInfoId) {
		AddressBean addressBean = new AddressBean();
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select * from address_table where deliveryInfoId = ?";
			st = cn.prepareStatement(sql);

			st.setString(1, deliveryInfoId);

			rs = st.executeQuery();

			while(rs.next()) {
				addressBean.setDeliveryInfoId(rs.getString(1));
				addressBean.setDeliveryName(rs.getString(2));
				addressBean.setPostalCode(rs.getString(3));
				addressBean.setAddress(rs.getString(4).replace("/", "")); // 区切り文字を外した状態で格納
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
		return addressBean;
	}

	@Override
	public CreditCardBean getTargetCreditCardInfo(String cardId) {
		CreditCardBean creditCardBean = new CreditCardBean();
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select * from credit_card_table where cardId = ?";
			st = cn.prepareStatement(sql);

			st.setString(1, cardId);

			rs = st.executeQuery();

			while(rs.next()) {
				String cardNo = rs.getString(3);
				String lastFourDisits = cardNo.substring(cardNo.length() - 4); // カード番号下四桁抽出

				creditCardBean.setCardId(rs.getString(1));
				creditCardBean.setCardOwnerName(rs.getString(2));
				creditCardBean.setCardNo(lastFourDisits);
				creditCardBean.setCardCompany(rs.getString(4));
				creditCardBean.setExpirationDate(rs.getString(5));
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
		return creditCardBean;
	}

	public String getCardId(String userId,String cardNo){
		String cardId=null;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT cardId FROM credit_card_table WHERE cardNo=? AND userId=?";

			st = cn.prepareStatement(sql);

			st.setString(1, cardNo);
			st.setString(2, userId);


			rs = st.executeQuery();


			while(rs.next()) {
				 cardId=(rs.getString(1));
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

		return cardId;
	}

}
