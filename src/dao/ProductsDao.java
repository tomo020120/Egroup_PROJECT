package dao;

import java.util.List;

import bean.ProductBean;

public interface ProductsDao {
	public void addProduct(ProductBean p);
	public List<ProductBean> getProductsDetails(String itemId);
	public List<?> getAllProducts();
}
