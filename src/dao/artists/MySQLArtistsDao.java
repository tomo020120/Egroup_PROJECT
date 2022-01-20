package dao.artists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ArtistBean;
import bean.joinBean.AllArtistDetailBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLArtistsDao implements ArtistsDao{
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	public List<ArtistBean> getAllArtists() {
		List<ArtistBean> artists= new ArrayList<ArtistBean>();

		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "SELECT * FROM artist_table";

			st=cn.prepareStatement(sql);

			rs=st.executeQuery();

			while(rs.next()) {
				ArtistBean p = new ArtistBean();

				p.setArtistId(rs.getString(1));
				p.setArtistName(rs.getString(2));
				p.setCoutory(rs.getString(3));
				p.setGroup(rs.getString(4));
				p.setArtistPict(rs.getString(5));



				artists.add(p);
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
		return artists;
	}



	public List<AllArtistDetailBean> getArtistsDetails(String artistId) {
		List<AllArtistDetailBean> artists= new ArrayList<AllArtistDetailBean>();
		System.out.println(artistId);
		try {
			cn = ConnectionManager.getInstance().getConnection();

			//itemIdが一致する商品詳の詳細を取ってくる
			String sql = "select * from artist_detail_view where artistId = ?"; // Viewへの問い合わせ


			st=cn.prepareStatement(sql);

			st.setString(1, artistId);

			rs=st.executeQuery();

			AllArtistDetailBean all = new AllArtistDetailBean();
				while(rs.next()) {
					all.setArtistName(rs.getString(1));
					all.setArtistPict(rs.getString(2));
					all.setCoutory(rs.getString(3));

					if(rs.getString(4) != null) {
						System.out.println("nullじゃない");
						all.setGroup(rs.getString(4));
					}else {
						System.out.println("nullだったよ");
						all.setGroup("SOLO");
					}
					all.setName(rs.getString(5));
					all.setPictPath(rs.getString(6));
					all.setItemId(rs.getString(7));

					artists.add(all);
				}

				System.out.println("要素数：" + artists.size());
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
		return artists;
	}

}


