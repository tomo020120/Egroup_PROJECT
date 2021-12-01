package dao.cart;

import java.util.List;

import bean.CartBean;
import bean.joinBean.AllCartBean;

public interface CartDao{
	public abstract boolean createCart(String userId);
	public abstract List<AllCartBean> getCart(String userId);
	public abstract boolean addCartProduct(String itemId, String orderCount, String subTotal, String cartId);
	public abstract boolean updateCartTotal(String total, String userId);
	public abstract CartBean getCartTotal(String userId,String cartId);
}
