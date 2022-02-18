package dao.products;

import java.util.List;

import bean.joinBean.AllProductDetailBean;
import bean.joinBean.ProductPictBean;

public interface ProductsDao {
	public AllProductDetailBean getProductsDetails(String itemId);
	public List<ProductPictBean> getAllProducts(String categoryId);
	public List<ProductPictBean> getProductsSearchResult(String productName, String sortNo,String[] colorsNo,String pageNo,String categoryId);
	public String getProductsSearchResultCount(String productName,String[] colorsNo,String categoryId);
}
