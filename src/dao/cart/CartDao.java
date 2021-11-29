package dao.cart;

import java.util.List;

import bean.ArtistBean;

public interface CartDao{
	public boolean addCart();
	public List<ArtistBean> getCart();
}
