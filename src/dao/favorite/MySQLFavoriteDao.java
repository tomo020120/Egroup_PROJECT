package dao.favorite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.FavoriteBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLFavoriteDao implements FavoriteDao {
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	@Override
	public List<FavoriteBean> getFavoriteList(String userId) {

		ArrayList<FavoriteBean> favorited= new ArrayList<FavoriteBean>();

		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select t1.name, t1.price, t2.pictPath from favorite_table join product_table t1 on favorite_table.itemId=t1.ItemId join item_pict_table t2 on favorite_table.itemId=t2.itemId;";

			st=cn.prepareStatement(sql);

			rs=st.executeQuery();

			while(rs.next()) {
				FavoriteBean f = new FavoriteBean();

				f.setFavoriteId(rs.getString(1));
				f.setUserId(rs.getString(2));
				f.setItemId(rs.getString(3));


				favorited.add(f);
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
		return favorited;
	}

	@Override
	public boolean addFavorite(FavoriteBean favoriteBean) {
		boolean flag = false;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "insert into favorite_table (userId, itemId) values(?,?)";

			st = cn.prepareStatement(sql);

			st.setString(1, favoriteBean.getUserId());
			st.setString(2,favoriteBean.getItemId());
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
	public boolean deleteFavorite(String itemId,String userId) {
		boolean flag = false;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql ="delete FROM favorite_table WHERE itemId = ? and userId = ?";
			st = cn.prepareStatement(sql);

			st.setString(1,itemId);
			st.setString(2,userId);


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

}
