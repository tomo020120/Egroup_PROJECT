package dao.favorite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.FavoriteBean;
import bean.joinBean.AllFavoriteBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLFavoriteDao implements FavoriteDao {
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	@Override
	public List<AllFavoriteBean> getFavoriteList(String userId) {

		ArrayList<AllFavoriteBean> favorited= new ArrayList<AllFavoriteBean>();

		try {
			cn = ConnectionManager.getInstance().getConnection();

			//String sql = "select t1.name, t1.price, t2.pictPath from favorite_table join product_table t1 on favorite_table.itemId=t1.ItemId join item_pict_table t2 on favorite_table.itemId=t2.itemId;";
			String sql="SELECT  pro.itemId,name,price,releaseDate,pictPath FROM favorite_table AS fav LEFT OUTER JOIN product_table AS pro ON fav.itemId = pro.itemId LEFT OUTER JOIN item_pict_table AS item ON pro.itemId=item.itemId WHERE userId=?";
			st=cn.prepareStatement(sql);
			st.setString(1, userId);

			rs=st.executeQuery();


			while(rs.next()) {
				AllFavoriteBean f = new AllFavoriteBean();

				f.setItemId(rs.getString(1));
				f.setName(rs.getString(2));
				f.setPrice(rs.getString(3));
				f.setReleaseDate(rs.getString(4));
				f.setPictPath(rs.getString(5));

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

	@Override
	public boolean isAddFavoriteItem(String itemId,String userId) {
		boolean flag = false;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql ="select count(itemId) from favorite_table where itemId = ? and userId = ?"; // お気に入りに追加したか否かを判定
			st = cn.prepareStatement(sql);

			st.setString(1,itemId);
			st.setString(2,userId);

			rs = st.executeQuery();

			while(rs.next()) {
				int count = rs.getInt(1);

				if(count > 0) {
					flag = true;
				}
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
