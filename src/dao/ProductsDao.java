package dao;

import java.util.List;

import bean.ProductBean;

public interface ProductsDao {
	public void addProduct(ProductBean p);
	public ProductBean getProduct(String pid);
	public List<ProductBean> getAllProducts();
}
