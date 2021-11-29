package dao.cart;

import java.util.List;

import bean.joinBean.AllCartBean;

public interface CartDao{
	public boolean addCart();
	public List<AllCartBean> getCart(String userId);
}
//