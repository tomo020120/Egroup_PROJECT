package dao.products;

import java.util.List;

import bean.ProductBean;
import bean.joinBean.AllProductDetailBean;
import bean.joinBean.ProductPictBean;

public interface ProductsDao {
	public void addProduct(ProductBean p);
	public AllProductDetailBean getProductsDetails(String itemId);
	public List<ProductPictBean> getAllProducts(String categoryId);
	public List<ProductPictBean> getProductsSearchResult(String productName, String sortNo,String[] colorsNo,String categoryId);
}
