package dao.admin.product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLProductEditDao implements ProductEditDao {

	private Connection cn = null;
	private PreparedStatement st = null;

	@Override
	public boolean addProduct(String productName, String price, String categoryId, String colorId, String shapeId,
			String artistId, String pictPath) {
			boolean flag = false; // insert結果flag

			try {
				cn = ConnectionManager.getInstance().getConnection();

				String sql = "call add_product(?,?,?,?,?,?,?)"; // カート内に同一商品があれば更新、なければ追加をするストアドプロシージャの実行

				CallableStatement cst = cn.prepareCall(sql);

				cst.setString(1, productName);
				cst.setInt(2, Integer.parseInt(price));
				cst.setInt(3, Integer.parseInt(categoryId));
				cst.setInt(4, Integer.parseInt(colorId));
				cst.setInt(5, Integer.parseInt(shapeId));
				cst.setInt(6, Integer.parseInt(artistId));
				cst.setString(7, pictPath);

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

	@Override
	public boolean updateProduct(String productName, String price, String categoryId, String colorId, String shapeId,
			String artistId, String pictPath, String itemId) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean deleteProduct(String itemId) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
