package dao.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ProductBean;
import bean.joinBean.AllProductDetailBean;
import bean.joinBean.ProductPictBean;
import dao.sql.MySQLConnector;

public class MySQLProductsDao implements ProductsDao{
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	//addProductは仮置き
	public void addProduct(ProductBean p) {

		try {
			cn = MySQLConnector.getConnection();

			String sql = "insert into t_products (pid,name,price) " + "values(?,?,?)";

			st = cn.prepareStatement(sql);

			st.setString(1, p.getItemId());
			st.setString(2, p.getName());
			st.setString(3, p.getPrice());

			st.executeUpdate();

			MySQLConnector.commitTransaction();

		}catch(SQLException e) {
			MySQLConnector.rollbackTransaction();
		}catch(Exception e) {
			MySQLConnector.rollbackTransaction();
		}finally {
			MySQLConnector.closeTransaction();
		}
	}


	public List<AllProductDetailBean> getProductsDetails(String itemId) {
		List<AllProductDetailBean> product= new ArrayList<AllProductDetailBean>();

		try {
			cn = MySQLConnector.getConnection();
			//int _testItemId =Integer.parseInt(testItemId);

			//itemIdが一致する商品詳の詳細を取ってくる
			String sql = "SELECT  a.itemId,name,price,releaseDate,pictPath,categoryName,shapeName,colorName,artistName,a.categoryId,a.colorId,a.shapeId,a.artistId\n" +
					"FROM Ibanezdb.product_table AS a LEFT OUTER JOIN Ibanezdb.item_pict_table AS b ON a.itemId = b.itemId\n" +
					"LEFT OUTER JOIN Ibanezdb.category_table AS c ON a.categoryId=c.categoryId\n" +
					"LEFT OUTER JOIN Ibanezdb.color_table AS d ON a.colorId=d.colorId\n" +
					"LEFT OUTER JOIN Ibanezdb.shape_table AS e ON a.shapeId=e.shapeId\n" +
					"LEFT OUTER JOIN Ibanezdb.artist_table AS f ON a.artistId=f.artistId WHERE a.itemId="+itemId+";";


			st=cn.prepareStatement(sql);

			rs=st.executeQuery();

				AllProductDetailBean all = new AllProductDetailBean();
				while(rs.next()) {
					all.setItemId(rs.getString(1));
					all.setName(rs.getString(2));
					all.setPrice(rs.getString(3));
					all.setReleaseDate(rs.getString(4));
					all.setPictPath(rs.getString(5));

					all.setCategoryName(rs.getString(6));
					all.setShapeName(rs.getString(7));
					all.setColorName(rs.getString(8));
					all.setArtistName(rs.getString(9));

				product.add(all);
				}
				MySQLConnector.commitTransaction();
		}catch(SQLException e) {
			MySQLConnector.rollbackTransaction();
		}catch(Exception e) {
			MySQLConnector.rollbackTransaction();
		}finally {
			MySQLConnector.closeTransaction();
		}
		return product;
	}

	public List<ProductPictBean> getAllProducts() {
		ArrayList<ProductPictBean> products= new ArrayList<ProductPictBean>();

		try {
			cn = MySQLConnector.getConnection();

			String sql = "SELECT a.itemId,name,price,releaseDate,orderCount,categoryId,colorId,shapeId,artistId,pictId,pictPath FROM Ibanezdb.item_pict_table AS a JOIN Ibanezdb.product_table AS b ON a.itemId = b.itemId;";

			st=cn.prepareStatement(sql);

			rs=st.executeQuery();

			while(rs.next()) {
				ProductPictBean p = new ProductPictBean();

				p.setItemId(rs.getString(1));
				p.setName(rs.getString(2));
				p.setPrice(rs.getString(3));
				p.setReleaseDate(rs.getString(4));
				p.setOrderCount(rs.getString(5));
				p.setCategoryId(rs.getString(6));
				p.setColorId(rs.getString(7));
				p.setShapeId(rs.getString(8));
				p.setArtistId(rs.getString(9));

				p.setPictId(rs.getString(10));
				p.setPictPath(rs.getString(11));

				products.add(p);
			}
			MySQLConnector.commitTransaction();


		}catch(SQLException e) {
			MySQLConnector.rollbackTransaction();
		}finally {
			MySQLConnector.closeTransaction();
		}
		return products;
	}
}
