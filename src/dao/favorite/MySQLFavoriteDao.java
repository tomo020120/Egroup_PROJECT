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
		List<FavoriteBean> favorite= new ArrayList<FavoriteBean>();

		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM Favorite_table";

			st=cn.prepareStatement(sql);

			rs=st.executeQuery();

			while(rs.next()) {
				FavoriteBean p = new FavoriteBean();

				p.setFavoriteId(rs.getString(1));
				p.setUserId(rs.getString(2));
				p.setItemId(rs.getString(3));



				favorite.add(p);
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
		return favorite;
	}

	@Override
	public boolean addFavorite(FavoriteBean favoriteBean) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean deleteFavorite(String favoriteId) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
