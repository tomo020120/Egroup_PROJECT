package dao.cart;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.joinBean.AllCartBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLCartDao implements CartDao{

	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	public boolean isExistenceCart(String userId) {
		boolean existFlag = false;
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select * from cart_table where userId = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, userId);

			rs = st.executeQuery();

			existFlag = rs.next();
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

		return existFlag;
	}

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


	public List<AllCartBean> getInsideCart(String userId) {

		List<AllCartBean> carts = new ArrayList<AllCartBean>();

		try {
			cn = ConnectionManager.getInstance().getConnection();


			String sql = "select * from all_inside_cart_view where userId = ?";

			st=cn.prepareStatement(sql);

			st.setString(1, userId);

			rs=st.executeQuery();


			while(rs.next()){
				AllCartBean p = new AllCartBean();

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


	public boolean addCartProduct(String itemId,String orderCount,int subTotal,String cartId) {
		boolean flag = false; // insert結果flag

			try {
				cn = ConnectionManager.getInstance().getConnection();

				String sql = "call upsert_inside_cart(?,?,?,?)"; // カート内に同一商品があれば更新、なければ追加をするストアドプロシージャの実行

				CallableStatement cst = cn.prepareCall(sql);

				cst.setString(1, itemId);
				cst.setString(2, orderCount);
				cst.setInt(3, subTotal);
				cst.setString(4, cartId);

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
		return flag;
	}


	public boolean updateCartTotal(String cartId) {
		boolean flag = false; // update結果flag
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "update cart_table SET total = (select ifNull(sum(subTotal),0) from inside_cart_table where cartId = ?) WHERE cartId = ?";
			st = cn.prepareStatement(sql);

			st.setString(1, cartId);
			st.setString(2, cartId);

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

	public boolean deleteCartProduct(String itemId,String cartId) {
		boolean flag = false; // insert結果flag

			try {
				cn = ConnectionManager.getInstance().getConnection();

				String sql ="delete FROM inside_cart_table WHERE itemId=? AND cartId=?";
				st = cn.prepareStatement(sql);

				st.setString(1, itemId);
				st.setString(2, cartId);

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
