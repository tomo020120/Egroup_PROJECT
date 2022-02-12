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
	private CallableStatement cst = null;

	@Override
	public boolean addProduct(String productName, String price, String categoryId, String colorId, String shapeId,
			String artistId, String pictPath) {
			boolean flag = false; // insert結果flag

			try {
				cn = ConnectionManager.getInstance().getConnection();

				String sql = "call add_product(?,?,?,?,?,?,?)"; // 同一商品名、各IDの有無をすべてチェックし正しいデータの場合product_table、item_pict_tableにinsertするプロシージャ実行

				cst = cn.prepareCall(sql);

				cst.setString(1, productName);
				cst.setInt(2, Integer.parseInt(price));
				cst.setString(3, categoryId);
				cst.setString(4, colorId);
				cst.setString(5, shapeId);
				cst.setString(6, artistId);
				cst.setString(7, pictPath);

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
