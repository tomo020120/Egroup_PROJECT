package dao.admin.product;

import bean.joinBean.AllProductDetailBean;

public interface ProductEditDao {

	public abstract AllProductDetailBean getTargetProduct(String itemId);
	public abstract boolean addProduct(AllProductDetailBean all);
	public abstract boolean updateProduct(AllProductDetailBean all);
	public abstract boolean deleteProduct(String[] itemIdArray);
}
