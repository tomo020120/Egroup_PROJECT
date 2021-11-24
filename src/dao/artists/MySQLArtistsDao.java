package dao.artists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ArtistBean;
import bean.joinBean.AllArtistDetailBean;
import dao.sql.MySQLConnector;

public class MySQLArtistsDao implements ArtistsDao{
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	ArrayList<ArtistBean> artists= new ArrayList<ArtistBean>();

	public List<ArtistBean> getAllArtists() {

		try {
			cn = MySQLConnector.getConnection();

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
			MySQLConnector.commitTransaction();


		}catch(SQLException e) {
			MySQLConnector.rollbackTransaction();
		}finally {
			MySQLConnector.closeTransaction();
		}
		return artists;
	}



	public List<AllArtistDetailBean> getArtistsDetails(String artistId) {
		List<AllArtistDetailBean> artists= new ArrayList<AllArtistDetailBean>();
		System.out.println(artistId);
		try {
			cn = MySQLConnector.getConnection();
			//int _testItemId =Integer.parseInt(testItemId);

			//itemIdが一致する商品詳の詳細を取ってくる
			String sql = "SELECT artistName,artistPict,countory,groupName,name,pictPath,artist_table.artistId,product_table.itemId " +
					"FROM artist_table join product_table ON artist_table.artistId = product_table.artistId " +
					"JOIN item_pict_table ON item_pict_table.itemId = product_table.itemId " +
					"Where artist_table.artistId="+artistId;


			st=cn.prepareStatement(sql);

			rs=st.executeQuery();

			AllArtistDetailBean all = new AllArtistDetailBean();
				while(rs.next()) {
					all.setArtistName(rs.getString(1));
					all.setArtistPict(rs.getString(2));
					all.setCoutory(rs.getString(3));
					if(rs.getString(4)!=null) {
						all.setGroup(rs.getString(4));
					}else {
						all.setGroup("SOLO");
					}
					all.setName(rs.getString(5));
					all.setPictPath(rs.getString(6));
					all.setItemId(rs.getString(8));





					artists.add(all);
				}

				System.out.println("要素数：" + artists.size());
				MySQLConnector.commitTransaction();
		}catch(SQLException e) {
			MySQLConnector.rollbackTransaction();
		}catch(Exception e) {
			MySQLConnector.rollbackTransaction();
		}finally {
			MySQLConnector.closeTransaction();
		}
		return artists;
	}

}


