package dao.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.joinBean.AllProductDetailBean;
import bean.joinBean.ProductPictBean;
import dbManager.ConnectionManager;
import ex.DaoOperationException;

public class MySQLProductsDao implements ProductsDao{
	private Connection cn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	public AllProductDetailBean getProductsDetails(String itemId) {
		AllProductDetailBean all = new AllProductDetailBean();
		try {
			cn = ConnectionManager.getInstance().getConnection();

			//itemIdが一致する商品詳の詳細を取ってくる
			String sql = "SELECT  a.itemId,name,price,CAST(`releaseDate` AS DATE),pictPath,categoryName,shapeName,colorName,artistName,a.categoryId,a.colorId,a.shapeId,a.artistId\n" +
					"FROM Ibanezdb.product_table AS a LEFT OUTER JOIN Ibanezdb.item_pict_table AS b ON a.itemId = b.itemId\n" +
					"LEFT OUTER JOIN Ibanezdb.category_table AS c ON a.categoryId=c.categoryId\n" +
					"LEFT OUTER JOIN Ibanezdb.color_table AS d ON a.colorId=d.colorId\n" +
					"LEFT OUTER JOIN Ibanezdb.shape_table AS e ON a.shapeId=e.shapeId\n" +
					"LEFT OUTER JOIN Ibanezdb.artist_table AS f ON a.artistId=f.artistId WHERE a.itemId = ?";


			st=cn.prepareStatement(sql);

			st.setString(1, itemId);

			rs=st.executeQuery();

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
				all.setCategoryId(rs.getString(10));

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

	public List<ProductPictBean> getAllProducts(String categoryId) {
		ArrayList<ProductPictBean> products= new ArrayList<ProductPictBean>();

		try {
			cn = ConnectionManager.getInstance().getConnection();

			String sql=  "SELECT a.itemId,name,price,releaseDate,orderCount,categoryId,colorId,shapeId,artistId,pictId,pictPath FROM Ibanezdb.item_pict_table AS a JOIN Ibanezdb.product_table AS b ON a.itemId = b.itemId where categoryId=? and name LIKE '%%' order by a.itemId ASC LIMIT 30 OFFSET 0";

			st=cn.prepareStatement(sql);

			st.setString(1, categoryId);

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
		return products;
	}

	public List<ProductPictBean> getProductsSearchResult(String productName,String sortCode,String[] colorsNo,String pageNo,String categoryId) {
		ArrayList<ProductPictBean> products= new ArrayList<ProductPictBean>();
		try {
			cn = ConnectionManager.getInstance().getConnection();
			int length = colorsNo.length-1;
			int questionCount=0;
			String sql = "SELECT a.itemId,name,price,releaseDate,orderCount,categoryId,colorId,shapeId,artistId,pictId,pictPath FROM Ibanezdb.item_pict_table AS a JOIN Ibanezdb.product_table AS b ON a.itemId = b.itemId where name LIKE ?";

			//まずsqlを完成させる

			if(length!=0) {
				sql+=" and colorId IN (";
				for(int count =1;count <=length;count++) {
					//0の分の?は抜いてある
					sql+=" ? ";
					questionCount++;
					if(1<length && count<length) {
						sql+=", ";
					}
				}
				sql+=" ) ";
			}else {
				sql+=" ? ";
				questionCount=1;
			}

			pageNo=Integer.toString(30*(Integer.parseInt(pageNo)-1));

			sql+="AND categoryId="+categoryId+" order by "+sortCode+" LIMIT 30 OFFSET "+pageNo;
			System.out.println("仮SQL:"+sql);

			//set
			st=cn.prepareStatement(sql);

			st.setString(1, "%" + productName + "%");

			//0 1,2,3 No.length=4 ,yes
			//0 1,2 No.length=3 ,yes
			//0 1 length = 2 ,NO
			//カラー条件コード構築
			int j=2;
			if(length!=0) {
				for(int i=1;i <= questionCount;i++) {
					st.setInt(j, Integer.parseInt(colorsNo[i]));
					j++;
				}
			}else {
				st.setString(j, "");
				j++;
			}

			//st.setString(j, sortCode);

			System.out.println("実行SQL :" + st.toString());

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
		return products;
	}
	public String getProductsSearchResultCount(String productName,String[] colorsNo,String categoryId) {
		String maxPageCount=null;

		try {
			cn = ConnectionManager.getInstance().getConnection();
			int length = colorsNo.length-1;
			int questionCount=0;
			String sql = "SELECT count(*) FROM Ibanezdb.item_pict_table AS a JOIN Ibanezdb.product_table AS b ON a.itemId = b.itemId where categoryId= "+categoryId+" and name LIKE ?";
			//String sql = "SELECT count(a.itemId) FROM Ibanezdb.item_pict_table AS a JOIN Ibanezdb.product_table AS b ON a.itemId = b.itemId where name LIKE '%%';";
			//まずsqlを完成させる

			if(length!=0) {
				sql+=" and colorId IN (";
				for(int count =1;count <=length;count++) {
					//0の分の?は抜いてある
					sql+=" ? ";
					questionCount++;
					if(1<length && count<length) {
						sql+=", ";
					}
				}
				sql+=" ) ";
			}else {
				sql+=" ? ";
				questionCount=1;
			}



			System.out.println("仮SQL:"+sql);

			//set
			st=cn.prepareStatement(sql);
			st.setString(1, "%" + productName + "%");

			//0 1,2,3 No.length=4 ,yes
			//0 1,2 No.length=3 ,yes
			//0 1 length = 2 ,NO
			//カラー条件コード構築
			int j=2;
			if(length!=0) {
				for(int i=1;i <= questionCount;i++) {
					st.setInt(j, Integer.parseInt(colorsNo[i]));
					j++;
				}
			}else {
				st.setString(j, "");
				j++;
			}

			//st.setString(j, sortCode);

			System.out.println("実行SQL :" + st.toString());

			rs=st.executeQuery();

			String resultCount=null;
			while(rs.next()) {
				resultCount=rs.getString(1);
			}

			double countNo=Math.ceil(Double.parseDouble(resultCount)/30.0);

			maxPageCount=Integer.toString((int)countNo);


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
		return maxPageCount;
	}
}
