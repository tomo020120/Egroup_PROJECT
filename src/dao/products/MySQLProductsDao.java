package dao.products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ProductBean;
import bean.joinBean.AllProductDetailBean;
import bean.joinBean.ProductPictBean;
import exp.ResourceAccessException;

public class MySQLProductsDao implements ProductsDao{
	
	//addProductは仮置き
	public void addProduct(ProductBean p) {
		Connection cn = null;
		PreparedStatement st  = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Ibanezdb?characterEncoding=UTF-8&serverTimezone=JST",
					"customer","cpass");//
			
			cn.setAutoCommit(false);
			
			String sql = "insert into t_products (pid,name,price) " + "values(?,?,?)";
			
			st = cn.prepareStatement(sql);
			
			st.setString(1, p.getItemId());
			st.setString(2, p.getName());
			st.setString(3, p.getPrice());
			
			st.executeUpdate();
			
			cn.commit();
			
		}catch(ClassNotFoundException e) {
			throw new ResourceAccessException(e.getMessage(),e);
		}catch(SQLException e) {
			try {
				cn.rollback();
			}catch(SQLException e2) {
				throw new ResourceAccessException(e2.getMessage(),e2);
			}
			throw new ResourceAccessException(e.getMessage(),e);
		}finally {
			try {
				if(st != null) {
					st.close();
				}
			}catch(SQLException e2) {
				throw new ResourceAccessException(e2.getMessage(),e2);
			}finally {
				try {
					if(cn != null) {
						cn.close();
					}
				}catch(SQLException e3) {
					throw new ResourceAccessException(e3.getMessage(),e3);
				}
			}
		}
	}
	
	
	public List getProductsDetails(String itemId) {

		Connection cn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		ArrayList product= new ArrayList();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(
					"jdbc:mysql://localhost/Ibanezdb?characterEncoding=UTF-8&serverTimezone=JST",
					"customer","cpass");
			
			cn.setAutoCommit(false);
			
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
			cn.commit();
			
		}catch(ClassNotFoundException e) {
			throw new ResourceAccessException(e.getMessage(),e);
		}catch(SQLException e) {
			try {
				cn.rollback();
			}catch(SQLException e2) {
				throw new ResourceAccessException(e2.getMessage(),e2);
			}
			throw new ResourceAccessException(e.getMessage(),e);
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(st!=null) {
					st.close();
				}
			}catch (SQLException e2) {
				throw new ResourceAccessException(e2.getMessage(),e2);
			}finally {
				try {
					if(cn!=null) {
						cn.close();
					}
				}catch(SQLException e3) {
					throw new ResourceAccessException(e3.getMessage(),e3);
				}
			}
		}
		return product;
	}
	
	public List getAllProducts() {
		Connection cn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		ArrayList products= new ArrayList();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(
					"jdbc:mysql://localhost/Ibanezdb?characterEncoding=UTF-8&serverTimezone=JST",
					"customer","cpass");
			
			cn.setAutoCommit(false);
			
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
			cn.commit();
			
		}catch(ClassNotFoundException e) {
			throw new ResourceAccessException(e.getMessage(),e);
		}catch(SQLException e) {
			try {
				cn.rollback();
			}catch(SQLException e2) {
				throw new ResourceAccessException(e2.getMessage(),e2);
			}
			throw new ResourceAccessException(e.getMessage(),e);
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(st!=null) {
					st.close();
				}
			}catch (SQLException e2) {
				throw new ResourceAccessException(e2.getMessage(),e2);
			}finally {
				try {
					if(cn!=null) {
						cn.close();
					}
				}catch(SQLException e3) {
					throw new ResourceAccessException(e3.getMessage(),e3);
				}
			}
		}
		return products;
	}
}
