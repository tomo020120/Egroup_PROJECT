package dao;

import java.util.List;

import bean.Product;

public interface ProductsDao {
	public void addProduct(Product p);
	public Product getProduct(String pid);
	public List getAllProducts();
}
