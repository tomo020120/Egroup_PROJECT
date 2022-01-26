package dao.history;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.joinBean.AllBrowsingHistoryInfoBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLHistoryDao implements HistoryDao{
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	
	public List<AllBrowsingHistoryInfoBean> getProductHistory(String userId) {
		ArrayList<AllBrowsingHistoryInfoBean> histories= new ArrayList<AllBrowsingHistoryInfoBean>();

		try {
			cn = ConnectionManager.getInstance().getConnection();
			
			//ページ下にあるのは写真のみ
			//以下閲覧履歴ページ
			//全ての履歴を削除ボタンあり
			//各一覧には商品名、値段、写真、履歴から削除(押したら削除される)
			//表product_table,item_pict_table,browsing_history_table
			String sql = "SELECT history.itemId,product.name,product.price,pict.pictPath,history.clickDate FROM browsing_history_table AS history LEFT OUTER JOIN product_table AS product ON history.itemId=product.itemId LEFT OUTER JOIN item_pict_table AS pict ON history.itemId=pict.itemId WHERE userId="+ userId +" order by clickDate DESC;";
			
			st=cn.prepareStatement(sql);

			rs=st.executeQuery();

			while(rs.next()) {
				AllBrowsingHistoryInfoBean b = new AllBrowsingHistoryInfoBean();

				b.setItemId(rs.getString(1));
				b.setName(rs.getString(2));;
				b.setPrice(rs.getString(3));
				b.setPictPath(rs.getString(4));
				b.setClickDate(rs.getString(5));
				
				histories.add(b);
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
		return histories;
	}
	
	public void addProductHistory(String userId, String itemId) {
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "insert into browsing_history_table (userId,itemId) " + "values(?,?)";

			st = cn.prepareStatement(sql);

			st.setString(1, userId);
			st.setString(2, itemId);

			st.executeUpdate();

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
	
	public void deleteProductHistory(String userId, String itemId) {
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "delete from browsing_history_table where userId = ? and itemId = ? ;";

			st = cn.prepareStatement(sql);

			st.setString(1, userId);
			st.setString(2, itemId);

			st.executeUpdate();

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
	
}
