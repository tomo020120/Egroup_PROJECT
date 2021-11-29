package dao.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.joinBean.AllCartBean;
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







	public List<AllCartBean> getCart(String userId) {

	List<AllCartBean> carts= new ArrayList<AllCartBean>();

	try {
		cn = MySQLConnector.getConnection();


		String sql = "SELECT name,cart_inside_table.orderCount,subTotal,product_table.itemId,cart_table.cartId,pictPath,total FROM cart_table join cart_inside_table ON cart_table.cartId = cart_inside_table.cartId JOIN product_table ON product_table.itemId=cart_inside_table.itemId JOIN item_pict_table ON item_pict_table.itemId = product_table.itemId Where cart_table.userId = ?";

		st=cn.prepareStatement(sql);

		st.setString(1, userId);

		rs=st.executeQuery();

		AllCartBean p = new AllCartBean();

		while(rs.next()){
			System.out.println("ru-pu");

			p.setName(rs.getString(1));
			p.setOrderCount(rs.getString(2));
			p.setSubTotal(rs.getString(3));
			p.setItemId(rs.getString(4));
			p.setPictPath(rs.getString(6));
			p.setTotal(rs.getString(7));

			carts.add(p);
		}

		MySQLConnector.commitTransaction();


	}catch(SQLException e) {
		MySQLConnector.rollbackTransaction();
	}finally {
		MySQLConnector.closeTransaction();
	}

	return carts;

	}
}

