package dao.products;

import java.util.List;

import bean.ProductBean;
import bean.joinBean.AllProductDetailBean;
import bean.joinBean.ProductPictBean;

public interface ProductsDao {
	public void addProduct(ProductBean p);
	public List<AllProductDetailBean> getProductsDetails(String itemId);
	public List<ProductPictBean> getAllProducts();
}
