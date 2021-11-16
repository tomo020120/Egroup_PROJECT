package dao;

import java.util.List;

import bean.Product;

public interface ProductsDao {
	public void addProduct(Product p);
	public Product getProduct(String pid);
<<<<<<< HEAD
	public List getAllProducts();
=======
	public List<Product> getAllProducts();
>>>>>>> branch 'master' of git@github.com:tomo020120/Egroup_PROJECT.git
}
