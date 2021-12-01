package dao.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CartBean;
import bean.joinBean.AllCartBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLCartDao implements CartDao{

	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	public boolean createCart(String userId) {
		boolean flag = false;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "insert into cart_table(userId) values(?)";

			st = cn.prepareStatement(sql);

			st.setString(1, userId);

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






	public List<AllCartBean> getCart(String userId) {

		List<AllCartBean> carts= new ArrayList<AllCartBean>();

		try {
			cn = ConnectionManager.getInstance().getConnection();


			String sql = "SELECT name,cart_inside_table.orderCount,subTotal,product_table.itemId,cart_table.cartId,pictPath,total FROM cart_table join cart_inside_table ON cart_table.cartId = cart_inside_table.cartId JOIN product_table ON product_table.itemId=cart_inside_table.itemId JOIN item_pict_table ON item_pict_table.itemId = product_table.itemId Where cart_table.userId = ?";

			st=cn.prepareStatement(sql);

			st.setString(1, userId);

			rs=st.executeQuery();

			AllCartBean p = new AllCartBean();

			while(rs.next()){
				p.setName(rs.getString(1));
				p.setOrderCount(rs.getString(2));
				p.setSubTotal(rs.getString(3));
				p.setItemId(rs.getString(4));
				p.setPictPath(rs.getString(6));
				p.setTotal(rs.getString(7));

				carts.add(p);
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

	return carts;

	}


	public boolean addCartProduct(String itemId,String orderCount,String subTotal,String cartId) {
		boolean flag = false; // insert結果flag

			try {
				cn = ConnectionManager.getInstance().getConnection();

				String sql = "insert into cart_inside_table values(?,?,?,?)";

				st = cn.prepareStatement(sql);

				st.setString(1, itemId);
				st.setString(2, orderCount);
				st.setString(3, subTotal);
				st.setString(4, cartId);

				System.out.println("実行SQL : " + st.toString());

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


	public boolean updateCartTotal(String total,String userId) {
		boolean flag = false; // update結果flag
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "update cart_table SET total = ? WHERE userId = ?";
			st = cn.prepareStatement(sql);

			st.setString(1, total);
			st.setString(2, userId);

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
	
	public CartBean getCartTotal(String cartId, String userId) {

		CartBean cart= new CartBean();

		try {
			cn = ConnectionManager.getInstance().getConnection();


			String sql = "SELECT total FROM cart_table Where cartId = ? and userId = ?";

			st=cn.prepareStatement(sql);

			st.setString(1, cartId);
			st.setString(2, userId);

			rs=st.executeQuery();

			while(rs.next()){
				cart.setTotal(rs.getString(1));
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

	return cart;

	}

}

