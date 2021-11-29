package dao.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ArtistBean;
import dao.sql.MySQLConnector;

public class MySQLCartDao implements CartDao{

	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	public boolean addCart() {
		boolean flag = false; // insert結果flag

		try {
			cn = MySQLConnector.getConnection();

			String sql = "insert into cart_inside_table(itemId,orderCount,subTotal,cartId) values(?,?,?,?)";

			st = cn.prepareStatement(sql);

			int result = st.executeUpdate();

			if(result > 0) {
				flag = true;
				MySQLConnector.commitTransaction();
			}
		}
		catch(SQLException e) {
			MySQLConnector.rollbackTransaction();
			e.printStackTrace();
		}
		catch(Exception e) {
			MySQLConnector.rollbackTransaction();
			e.printStackTrace();
		}
		finally {
			MySQLConnector.closeTransaction();
		}

		return flag;
	}
	

	
	
	
	
		ArrayList<ArtistBean> artists= new ArrayList<ArtistBean>();

	public List<ArtistBean> getCart() {

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

}


