package dao.admin.product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.joinBean.AllProductDetailBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLProductEditDao implements ProductEditDao {

	private Connection cn = null;
	private PreparedStatement st = null;
	private CallableStatement cst = null;
	private ResultSet rs = null;



	@Override
	public AllProductDetailBean getTargetProduct(String itemId) {
		AllProductDetailBean all = new AllProductDetailBean();

		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "select name,price,categoryId,colorId,artistId,pictPath from product_table join item_pict_table using(itemId) where itemId = ?";

			st = cn.prepareStatement(sql);

			st.setString(1, itemId);

			rs = st.executeQuery();

			while(rs.next()) {
				String name = rs.getString(1);
				String price = rs.getString(2);
				String categoryId = rs.getString(3);
				String colorId = rs.getString(4);

				String artistId = rs.getString(5);
				String pictPath = rs.getString(6);

				all.setName(name);
				all.setPrice(price);
				all.setCategoryId(categoryId);
				all.setColorId(colorId);
				all.setArtistId(artistId);
				all.setPictPath(pictPath);
				all.setItemId(itemId);
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

		return all;
	}

	@Override
	public boolean addProduct(AllProductDetailBean all) {
			boolean flag = false; // insert結果flag

			try {
				cn = ConnectionManager.getInstance().getConnection();

				String sql = "call add_product(?,?,?,?,?,?)"; // 同一商品名、各IDの有無をすべてチェックし正しいデータの場合product_table、item_pict_tableにinsertするプロシージャ実行

				cst = cn.prepareCall(sql);

				cst.setString(1, all.getName());
				cst.setInt(2, Integer.parseInt(all.getPrice()));
				cst.setString(3, all.getCategoryId());
				cst.setString(4, all.getColorId());
				cst.setString(5, all.getArtistId());
				cst.setString(6, all.getPictPath());

				int result = cst.executeUpdate();

				System.out.println("影響行数" + result);

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
				if(cst != null) {
					try {
						cst.close();
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
	public boolean updateProduct(AllProductDetailBean all) {
		boolean flag = false; // insert結果flag

		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "update product_table join item_pict_table using(itemId) set name=?,price=?,categoryId=?,colorId=?,artistId=?,pictPath=? where itemId = ?"; //二つのテーブルの結合をしてから一括更新

			st = cn.prepareStatement(sql);

			st.setString(1, all.getName());
			st.setString(2, all.getPrice());
			st.setString(3, all.getCategoryId());
			st.setString(4, all.getColorId());
			st.setString(5, all.getArtistId());
			st.setString(6, all.getPictPath());
			st.setString(7, all.getItemId());

			int result = st.executeUpdate();

			System.out.println("影響行数" + result);

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
	public boolean deleteProduct(String[] itemIdArray) {
		boolean flag = false; // insert結果flag
		int result = 0;
		int length = itemIdArray.length; // 要素数
		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql = "delete from product_table where itemId=?"; //二つのテーブルの結合をしてから一括更新
			st = cn.prepareStatement(sql);

			for(int i = 0; i < length; i++) {
				st.setString(1, itemIdArray[i]);
				result += st.executeUpdate();
			}

			System.out.println("影響行数" + result);

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
