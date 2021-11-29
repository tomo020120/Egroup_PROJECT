package dao.cart;

import java.util.List;

import bean.joinBean.AllCartBean;

public interface CartDao{
	public abstract boolean createCart(String userId);
	public abstract boolean addCart();
	public abstract List<AllCartBean> getCart(String userId);
}
