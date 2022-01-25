package dao.cart;

import java.util.List;

import bean.joinBean.AllCartBean;

public interface CartDao{
	public abstract boolean isExistenceCart(String userId);
	public abstract boolean createCart(String userId);
	public abstract List<AllCartBean> getInsideCart(String userId);
	public abstract boolean addCartProduct(String itemId, String orderCount, int subTotal, String cartId);
	public abstract boolean updateCartTotal(String cartId);
	public abstract boolean deleteCartProduct(String cartId,String itemId);
	public abstract int getTotalAmount(String cartId);
}
